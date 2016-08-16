/**
 * @since Dec 3, 2009
 */
package flink.util;

import java.math.BigDecimal;

/**
 * ����.����λС��Ϊ����.
 * 
 *
 */
public abstract class AmountUtils {
	private static final double ZERO = 0.0001;
	private static final int SCALE = 2;

	/**
	 * �жϽ���Ƿ����, ����֮��С��ZERO ��Ϊ���.
	 * 
	 * <pre>
	 * AmountUtils.equals(null, null)	= false
	 * AmountUtils.equals(1, null)      = false
	 * AmountUtils.equals(null, 1)      = false
	 * AmountUtils.equals(2, 2)      	= true
	 * AmountUtils.equals(2, 2.001)     = false
	 * </pre>
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean equals(Double d1, Double d2) {
		if (d1 == null || d2 == null) {
			return false;
		}

		return Math.abs(d1 - d2) < ZERO;
	}

	/**
	 * ��������.
	 * 
	 * @param value
	 * @return
	 */
	public static double format(double value) {
		return new BigDecimal(Double.toString(value)).setScale(SCALE,
				BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �Ƚ�.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compare(double d1, double d2) {
		return new BigDecimal(Double.toString(d1)).setScale(SCALE,
				BigDecimal.ROUND_HALF_UP).compareTo(
				new BigDecimal(Double.toString(d2)).setScale(SCALE,
						BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * ����.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean gt(double d1, double d2) {
		return compare(d1, d2) > 0;
	}

	/**
	 * ����.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean et(double d1, double d2) {
		return compare(d1, d2) == 0;
	}

	/**
	 * ������.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean ne(double d1, double d2) {
		return compare(d1, d2) != 0;
	}

	/**
	 * С��.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean lt(double d1, double d2) {
		return compare(d1, d2) < 0;
	}

	/**
	 * ���ڵ���.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean ge(double d1, double d2) {
		return compare(d1, d2) >= 0;
	}

	/**
	 * С�ڵ���.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean le(double d1, double d2) {
		return compare(d1, d2) <= 0;
	}

	/**
	 * �ӷ�����, �����������.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double add(Double d1, Double d2) {
		return Double.valueOf(AmountUtils.format(d1.doubleValue()
				+ d2.doubleValue()));
	}

	/**
	 * ��ȷ�ӷ�����, �����������.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double eadd(Double d1, Double d2) {
		BigDecimal bd1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bd2 = new BigDecimal(String.valueOf(d2));

		return Double.valueOf(bd1.add(bd2).doubleValue());
	}

	/**
	 * ��������, �����������.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double subtract(Double d1, Double d2) {
		return Double.valueOf(AmountUtils.format(d1.doubleValue()
				- d2.doubleValue()));
	}

	/**
	 * ��ȷ��������, �����������.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double esubtract(Double d1, Double d2) {
		BigDecimal bd1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bd2 = new BigDecimal(String.valueOf(d2));

		return Double.valueOf(bd1.subtract(bd2).doubleValue());
	}
}
