package flink.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * web ��Դ.
 * 
 * 
 */
public abstract class WebResource {

	/**
	 * ��һ����������.
	 *
	 * @return
	 */
	public static final String PARAM_NAME = "action";

	/**
	 * �����������ȡlink.
	 * 
	 * @param request
	 * @return
	 */
	public static String getLink(HttpServletRequest request) {
		String url = request.getRequestURI().substring(request.getContextPath().length());
		String param = request.getParameter(WebResource.PARAM_NAME);

		if (StringUtils.isNotEmpty(param)) {
			url = getLink(url, param);
		}

		return url;
	}

	/**
	 * ��url��paramƴ��.
	 *
	 * @return
	 */
	public static String getLink(String url, String param) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		StringBuffer key = new StringBuffer();
		key.append(url);

		if (StringUtils.isNotEmpty(param)) {
			key.append("?");

			key.append(PARAM_NAME);
			key.append("=");
			key.append(param);
		}

		return key.toString();
	}

	/**
	 * ����Դ�б�ַ���map��.
	 *
	 * @param resources
	 * @return (key: url��paramƴ�ӵ�����, value:WebResource)
	 */
	public static Map<String, WebResource> map(List<WebResource> resources) {
		Map<String,WebResource> map = new HashMap<String,WebResource>();

		for (WebResource res : resources) {
			map.put(res.getLink(), res);
		}

		return map;
	}

	/**
	 * ��url��paramƴ��.
	 *
	 * @return
	 */
	public String getLink() {
		return getLink(getUrl(), getParamValue());
	}

	/**
	 * ��ȡ����ֵ.
	 *
	 * @return
	 */
	public abstract String getParamValue();

	/**
	 * ��ȡurl��ַ.
	 *
	 * @return
	 */
	public abstract String getUrl();

}
