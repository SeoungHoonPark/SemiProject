package Data;
/*
 * 서버하고 통신할 데이터를 기억해고 실제 통신할 클래스 
 * 
 *  프로토콜 :
 *  
 *  클라이언트 => 서버 
 *  전체검색 -> 1101
 *  대출가능검색 -> 1102
 *  책이름검색 -> 1103
 *  내책검색 -> 1104
 *  
 */
import java.io.Serializable;  

import Data.BBBookData;
import Data.BBMemberData;
import Data.BBMsgData;
import java.util.ArrayList;
import java.util.Vector;
public class BBMainData implements Serializable {
	public int protocol;
	public boolean 	isSuccess;
	public BBBookData bookData;
	public BBMemberData memberData;
	public BBMsgData 	msgData;
	
	public Vector list;
	public Object[][] objectArray;
	public ArrayList	anyList;
	public ArrayList 	msgFromList;
	public ArrayList  	msgToList;

	
}


