package flink.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryScalarReturn;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.type.Type;
import org.hibernate.type.TypeResolver;

/**
 * hibernate hql����.
 * 
 *
 */
public class HqlHelper {
	private static final String SPACE = " ";
	private static final String TAB = "\t";
	private static final String DOT = ".";
	private static final String AS = "as";
	private static final String AS_WITH_SPACE = SPACE.concat(AS).concat(SPACE);
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private StringBuffer hql = new StringBuffer();
	private List params = new ArrayList();
	private List paramTypes = new ArrayList();
	private List scalars = new ArrayList();
	
	public HqlHelper() {
	}

	public void clear() {
		this.hql.delete(0, hql.length());
		params.clear();
		paramTypes.clear();
		scalars.clear();
	}
	
	public HqlHelper(Class clazz) {
		hql.append("from ");
		hql.append(clazz.getName());
		hql.append(SPACE);
	}
	
	public HqlHelper(Class clazz, String alias) {
		hql.append("from ");
		hql.append(clazz.getName());
		hql.append(SPACE);
		hql.append(alias);
		hql.append(SPACE);
	}
	
	public StringBuffer getHql() {
		return hql;
	}
	
	@SuppressWarnings("unchecked")
	public HqlHelper appendScalar(String scalar, Type type) {
		scalars.add(new NativeSQLQueryScalarReturn(scalar, type));
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public Type[] getParamTypes() {
		return (Type[]) paramTypes.toArray(new Type[paramTypes.size()]);
	}
	
	public Object[] getParams() {
		return params.toArray();
	}
	
	public List getParamList() {
		return params;
	}
	
	public HqlHelper append(String hql) {
		appendHql(hql);
		return this;
	}
	
	public HqlHelper append(String[] properties, String entityAlias) {
		getHql(properties, entityAlias);
		appendHql(getHql(properties, entityAlias));
		
		return this;
	}

	/**
	 * ���hql.
	 * 
	 * @param hql
	 */
	private HqlHelper appendHql(String hql) {
		this.hql.append(hql + SPACE);
		return this;
	}
	
	/**
	 * ��Ӳ���.
	 * 
	 * @param hql
	 * @param param
	 */
	public HqlHelper append(String hql, Object param) {
		if (param == null) {
			return this;
		}
		
		if (param.getClass().equals(String.class)) {
			String p = (String) param;
			
			if (p.trim().length() > 0) {
				appendHql(hql);
				appendParam(param);
			}
		}
		else {
			appendHql(hql);
			appendParam(param);
		}
		
		return this;
	}
	
	/**
	 * ����
	 * @param orderBy
	 * @return
	 */
	public HqlHelper orderBy(String orderBy) {
		return StringUtils.isEmpty(orderBy) ? this : appendHql("order by").appendHql(orderBy);
	}
	
	/**
	 * ����
	 * @param orderBy
	 * @param orderType ����ʽ: ������
	 * @return
	 */
	public HqlHelper orderBy(String orderBy, String orderType) {
		if (orderType == null) {
			orderType = StringUtils.EMPTY;
		}
		
		return StringUtils.isEmpty(orderBy) ? this : appendHql("order by").appendHql(orderBy).appendHql(orderType);
	}
	
	/**
	 * ����������.
	 * 
	 * @param hql ��: "where id in ?"
	 * @param params
	 */
	public HqlHelper append(String hql, Object[] params) {
		if (params == null || params.length == 0) {
			return this;
		}
		
		int length = params.length;
		String placeholder = getPlaceholderString(length);
		String s = hql.replaceFirst("\\?", "(".concat(placeholder).concat(")"));
		appendHql(s);
		
		for (int i = 0; i < length; i++) {
			appendParam(params[i]);
		}
		
		return this;
	}
	
	/**
	 * ����ռλ���ַ���.
	 * 
	 * @param length
	 * @return
	 */
	private String getPlaceholderString(int length) {
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < length; i++) {
			sb.append(", ?");
		}
		
		return sb.toString().substring(2);
	}

	/**
	 * ��Ӳ���.
	 * 
	 * @param hql
	 * @param allowAppend
	 * @param param
	 */
	public HqlHelper append(String hql, boolean allowAppend, Serializable param) {
		if (!allowAppend) {
			return this;
		}
		
		append(hql, param);
		return this;
	}
	
	/**
	 * ����ַ���ģ��ƥ��Ĳ���.
	 * 
	 * @param hql
	 * @param param
	 * @param matchMode
	 */
	public HqlHelper append(String hql, String param, MatchMode matchMode) {
		if (param == null || param.trim().length() == 0) {
			return this;
		}
		
		if (matchMode == null) {
			return this;
		}
		
		String matchName = matchMode.toString();
		
		if (MatchMode.START.toString().equals(matchName)) {
			param = param.concat("%");
		}
		else if (MatchMode.END.toString().equals(matchName)) {
			param = "%".concat(param);
		}
		else if (MatchMode.ANYWHERE.toString().equals(matchName)) {
			param = "%".concat(param).concat("%");
		}
		else {
		}
		
		appendHql(hql);
		appendParam(param);
		
		return this;
	}

	/**
	 * ��Ӳ���.
	 * 
	 * @param param
	 */
	@SuppressWarnings("unchecked")
	private void appendParam(Object param) {
		this.params.add(param);
		this.paramTypes.add(getParamType(param.getClass()));
	}

	/**
	 * ��Ӳ�������.
	 * 
	 * @param clazz
	 */
	private Type getParamType(Class clazz) {
		//return TypeFactory.heuristicType(clazz.getName());
		TypeResolver typeResolver = new TypeResolver();
		return typeResolver.heuristicType(clazz.getName());
	}
	
	/**
	 * @param session
	 * @return
	 */
	public Query getQuery(Session session) {
		return session.createQuery(hql.toString())
				.setParameters(params.toArray(), ArrayHelper.toTypeArray(paramTypes));
	}
	
	public SQLQuery getSQLQuery(Session session) {
		SQLQuery query =  session.createSQLQuery(hql.toString());
		
		for (Iterator i = scalars.iterator(); i.hasNext();) {
			NativeSQLQueryScalarReturn scalar = (NativeSQLQueryScalarReturn) i.next();
			query.addScalar(scalar.getColumnAlias(), scalar.getType());
		}
		
		query.setParameters(this.getParams(), this.getParamTypes());
		
		return query;
	}
	
	/**
	 * ����hql �Ͳ�ѯ������ȡ��ѯ�����¼��.<br>
	 * <b>
	 * 	��֧�ֲ�����order �Ӿ�, ��order �Ӿ�ֻ����һ����Ϊ���һ���Ӿ��hql.
	 * 	������ͳ��hql.
	 * </b>
	 * 
	 * @return
	 */
	public int getRecordCount(Session session) {
		String hql = this.hql.toString().trim();
		hql = replaceHqlHead(hql);
		hql = replaceHqlTail(hql);
		
		Long count = (Long) session.createQuery(hql)
				.setParameters(params.toArray(), ArrayHelper.toTypeArray(paramTypes))
				.uniqueResult();
		
		return count.intValue();
	}
	
	/**
	 * ����sql��ѯ����.
	 */
	public int getRecordCountBySql(Session session) {
		String hql = this.hql.toString().trim();
		
		BigDecimal count = (BigDecimal) session.createSQLQuery("select count(*) from (".concat(hql).concat(") t"))
			.setParameters(params.toArray(), ArrayHelper.toTypeArray(paramTypes))
			.uniqueResult();
		
		return count.intValue();
	}
	
	/**
	 * @param hql
	 * @return
	 */
	private String replaceHqlTail(String hql) {
		int orderPos = this.getFirstPos(hql, "order");
		
		if (orderPos == -1) {
			return hql;
		}
		else {
			return hql.substring(0, orderPos);
		}
	}

	/**
	 * �滻hql ͷΪselect count(*).
	 * @param hql
	 * @return
	 */
	private String replaceHqlHead(String hql) {
		String head = "select count(*) ";
		String firstWord = getFirstWord(hql);
		
		if ("from".equalsIgnoreCase(firstWord)) {
			return head + hql;
		}
		else if ("select".equalsIgnoreCase(firstWord)) {
			int pos = getFirstPos(hql, "from");
			
			if (pos == -1) {
				throw new IllegalArgumentException("hql illegal");
			}
			
			return head + hql.substring(pos);
		}
		else {
			throw new IllegalArgumentException("hql illegal");
		}
		
	}

	/**
	 * ��ȡ��һ������.
	 * 
	 * @param hql
	 * @return
	 */
	private String getFirstWord(String hql) {
		int firstSeparatorPos = getFirstSeparatorPos(hql);
		
		return hql.substring(0, firstSeparatorPos);
	}

	/**
	 * ��ȡ��һ���ָ�����λ��.
	 * 
	 * @param hql
	 * @return
	 */
	private int getFirstSeparatorPos(String hql) {
		int firstSpacePos = hql.indexOf(SPACE);
		int firstTabPos = hql.indexOf(TAB);
		int firstLineSeparatorPos = hql.indexOf(LINE_SEPARATOR);
		int pos = -1;
		
		if (firstSpacePos == -1 || firstTabPos == -1) {
			pos = Math.max(firstSpacePos, firstTabPos);
		}
		else {
			pos = Math.min(firstSpacePos, firstTabPos);
		}
		
		if (firstLineSeparatorPos == -1) {
			return pos;
		}
		else {
			return Math.min(pos, firstLineSeparatorPos);
		}
	}

	/**
	 * ��ȡ�ؼ��ֵ�һ�γ��ֵ�λ��.
	 * 
	 * @param keyword
	 * @return
	 */
	private int getFirstPos(String hql, String keyword) {
		String s = hql.toLowerCase();
		
		if (hql.startsWith(keyword.toLowerCase())) {
			return 0;
		}
		
		String[] padedKeywords = padSeparatorKeyword(keyword);
		
		for (int i = 0; i < padedKeywords.length; i++) {
			int pos = s.indexOf(padedKeywords[i]);
			
			if (pos > 0) {
				return pos;
			}
		}
		
		return -1;
	}

	/**
	 * ��hql �ؼ��ּӱ�ʶ��.
	 * 
	 * @param keyword
	 * @return
	 */
	private String[] padSeparatorKeyword(String keyword) {
		return new String[] {
				SPACE.concat(keyword).concat(SPACE),
				SPACE.concat(keyword).concat(TAB),
				SPACE.concat(keyword).concat(LINE_SEPARATOR),
				
				TAB.concat(keyword).concat(SPACE),
				TAB.concat(keyword).concat(TAB),
				TAB.concat(keyword).concat(LINE_SEPARATOR),
				
				LINE_SEPARATOR.concat(keyword).concat(SPACE),
				LINE_SEPARATOR.concat(keyword).concat(TAB),
				LINE_SEPARATOR.concat(keyword).concat(LINE_SEPARATOR)
		};
	}

	public List list(Session session) {
		return this.getQuery(session).list();
	}
	
	public List list(Session session, String alias, LockMode lockMode) {
		return this.getQuery(session).setLockMode(alias, lockMode).list();
	}
	
	public Object getUniqueResult(Session session) {
		return this.getQuery(session).uniqueResult();
	}
	
	public Object getUniqueResult(Session session, String alias, LockMode lockMode) {
		return this.getQuery(session).setLockMode(alias, lockMode).list();
	}
	
	/**
	 * ȡ��������ƴ�ӵ�hql, ��"user.code as code, user.name as name"
	 * @param properties
	 * @param entityAlias
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static String getHql(String[] properties, String entityAlias) {
		if (ArrayUtils.isEmpty(properties)) {
			return StringUtils.EMPTY;
		}
		
		List list = new ArrayList();
		String entityAliasPrefix = StringUtils.isEmpty(entityAlias) ? StringUtils.EMPTY : entityAlias.concat(DOT);
		
		for (int i = 0; i < properties.length; i++) {
			String property = properties[i];
			list.add(entityAliasPrefix.concat(property).concat(AS_WITH_SPACE).concat(property));
		}
		
		return StringUtils.join(list.iterator(), ",".concat(SPACE));
	}
}
