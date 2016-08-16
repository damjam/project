package flink.util;

import java.util.Collection;

/**
 * Ȩ�޽ӿ�.
 * 
 * 
 */
public interface IPrivilege {
	/**
	 * ��ȡ���е��ӽڵ�
	 * 
	 * @return
	 */
	public Collection<IPrivilege> getAllChildren();

	/**
	 * Ȩ�ޱ��.
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * Ȩ�����.
	 * 
	 * @return
	 */
	String getEntry();

	/**
	 * Ȩ������.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * ����Ȩ�ޱ��.
	 * 
	 * @return
	 */
	String getParent();

	/**
	 * �Ƿ��ǲ˵�����Ŀ¼����true������Ҷ�ӣ�����false��
	 * 
	 * @return
	 */
	public boolean isMenu();

	/**
	 * ��Ȩ�޼���.
	 * 
	 * @param children
	 */
	void setChildren(Collection<IPrivilege> children);

	void setCode(String code);

}
