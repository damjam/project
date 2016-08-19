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
public class ButtonHtmlNavigator implements HtmlNavigator {

	@Override
	public String getHtmlNavigation(String contextPath, Paginater p,
			int formIndex, boolean tidy) {
		String refreshEvent = "Paginater.toPage(" + formIndex + ","
				+ p.getCurrentPage() + ")";
		String refreshId = " id='_refresh_" + formIndex + "' ";

		StringBuffer html = new StringBuffer();
		html.append("<div class='pageNavigation'>");
		html.append("  <span class='pageSummary'>��" + p.getMaxRowCount()
				+ "����¼����" + p.getMaxPage() + "ҳ</span>");
		html.append("  <span>");
		html.append("    <input type='text' name='pageSize' id='pageSize_"
				+ formIndex + "' class='pageinputtext' value='"
				+ p.getPageSize() + "' onkeypress='Paginater.keyToPage("
				+ formIndex + ")'/>����¼��ҳ");
		html.append("    ��ǰ��<input type='text' name='pageNumber' id='goPageIndex_"
				+ formIndex
				+ "' class='pageinputtext' value='"
				+ p.getCurrentPage()
				+ "' onkeypress='Paginater.keyToPage("
				+ formIndex + ")' />ҳ");
		html.append("  </span>");

		html.append("<a href='javascript: " + refreshEvent + "'" + refreshId
				+ " style='display:none'>[ˢ��]</a>");

		if (p.getCurrentPage() == p.getFirstPage()) {
			html.append("��ҳ&nbsp;��һҳ");
		} else {
			html.append("<input type='button' class='pagenavbtn' onclick='Paginater.toPage("
					+ formIndex + "," + p.getFirstPage() + ")' value='��ҳ'/>");
			html.append("<input type='button' class='pagenavbtn' onclick='Paginater.toPage("
					+ formIndex + "," + p.getPriorPage() + ")' value='��һҳ'/>");
		}

		if (p.getCurrentPage() == p.getLastPage() || p.getLastPage() <= 1) {
			html.append("��һҳ&nbsp;ĩҳ");
		} else {
			html.append("<input type='button' class='pagenavbtn' onclick='Paginater.toPage("
					+ formIndex + "," + p.getNextPage() + ")' value='��һҳ'/>");
			html.append("<input type='button' class='pagenavbtn' onclick='Paginater.toPage("
					+ formIndex + "," + p.getLastPage() + ")' value='βҳ'/>");
		}

		html.append("</div>");

		return html.toString();
	}

}
