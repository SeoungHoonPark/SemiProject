package Data;
/*
 * 	서버하고 통신할 데이터를 기억해고 실제 통신할 클래스
 */
import		java.io.*;
import java.util.ArrayList;
public class BBMainData implements Serializable {
	
	public int protocol;
	public boolean 	isSuccess;
	
	// 어쪽에 회원정보, 책 정보, 쪽지.. 등등의 데이터빈 형태로 추가
	public BBMemberData memberData;
	public BBMsgData msgData;
	
	public ArrayList	anyList;
	public ArrayList 	msgFromList;
	public ArrayList  	msgToList;
}
