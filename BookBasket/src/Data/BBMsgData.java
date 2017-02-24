package Data;
/**
 * 메세지(쪽지) 관련 데이터 빈 클래스
 * @author 
 * @since 2017 02 21
 */
public class BBMsgData extends BBMainData{
	
	public int no;						// 퀄럼명 : ms_no 					== 쪽지 일련번호
	public String sendId;			// 퀄럼명 : MS_SENDID			== 보낸이 아이디
	public String receiveId;		// 퀄럼명 : MS_RECEIVEID		== 받은이 아이디
	public int bookNo;			// 퀄럼명 : ms_br_bno				== 예약 책 번호
	public String bookName;		//	B_Book.bb_name				== 예약 책 이름
	public String msDate;			// 퀄럼명 : MS_DATE				== 쪽지 등록일
	public String msTxt;				// 퀄럼명 : MS_TEXT				== 예약 내용
	public String check;		// 퀄럼명 : MS_CHECK				== 읽은 여부
	
	// primary key로 사용하는 변수 getter 함수로 만듬
	public int getNo(){
		return  this.no;
	}

	@Override
	public String toString() {
		return "BBMsgData [no=" + no + ", sendId=" + sendId + ", receiveId=" + receiveId + ", bookNo=" + bookNo
				+ ", bookName=" + bookName + ", msDate=" + msDate + ", msTxt=" + msTxt + ", check=" + check + "]";
	}
}

