package flink.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;

/**
 * Title: BaseDAO
 * Description: 
 * 
 */
public interface BaseDao {
	
	/**
	 * description: �������.
	 * 
	 * @param object
	 * @return
	 * 
	 * 
	 * @since 2007-11-25
	 */
	Serializable save(Object entity);
	Serializable save(Object entity, boolean flush, boolean clear);
	
	/**
	 * description: �������.
	 * 
	 * @param object
	 * @return
	 * 
	 * 
	 * @since 2007-11-25
	 */
	void saveOrUpdate(Object entity);

	/**
	 * description: ���¶���.
	 * 
	 * @param object
	 * @return
	 * 
	 * 
	 * @since 2007-11-26
	 */
	void update(Object entity);

	/**
	 * description: ɾ������.
	 * 
	 * @param object
	 * @return
	 * 
	 * 
	 * @since 2007-11-26
	 */
	void delete(Object entity);
	
	/**
	 * description: ɾ������.
	 * 
	 * @param id
	 * @return
	 * 
	 * 
	 * @since 2007-11-26
	 */
	void deleteById(Serializable id);
	
	/**
	 * description: ���Ҷ���.
	 * 
	 * @param id
	 * @return
	 * 
	 * 
	 * @since 2007-11-27
	 */
	<E> E findById(Serializable id);
	<E> E findById(Class model, Serializable id);
	
	/**
	 * ��ѯ������.
	 * 
	 * @param id
	 * @return
	 */
	<E> E findByIdWithLock(Serializable id);
	
	/**
	 * ��ѯ������.
	 * 
	 * @param id
	 * @return
	 */
	<E> E findByIdWithLock(Serializable id, Class model);
	
	/**
	 * ��ѯ������.
	 * 
	 * @param id
	 * @param wait �Ƿ�ȴ�, ���ȴ����������������������򷵻ؿ�ֵ.
	 * @return
	 */
	<E> E findByIdWithLock(Serializable id, boolean wait);
	
	/**
	 * ��ѯ������.
	 * 
	 * @param id
	 * @return
	 */
	<E> E findByIdWithLock(Serializable id, Class model, boolean wait);
	
	/**
	 * description: ���Ҷ���.
	 * 
	 * @return
	 * 
	 * 
	 * @since 2007-11-27
	 */
	List findAll();
	
	List findAll(Class clazz);
	
	List findAll(String orderProperty, String orderType);
	
	/**
	 * ������.
	 */
	void clear();
	void flush();
	void flushAndClear();
	void refresh(Object entity);
	void evict(Object entity);
	
	void lock(Object entity, LockMode lockMode);
	
	Long findCountByParam(Class<?> clazz, Map<String, Object> params, String idKey, Object idValue);
	
	Object getUniqueResult(Class clazz, String property, Object value);
	
	Object getUniqueResult(Class clazz, Map<String, Object> params);
	
	List<?> getResults(String property, Object value);
}
