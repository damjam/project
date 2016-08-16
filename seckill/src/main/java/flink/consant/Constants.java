package flink.consant;

public abstract class Constants {
	// �û�ȫ��Ȩ�޵�.
	public static final String USER_PRIVILEGE = "USER_PRIVILEGE";

	// Ȩ����Դ.
	public static final String USER_PRIVILEGE_RES = "USER_PRIVILEGE_RES";

	// �û�Ȩ�������������е�Ҷ�ӽڵ�
	public static final String USER_MENU = "USER_MENU";

	// �û��˵��б���ʾΪ��߲˵�ʹ�ã�������Ҷ�ӽڵ㡣
	public static final String USER_MENU_LIST = "USER_MENU_LIST";
	public static final String SESSION_USER = "SESSION_USER";
	public static final String PAGE_SIZE = "pageSize";
	public static final int DEFAULT_PAGE_SIZE = 20;

	// �ͻ�id
	public static final String SESSION_CUSTOM_ID = "SESSION_CUSTOM_ID";

	public static final String SESSION_CUSTOM = "SESSION_CUSTOM";

	public static final String INVEST_ACCT_NOS = "INVEST_ACCT_NOS";

	public static final String BRANCH_TYPE = "BRANCH_TYPE";

	public static final String BRANCH_TAG = "tag";
	
	public static final String BRANCH_NO = "BRANCH_NO";
	
	// ********************************************************************
	public static final String OPER_INFO = "msg";
	public static final String SAVE_SUCCESS = "����ɹ�";
	public static final String SAVE_FAIL = "����ʧ��";
	public static final String UPDATE_SUCCESS = "�޸ĳɹ�";
	public static final String UPDATE_FAIL = "�޸�ʧ��";
	public static final String DELETE_SUCCESS = "ɾ���ɹ�";
	public static final String DELETE_FAIL = "ɾ��ʧ��";
	// ********************************************************************

	// ******************************************************************
	// ϵͳ��־����
	public static final String LOG_SYS_INFO = "I"; // ��Ϣ
	public static final String LOG_SYS_WARNING = "W"; // ����
	public static final String LOG_SYS_ERROR = "E"; // ����
	public static final String LOG_SYS_TYPE = "I,W,E";

	// ϵͳ��־���
	public static final String LOG_SYS_S = "S"; // ����Ա��������
	public static final String LOG_SYS_U = "U"; // ҵ����Ա����
	public static final String LOG_SYS_CLASS = "S,U";

	// ϵͳ��־״̬
	public static final String LOG_SYS_STAT_VIEW = "00";
	public static final String LOG_SYS_STAT_NO_VIEW = "11";

	// �û���־����
	public static final String LOG_USER_S = "S"; // ��ѯ
	public static final String LOG_USER_A = "A"; // ����
	public static final String LOG_USER_U = "U"; // �޸�
	public static final String LOG_USER_D = "D"; // ɾ��
	public static final String LOG_USER_C = "C"; // ���
	public static final String LOG_USER_O = "O"; // ����
	public static final String LOG_USER_TYPE = "S,A,U,D,C,O";

	public static final String PRIVILEGE_RESOURCE_ID = "PRIVILEGE_RESOURCE_ID";
	public static final String ROLE_INFO_ID = "ROLE_INFO_ID";
	public static final String USER_LOG_ID = "USER_LOG_ID";
	public static final String NOTICE_MSG_ID = "NOTICE_MSG_ID";
	public static final String NOTICE_MSG_RECORD_ID = "NOTICE_MSG_RECORD_ID";
	public static final String PUSH_PLAN_ID = "PUSH_PLAN_ID";
	public static final String PUSH_RECORD_ID = "PUSH_RECORD_ID";
	public static final String CUST_INFO_ID = "CUST_INFO_ID";
	public static final String USER_INFO_ID = "USER_INFO_ID";
	public static final String ACCOUNT_JOURNAL_ID = "ACCOUNT_JOURNAL_ID";
	public static final String WATER_RECORD_ID = "WATER_RECORD_ID";
	public static final String OWNER_INFO_ID = "OWNER_INFO_ID";
	public static final String HOUSE_INFO_ID = "HOUSE_INFO_ID";
	public static final String BILL_ID = "BILL_ID";
	public static final String LIMIT_GROUP_INFO_ID = "LIMIT_GROUP_INFO_ID";
	public static final String ACCOUNT_DETAIL_ID = "ACCOUNT_DETAIL_ID";
	public static final String CONTACT_ID = "CONTACT_ID";
	public static final String MERCHANT_INFO_ID = "MERCHANT_INFO_ID";
	public static final String PARKING_INFO_ID = "PARKING_INFO_ID";
	public static final String CAR_INFO_ID = "CAR_INFO_ID";
	public static final String TIMER_ID = "TIMER_ID";
	public static final String BILL_TRACK_ID = "BILL_TRACK_ID";
	public static final String TIMER_DO_ID = "TIMER_DO_ID";
	public static final String CHARGE_PARAM_ID = "CHARGE_PARAM_ID";
	public static final String CHARGE_ITEM_ID = "CHARGE_ITEM_ID";
	public static final String SYSUSER = "system";
	public static final String[] RENT_BUILDINGS = { "" };
	public static final String INNER_ACCTID = "00000000";
	public static final String COM_NAME = "��Ӳ�����ҵ�������޹�˾����̲�ͷ�����";
	// ���ڵ�Ȩ�ޱ��.
	public static final String ROOT_PRIVILEGE_CODE = "00";

	public static final String YES = "Y";

	public static final String NO = "N";

	public static final String LEFT = "l";

	public static final String RIGHT = "r";

	public static final String CENTER = "c";
	/** �����ɹ��Ķ������� */
	public static final String OPEN_ACC_MOBILE_MSG = "�𾴵Ŀͻ������ѳɹ����������ڽ��ڵ��ӽ��������Ƴ��Ļƽ�Ͷҵ�񣬶�Ͷ�˺�Ϊ{?}��������ͨ���������к����ڽ��ڵ��ӽ������Ĺ����������Ķ�Ͷ�ƻ�����ѯ��ϸ���˻���Ϣ��";
	/** �����ɹ��Ķ������� */
	public static final String CANCEL_ACC_MOBILE_MSG = "�𾴵Ŀͻ������ѳɹ�ȡ�������ڽ��ڵ��ӽ��������Ƴ��Ļƽ�Ͷҵ��";
	/** ����ƻ���ɵĶ������� */
	public static final String DEPOSIT_MOBILE_MSG = "�𾴵Ŀͻ�������{?}��{?}��{?}�ճɹ�����{?}�˻ƽ𣬿ۿ���{?}Ԫ��Ŀǰ�����Ļƽ��˻����лƽ�{?}�ˣ��ʽ��˻����{?}Ԫ��";
	/** ʵ�������ɵĶ������� */
	public static final String TAKEOUT_MOBILE_MSG = "�𾴵Ŀͻ�������{?}��{?}��{?}�ճɹ����{?}�˻ƽ�ʵ����أ����ۿ���{?}Ԫ��Ŀǰ�����Ļƽ��˻����лƽ�{?}�ˣ��ʽ��˻����{?}Ԫ��������Լ���������Я���������֤����ָ������������������";
	/** �ֽ������ɵĶ������� */
	public static final String SOLD_MOBILE_MSG = "�𾴵Ŀͻ�������{?}��{?}��{?}�ճɹ����{?}�˻ƽ��ֽ���أ����ۿ������Ѻ�ķ������Ϊ{?}Ԫ��Ŀǰ�����Ļƽ��˻����лƽ�{?}�ˣ��ʽ��˻����{?}Ԫ���ֽ���ص��ʽ�������������ڷ������������п���";
	/** ��ҵ�Ѵ߿�֪ͨ */
	public static final String SERVICE_BILL_DUE_MSG = "�𾴵�ҵ���������ҵ�ѽ���{?}���ڣ�Ϊ�˸��õ�Ϊ�����������ڽ��ڽ�����ҵ��";
}
