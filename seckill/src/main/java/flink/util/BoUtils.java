package flink.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

/**
 * bean utils.
 * 
 * 
 */
public abstract class BoUtils {
	/**
	 * �������.
	 * 
	 * @param targetList
	 *            Ŀ���б�
	 * @param targetKeyName
	 *            Ŀ������key ��Ŀ���б���Ԫ��ȡkeyֵ(��user.code)
	 * @param targetPropertyName
	 *            Ŀ���������� ��ӵ���������(��user.name)
	 * 
	 * @param sourceList
	 *            Դ�б�
	 * @param sourceKeyName
	 *            Դ����key ��Դ�б���ȡkeyֵ��Ӧ���б�Ԫ��
	 * @param sourcePropertyName
	 *            Դ�������� ��Դ�б�Ԫ����ȡ����ֵ��ΪĿ���������Ƶ�ֵ
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void addProperty(List targetList, String targetKeyName,
			String targetPropertyName, List sourceList, String sourceKeyName,
			String sourcePropertyName) {
		if (CollectionUtils.isEmpty(targetList)
				|| CollectionUtils.isEmpty(sourceList)) {
			return;
		}

		try {
			// ��Դlistת��Ϊmap.
			Map sourceMap = new HashMap();
			for (Iterator i = sourceList.iterator(); i.hasNext();) {
				Object element = i.next();
				Object keyValue = PropertyUtils.getProperty(element,
						sourceKeyName);
				Object propertyValue = PropertyUtils.getProperty(element,
						sourcePropertyName);

				sourceMap.put(keyValue, propertyValue);
			}

			// ����Ŀ������.
			for (Iterator i = targetList.iterator(); i.hasNext();) {
				Object element = i.next();
				Object keyValue = PropertyUtils.getProperty(element,
						targetKeyName);
				Object propertyValue = sourceMap.get(keyValue);

				PropertyUtils.setProperty(element, targetPropertyName,
						propertyValue);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void addProperty(List targetList, String targetKeyName,
			String targetPropertyName, Map<String, String> sourceMap,
			String sourceKeyName, String sourcePropertyName) {
		if (CollectionUtils.isEmpty(targetList) || MapUtils.isEmpty(sourceMap)) {
			return;
		}

		try {
			// ����Ŀ������.
			for (Iterator i = targetList.iterator(); i.hasNext();) {
				Object element = i.next();
				Object keyValue = PropertyUtils.getProperty(element,
						targetKeyName);
				Object propertyValue = sourceMap.get(keyValue);

				PropertyUtils.setProperty(element, targetPropertyName,
						propertyValue);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ȡ����.
	 * 
	 * @param list
	 * @param propertyName
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static List getProperties(List list, String propertyName) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.EMPTY_LIST;
		}

		Set result = new HashSet();
		try {
			for (Iterator i = list.iterator(); i.hasNext();) {
				Object value = PropertyUtils
						.getProperty(i.next(), propertyName);

				if (value != null) {
					result.add(value);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return new ArrayList(result);
	}
}
