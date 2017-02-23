package Data;
import java.io.*;
// 등록된 책 정보에 관한 데이터
public class BBBookData extends BBMainData{
	public int bb_no; // 책번호
	public String bb_name; // 책이름
	public String bb_writer; // 저자
	public String bb_com; // 출판사
	public String bb_ownerid; // 등록한 아이디(책소유자)
	public String bb_staus; // 책상태
	public int bb_rcode; // 예약코드
	public String bb_date; // 구입연월
	public String Bmonth; // 구입월
	public String Byear; // 구입연월
	public char	  bb_visibleYN; 


	@Override
	public String toString(){
		
		return bb_name;
	}
}
