package flink.util;

import org.apache.commons.lang.StringUtils;

/**
 * ��֤�ַ����ͺͳ���,��ʹ������.
 * 
 * 
 * 
 */
public abstract class Validator {
	private static final String INTERPUNCTION = ",.-=_+()[]{}";
	private static final String INVALID_INTERPUNCTION = "'</>&;";

	/**
	 * FIXME
	 * 
	 * @param s
	 * @return
	 */
	public static boolean hasChinese(String s) {
		if (StringUtils.isEmpty(s)) {
			return false;
		}

		return s.getBytes().length != s.length();
	}

	/**
	 * ������Ч���.
	 * 
	 * @param s
	 * @return
	 */
	private static boolean includeInvalidInterpunction(String s) {
		for (int i = 0, len = s.length(); i < len; i++) {
			char ch = s.charAt(i);

			if (INVALID_INTERPUNCTION.indexOf(ch) > -1) {
				return true;
			}
		}

		return false;
	}

	public static boolean isValid(String s, int charType, int length) {
		if (s == null || s.length() > length) {
			return false;
		}

		if (CharType.N == charType) {
			return s.length() == 0 ? false : StringUtils.isNumeric(s);
		} else if (CharType.L == charType) {
			return StringUtils.isAlpha(s);
		} else if (CharType.I == charType) {
			return validateInterpunction(s);
		} else if (CharType.N_L == charType) {
			return validateLetterOrDigit(s);
		} else if (CharType.N_I == charType) {
			return validateDigitOrInterpunction(s);
		} else if (CharType.L_I == charType) {
			return validateLetterOrInterpunction(s);
		} else if (CharType.N_L_I == charType) {
			return validateDigitOrLetterOrInterpunction(s);
		} else if (CharType.ALL == charType) {
			return !includeInvalidInterpunction(s);
		}

		return false;
	}

	public static void main(String[] args) {
		// System.out.println(Character.getType('a'));
		// System.out.println(Character.getType('z'));
		// System.out.println(Character.getType('A'));
		// System.out.println(Character.getType('Z'));
		// System.out.println(Character.getType(','));
		// System.out.println(Character.getType('��'));
		System.out.println(isValid("!@", CharType.ALL, 2));
	}

	/**
	 * ��֤���ֻ���.
	 * 
	 * @param s
	 * @return
	 */
	private static boolean validateDigitOrInterpunction(String s) {
		for (int i = 0, len = s.length(); i < len; i++) {
			char ch = s.charAt(i);

			if (INTERPUNCTION.indexOf(ch) == -1 && !Character.isDigit(ch)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ��֤����, ��ĸ����.
	 * 
	 * @param s
	 * @return
	 */
	private static boolean validateDigitOrLetterOrInterpunction(String s) {
		for (int i = 0, len = s.length(); i < len; i++) {
			char ch = s.charAt(i);

			if (INTERPUNCTION.indexOf(ch) == -1 && !Character.isLetter(ch)
					&& !Character.isDigit(ch)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ��֤���.
	 * 
	 * @param s
	 * @return
	 */
	private static boolean validateInterpunction(String s) {
		for (int i = 0, len = s.length(); i < len; i++) {
			if (INTERPUNCTION.indexOf(s.charAt(i)) == -1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ��֤��ĸ������.
	 * 
	 * @param s
	 * @return
	 */
	private static boolean validateLetterOrDigit(String s) {
		for (int i = 0, len = s.length(); i < len; i++) {
			char ch = s.charAt(i);

			if (!Character.isDigit(ch) && !Character.isUpperCase(ch)
					&& !Character.isLowerCase(ch)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ��֤��ĸ����.
	 * 
	 * @param s
	 * @return
	 */
	private static boolean validateLetterOrInterpunction(String s) {
		for (int i = 0, len = s.length(); i < len; i++) {
			char ch = s.charAt(i);

			if (INTERPUNCTION.indexOf(ch) == -1 && !Character.isLetter(ch)) {
				return false;
			}
		}

		return true;
	}
}
