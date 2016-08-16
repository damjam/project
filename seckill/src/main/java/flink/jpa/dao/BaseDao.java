package flink.jpa.dao;

import java.io.Serializable;

import javax.persistence.LockModeType;
import javax.persistence.Query;

public interface BaseDao<T, ID extends Serializable> extends Serializable {

	/**
	 * ����ʵ�����
	 * 
	 * @param id
	 *            ID
	 * @return ʵ��������������򷵻�null
	 */
	T find(ID id);

	/**
	 * ����ʵ�����
	 * 
	 * @param id
	 *            ID
	 * @param lockModeType
	 *            ������ʽ
	 * @return ʵ��������������򷵻�null
	 */
	T find(ID id, LockModeType lockModeType);

	/**
	 * �־û�ʵ�����
	 * 
	 * @param entity
	 *            ʵ�����
	 */
	void persist(T entity);

	/**
	 * �ϲ�ʵ�����
	 * 
	 * @param entity
	 *            ʵ�����
	 * @return ʵ�����
	 */
	T merge(T entity);

	/**
	 * �Ƴ�ʵ�����
	 * 
	 * @param entity
	 *            ʵ�����
	 */
	void remove(T entity);

	/**
	 * ִ��JPAԭ��sql��ѯ
	 * 
	 * @param sql
	 */
	public Query createNativeQuery(String sql);

	/**
	 * ˢ��ʵ�����
	 * 
	 * @param entity
	 *            ʵ�����
	 */
	void refresh(T entity);

	/**
	 * ˢ��ʵ�����
	 * 
	 * @param entity
	 *            ʵ�����
	 * @param lockModeType
	 *            ������ʽ
	 */
	void refresh(T entity, LockModeType lockModeType);

	/**
	 * ��ȡʵ�����ID
	 * 
	 * @param entity
	 *            ʵ�����
	 * @return ʵ�����ID
	 */
	ID getIdentifier(T entity);

	/**
	 * �ж��Ƿ�Ϊ�й�״̬
	 * 
	 * @param entity
	 *            ʵ�����
	 * @return �Ƿ�Ϊ�й�״̬
	 */
	boolean isManaged(T entity);

	/**
	 * ����Ϊ����״̬
	 * 
	 * @param entity
	 *            ʵ�����
	 */
	void detach(T entity);

	/**
	 * ����ʵ�����
	 * 
	 * @param entity
	 *            ʵ�����
	 * @param lockModeType
	 *            ������ʽ
	 */
	void lock(T entity, LockModeType lockModeType);

	/**
	 * �������
	 */
	void clear();

	/**
	 * ͬ������
	 */
	void flush();
}