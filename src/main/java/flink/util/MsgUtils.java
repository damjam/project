package flink.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ��Ϣ����.
 * 
 * 
 */
public abstract class MsgUtils {
	private static final String REGEX = "\\{\\?\\}";

	/**
	 * �滻��־�����еĴ��ַ�,��ʽΪ{?}....
	 * 
	 * @param log
	 * @param param
	 * @return
	 */
	public static String r(String msg, Object... params) {
		if (StringUtils.isBlank(msg) || ArrayUtils.isEmpty(params)) {
			return msg;
		}

		for (int i = 0, n = params.length; i < n; i++) {
			msg = msg.replaceFirst(REGEX, ObjectUtils.toString(params[i]));
		}

		return msg;
	}
}
