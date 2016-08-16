package flink.util;

import java.math.BigDecimal;

/**
 * ���ְ�����
 * 
 * @
 * 
 */
public class NumberHelper {
	/**
	 * ����һ�����ı������
	 * 
	 * @param partQuatity
	 * @param totalQuatity
	 * @param total
	 * @return ������λС��
	 */
	public static Double getPart(Double partQuatity, Double totalQuatity, Double total) {
		BigDecimal result = BigDecimal.valueOf(total).multiply(BigDecimal.valueOf(partQuatity))
				.divide(BigDecimal.valueOf(totalQuatity));
		return result.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * ��ӣ����������������Ϊnull��Ϊnull��ȡĬ��ֵ0
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Long add(Long o1, Long o2) {
		if (o1 == null) {
			o1 = 0L;
		}
		if (o2 == null) {
			o2 = 0L;
		}
		return BigDecimal.valueOf(o1).add(BigDecimal.valueOf(o2)).longValue();
	}

	/**
	 * ��ӣ����������������Ϊnull��Ϊnull��ȡĬ��ֵ0
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Double add(Double o1, Double o2) {
		if (o1 == null) {
			o1 = 0.0;
		}
		if (o2 == null) {
			o2 = 0.0;
		}
		return BigDecimal.valueOf(o1).add(BigDecimal.valueOf(o2)).doubleValue();
	}
	
	/**
	 * ����������
	 * @param numbers
	 * @return
	 */
	public static Double add(Double[] numbers){
		Double result = 0d;
		for(Double d:numbers){
			result = add(result,d);
		}
		return result;
	}

	/**
	 * ��������������������Ϊnull��Ϊnull��ȡĬ��ֵ0
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Long sub(Long o1, Long o2) {
		if (o1 == null) {
			o1 = 0L;
		}
		if (o2 == null) {
			o2 = 0L;
		}

		return BigDecimal.valueOf(o1).subtract(BigDecimal.valueOf(o2)).longValue();
	}

	/**
	 * ��������������������Ϊnull��Ϊnull��ȡĬ��ֵ0
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Double sub(Double o1, Double o2) {
		if (o1 == null) {
			o1 = 0.0;
		}
		if (o2 == null) {
			o2 = 0.0;
		}

		return BigDecimal.valueOf(o1).subtract(BigDecimal.valueOf(o2)).doubleValue();
	}

}
