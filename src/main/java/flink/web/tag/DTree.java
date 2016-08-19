package flink.web.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;

public class DTree extends SimpleTagSupport {

	private String jsSrc = "/dtree/wtree.js";

	private String imgSrc = "/dtree/images/system/menu/";

	private String cssSrc = "/dtree/dtree.css";

	private String displayDivId;

	private String target = null;

	private boolean folderLinks = true;

	private boolean useSelection = true;

	private boolean useCookies = true;

	private boolean userLines = true;

	private boolean useIcons = true;

	private boolean useStatusText = false;

	private boolean closeSameLevel = false;

	private boolean inOrder = false;

	private boolean check = false;

	private String dataListName;

	private boolean buffer;

	private String initCheckDataName = "initCheck";

	/**
	 * ��ȡJS��·��
	 * 
	 * @return
	 */
	public String getJsSrc() {
		return jsSrc;
	}

	/**
	 * ����JS��·��
	 * 
	 * @param jsSrc
	 */
	public void setJsSrc(String jsSrc) {
		this.jsSrc = jsSrc;
	}

	/**
	 * ��ȡͼƬ·��
	 * 
	 * @return
	 */
	public String getImgSrc() {
		return imgSrc;
	}

	/**
	 * ����ͼƬ·��
	 * 
	 * @param imgSrc
	 */
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	/**
	 * ��ȡ����ʾ��DIV
	 * 
	 * @return
	 */
	public String getDisplayDivId() {
		return displayDivId;
	}

	/**
	 * ��������ʾ��DIV
	 * 
	 * @param displayDivId
	 */
	public void setDisplayDivId(String displayDivId) {
		this.displayDivId = displayDivId;
	}

	/**
	 * ��ȡ��λ��
	 * 
	 * @return
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * ���ô�λ��
	 * 
	 * @param target
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * ��ȡ�Ƿ��г�ʼ��ֵ
	 * 
	 * @return
	 */
	public boolean isUseSelection() {
		return useSelection;
	}

	/**
	 * �����Ƿ��г�ʼ��ֵ
	 * 
	 * @param useSelection
	 */
	public void setUseSelection(boolean useSelection) {
		this.useSelection = useSelection;
	}

	/**
	 * ��ȡCOOKIES
	 * 
	 * @return
	 */
	public boolean isUseCookies() {
		return useCookies;
	}

	/**
	 * ����cookies
	 * 
	 * @param useCookies
	 */
	public void setUseCookies(boolean useCookies) {
		this.useCookies = useCookies;
	}

	/**
	 * ��ȡ�Ƿ�ʹ��ͼƬ
	 * 
	 * @return
	 */
	public boolean isUseIcons() {
		return useIcons;
	}

	/**
	 * �����Ƿ�ʹ��ͼƬ
	 * 
	 * @param useIcons
	 */
	public void setUseIcons(boolean useIcons) {
		this.useIcons = useIcons;
	}

	/**
	 * �Ƿ�ʹ��״̬�ı�
	 * 
	 * @return
	 */
	public boolean isUseStatusText() {
		return useStatusText;
	}

	/**
	 * �����Ƿ�ʹ�þ�̬�ı�
	 * 
	 * @param useStatusText
	 */
	public void setUseStatusText(boolean useStatusText) {
		this.useStatusText = useStatusText;
	}

	/**
	 * ��ȡ�Ƿ�ر�ͬһ��
	 * 
	 * @return
	 */
	public boolean isCloseSameLevel() {
		return closeSameLevel;
	}

	/**
	 * �����Ƿ�ر�ͬһ��
	 * 
	 * @param closeSameLevel
	 */
	public void setCloseSameLevel(boolean closeSameLevel) {
		this.closeSameLevel = closeSameLevel;
	}

	/**
	 * �Ƿ�����
	 * 
	 * @return
	 */
	public boolean isInOrder() {
		return inOrder;
	}

	/**
	 * �����Ƿ�����
	 * 
	 * @param inOrder
	 */
	public void setInOrder(boolean inOrder) {
		this.inOrder = inOrder;
	}

	/**
	 * ��ȡ�Ƿ��и�ѡ��
	 * 
	 * @return
	 */
	public boolean isCheck() {
		return check;
	}

	/**
	 * �����Ƿ��и�ѡ��
	 * 
	 * @param check
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * ��ȡ�Ƿ��г�����
	 * 
	 * @return
	 */
	public boolean isFolderLinks() {
		return folderLinks;
	}

	/**
	 * �����Ƿ��г�����
	 * 
	 * @param folderLinks
	 */
	public void setFolderLinks(boolean folderLinks) {
		this.folderLinks = folderLinks;
	}

	/**
	 * ��ȡ�Ƿ����»���
	 * 
	 * @return
	 */
	public boolean isUserLines() {
		return userLines;
	}

	/**
	 * �����Ƿ����»���
	 * 
	 * @param userLines
	 */
	public void setUserLines(boolean userLines) {
		this.userLines = userLines;
	}

	/**
	 * ��ȡ���νṹ���ݴ��request����
	 * 
	 * @return
	 */
	public String getDataListName() {
		return dataListName;
	}

	/**
	 * �������ݴ��ʱ��request����
	 * 
	 * @param dataListName
	 */
	public void setDataListName(String dataListName) {
		this.dataListName = dataListName;
	}

	/**
	 * ��ȡ�Ƿ����ͼƬ����Ч��
	 * 
	 * @return
	 */
	public boolean isBuffer() {
		return buffer;
	}

	/**
	 * �����Ƿ����ͼƬ����Ч��
	 * 
	 * @param buffer
	 */
	public void setBuffer(boolean buffer) {
		this.buffer = buffer;
	}

	/**
	 * ��ȡ���ó�ʼ���ڵ�ID��request�������
	 * 
	 * @return
	 */
	public String getInitCheckDataName() {
		return initCheckDataName;
	}

	/**
	 * ��ȡ��ʽ����
	 * 
	 * @return
	 */
	public String getCssSrc() {
		return cssSrc;
	}

	/**
	 * ������ʽ
	 * 
	 * @param cssSrc
	 */
	public void setCssSrc(String cssSrc) {
		this.cssSrc = cssSrc;
	}

	/**
	 * ���ó�ʼ���ڵ�ID��request�������
	 * 
	 * @param initCheckDataName
	 */
	public void setInitCheckDataName(String initCheckDataName) {
		this.initCheckDataName = initCheckDataName;
	}

	@Override
	public void doTag() throws JspException, IOException {

		PageContext pageContext = (PageContext) super.getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		String contextPath = request.getContextPath();
		JspWriter out = pageContext.getOut();

		Object data = request.getAttribute(this.dataListName);
		if (null == data) {
			out.print("û������");
		} else {

			// �Ȼ���ͷ��
			StringBuffer imageLoading = new StringBuffer();
			imageLoading.append("<script type='text/javascript'>");
			imageLoading.append(" document.getElementById('"
					+ this.getDisplayDivId() + "').innerHTML=LOAD_IMAGE;");
			imageLoading.append("</script>");
			out.print(imageLoading);

			// ��ʽ����
			StringBuffer styleLoading = new StringBuffer();
			styleLoading.append("<link rel='stylesheet' type='text/css' href="
					+ contextPath + this.getCssSrc() + "/>");

			StringBuffer sb = new StringBuffer();
			sb.append(" <script src='" + contextPath + this.getJsSrc()
					+ "' type='text/javascript' ></script> ");
			sb.append(" <script type='text/javascript'>");
			sb.append(" var d = new dTree('d','" + contextPath
					+ this.getImgSrc() + "');");
			initDtreeConfig(sb);
			initDtreeNode(sb, (List<DTreeObj>) data);
			createBufferFunction(sb, request);
			this.showDtree(sb);
			sb.append(" </script> ");

			out.print(sb);
		}

		super.doTag();
	}

	/**
	 * ��ʼ��ѡ�����ڵ�
	 * 
	 * @param sb
	 */
	private void initDtree(StringBuffer sb, HttpServletRequest request) {

		String initCheckIds = (String) request
				.getAttribute(this.initCheckDataName);
		if (null == initCheckIds || initCheckIds.length() == 0) {
			return;
		}

		sb.append("d.initCheckBoxNodes('" + initCheckIds + "');d.openAll();");

	}

	private void createBufferFunction(StringBuffer sb,
			HttpServletRequest request) {

		sb.append("function buf(){");
		sb.append("document.getElementById('" + this.displayDivId
				+ "').innerHTML=d;");
		this.initDtree(sb, request);
		sb.append("}");

	}

	private void showDtree(StringBuffer sb) {
		if (buffer) {
			sb.append("setTimeout('buf()',3000);");
		} else {
			sb.append("buf();");
		}

		sb.append("d.closeAll();");
	}

	private void initDtreeNode(StringBuffer sb, List<DTreeObj> data) {

		for (int i = 0; i < data.size(); i++) {
			DTreeObj dTreeObj = data.get(i);
			sb.append(" d.add(");
			sb.append("'" + dTreeObj.getNodeId() + "',");
			sb.append("'" + dTreeObj.getNodePid() + "',");
			sb.append("'" + dTreeObj.getNodeLabel() + "',");
			sb.append("'" + dTreeObj.getNodeUrl() + "',");
			sb.append("'" + dTreeObj.getNodeLabel() + "',");
			sb.append("'" + dTreeObj.getNodeTarget() + "',");
			sb.append("'" + dTreeObj.getNodeName() + "',");
			sb.append("'" + dTreeObj.getNodeValue() + "',");
			sb.append("" + dTreeObj.getNodeIcon() + ",");
			sb.append("" + dTreeObj.isNodeIconOpen() + ",");
			sb.append("" + dTreeObj.isOpen() + "");
			sb.append(");");
		}
	}

	private void initDtreeConfig(StringBuffer sb) {

		if (!StringUtils.isEmpty(this.getTarget())) {
			sb.append(" d.config.target=" + this.getTarget() + ";");
		}

		if (!this.isFolderLinks()) {
			sb.append(" d.config.folderLinks= false ;");
		}

		if (!this.isUseSelection()) {
			sb.append(" d.config.useSelection = false ;");
		}

		if (!this.isUseCookies()) {
			sb.append(" d.config.useCookies = false ;");
		}

		if (!this.isUserLines()) {
			sb.append(" d.config.useLines = false ;");
		}

		if (!this.isUseIcons()) {
			sb.append(" d.config.useIcons = false ;");
		}

		if (this.isUseStatusText()) {
			sb.append(" d.config.useStatusText = true ;");
		}

		if (this.isCloseSameLevel()) {
		}
		{
			sb.append(" d.config.closeSameLevel = true ;");
		}

		if (this.isCheck()) {
			sb.append(" d.config.check = true ;");
		}
	}

}
