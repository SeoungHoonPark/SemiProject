package Server;

// sql 모음
public class BBSql {
	
	// 상수를 문자로 구별하므로 가독성을 높이기 위한 처리
	// 로그인 관련 상수
	public static final int selectLogin 		= 1101;
	
	
	// 쪽지 관련 상수
	public static final int selectFromListMsg = 1401;
	public static final int selectToListMst = 1402;
	
	public static String getSQL(int code){
		
		String sql = "";
		// 아래를 메시지(쪽지)-- 데이터 입력 쿼리 --
		//insert into b_msg values((select nvl(max(ms_no),0)+1 from b_msg),'aa','bb',11,sysdate,'책예약함','N');
		
		switch(code){
		case	selectLogin:	//로그인 처리
			sql = "select BM_NO, BM_ID, BM_NAME, BM_PHONE from B_MEMBER where bm_id = ? and bm_pw = ?";
			break;
		case selectFromListMsg:			//받은 쪽지 리스트 처리
			sql = "SELECT MS_NO, MS_SENDID, BR_NO, MS_TEXT, MS_DATE FROM B_MSG WHERE MS_RECEIVEID=?";
			break;
		case selectToListMst:				//전달한 쪽지 리스트 처리
			sql = "SELECT MS_NO, MS_SENDID, BR_NO,  MS_TEXT, MS_DATE FROM B_MSG WHERE MS_SENDID=?";
			break;
		}
		return sql;
	}
}
