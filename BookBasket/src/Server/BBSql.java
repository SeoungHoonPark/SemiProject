package Server;

// sql 모음
public class BBSql {
	
	// 상수를 문자로 구별하므로 가독성을 높이기 위한 처리
	// 로그인 관련 상수
	public static final int selectLogin 		= 1101;
	
	
	// 쪽지 관련 상수
	public static final int selectFromListMsg = 1401;
	public static final int selectToListMst = 1402;
	public static final int selectDetailtMsg = 1403;
	
	public static String getSQL(int code){
		
		String sql = "";
		switch(code){
		case	selectLogin:	//로그인 처리
			sql = "select BM_NO, BM_ID, BM_NAME, BM_PHONE from B_MEMBER where bm_id = ? and bm_pw = ?";
			break;
		case selectFromListMsg:			//받은 쪽지 리스트 처리
			sql = "SELECT MS_NO, MS_SENDID, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_RECEIVEID=?";
			break;
		case selectToListMst:				//전달한 쪽지 리스트 처리
			sql = "SELECT MS_NO, MS_RECEIVEID, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_SENDID=?";
			break;
		case selectDetailtMsg:			// 선택한 줄에 대한 쪽지 상세보기 처리
			sql = "SELECT MS_NO, MS_SENDID, MS_RECEIVEID, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_NO=?";
		}
		System.out.println("sql = " + sql);
		return sql;
	}
}
