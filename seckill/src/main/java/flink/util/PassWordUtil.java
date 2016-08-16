package flink.util;

import java.util.Random;

/**
 * �������������
 * 
 * @
 * 
 */
public class PassWordUtil {

	/**
	 * ����һ��6λ�������
	 * 
	 * @return
	 */
	public static String genernatePassword() {
		return genernatePassword(6);
	}

	/**
	 * ��������ַ���
	 * 
	 * @param length
	 * @return
	 */
	public static String genernatePassword(int length) {
		StringBuffer buffer = new StringBuffer(
				"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(genernatePassword());
	}

}
