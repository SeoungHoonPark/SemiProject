package Data;
/*
 * 	서버하고 통신할 데이터를 기억하고 실제 통신할 클래스
 */
import		java.io.*;
import java.util.ArrayList;
import java.util.Vector;
public class BBMainData implements Serializable {
	public int protocol;
	public boolean isSuccess;
	public BBBookData bookData; // 등록된 책 정보
	public BBMemberData memberData;
	public BBMsgData msgData;
	
	public Vector list;
	public Object[][] objectArray;
	public ArrayList	anyList;
	public ArrayList 	msgFromList;
	public ArrayList  	msgToList;
}
