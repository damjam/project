package flink.web.tag;

import flink.util.Paginater;

/**
 * classical html navigator.
 * 
 * <pre>
 * [��ҳ][��һҳ][��һҳ][ĩҳ]
 * </pre>
 * 
 */
public class ClassicalHtmlNavigator implements HtmlNavigator {

	@Override
	public String getHtmlNavigation(String contextPath, Paginater p,
			int formIndex, boolean tidy) {
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"pageNavigation\">");
		String refreshEvent = "Paginater.toPage(" + formIndex + ","
				+ p.getCurrentPage() + ")";
		String refreshId = " id=\"_refresh_" + formIndex + "\" ";

		if (tidy) {
			html.append("<a href=\"javascript: " + refreshEvent + "\""
					+ refreshId + " style=\"display:none\">[ˢ��]</a>");

			if (p.getCurrentPage() != p.getFirstPage()) {
				html.append("<a href=\"javascript: Paginater.toPage("
						+ formIndex + "," + p.getFirstPage() + ")\">[��ҳ]</a>");
				html.append("<a href=\"javascript: Paginater.toPage("
						+ formIndex + "," + p.getPriorPage() + ")\">[��һҳ]</a>");
			}

			if (p.getCurrentPage() != p.getLastPage()) {
				html.append("<a href=\"javascript: Paginater.toPage("
						+ formIndex + "," + p.getNextPage() + ")\">[��һҳ]</a>");
				html.append("<a href=\"javascript: Paginater.toPage("
						+ formIndex + "," + p.getLastPage() + ")\">[ĩҳ]</a>");
			}
		} else {
			html.append("<a href=\"javascript: " + refreshEvent + "\""
					+ refreshId + ">[ˢ��]</a>");

			if (p.getCurrentPage() == p.getFirstPage()) {
				html.append("[��ҳ][��һҳ]");
			} else {
				html.append("<a href=\"javascript: Paginater.toPage("
						+ formIndex + "," + p.getFirstPage() + ")\">[��ҳ]</a>");
				html.append("<a href=\"javascript: Paginater.toPage("
						+ formIndex + "," + p.getPriorPage() + ")\">[��һҳ]</a>");
			}

			if (p.getCurrentPage() == p.getLastPage()) {
				html.append("[��һҳ][ĩҳ]");
			} else {
				html.append("<a href=\"javascript: Paginater.toPage("
						+ formIndex + "," + p.getNextPage() + ")\">[��һҳ]</a>");
				html.append("<a href=\"javascript: Paginater.toPage("
						+ formIndex + "," + p.getLastPage() + ")\">[ĩҳ]</a>");
			}
		}

		html.append("&nbsp;&nbsp;ת����<input type=\"text\" id=\"goPageIndex_"
				+ formIndex + "\" size=\"3\" class=\"pageinputtext\" />ҳ");
		html.append("<img src=\"" + contextPath
				+ "/images/jumpto.bmp\" onclick=\"Paginater.goPage("
				+ formIndex + ")\" style=\"cursor: hand\" />");

		html.append("</div>");

		return html.toString();
	}

}
