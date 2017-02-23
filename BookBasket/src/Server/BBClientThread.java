package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Data.BBBookData;
import Data.BBMainData;

/*
 * 	실제로 클라이언트하고 통신하는 클래스
 */
public class BBClientThread extends Thread {

	public BBMainServer main;
	public Socket 			socket;
	public ObjectInputStream 	oin;
	public ObjectOutputStream	oout;

	
	public BBClientThread(BBMainServer m, Socket s) throws Exception{
		main = m;
		socket = s;

		oout = new ObjectOutputStream(socket.getOutputStream());
		oin = new ObjectInputStream(s.getInputStream());
	}
	
	void searchAllProc(){
		
		BBBookData bookD = new BBBookData();
		
		Object[][] obj = null;
		try{	
			main.rs = main.pstmtTotalSearch.executeQuery();
			main.rs.last();
			int row = main.rs.getRow();
			main.rs.beforeFirst();
			System.out.println("rs row 길이" + row);
			
			int c=0;
			
			
			obj = new Object[row][8];
			while(main.rs.next()==true){
				obj[c][0]=main.rs.getInt("bb_no");
				obj[c][1]=main.rs.getString("bb_name");
				obj[c][2]=main.rs.getString("bb_writer");
				obj[c][3]=main.rs.getString("bb_ownerid");
				obj[c][4]=main.rs.getString("bb_status");
				obj[c][5]=main.rs.getInt("bb_rcode");
				obj[c][6]=main.rs.getString("bb_date");
				c++;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("bbclientthread 전체검색함수 예외");
		}finally {
			main.db.close(main.rs);
		}
		BBMainData mainD = new BBMainData();
		mainD.protocol = 2201;
	//	mainD.list = list;
		mainD.objectArray = obj;
		
		System.out.println("전체검색 응답프로토콜 :"+mainD.protocol);
		
		try {
			oout.writeObject(mainD);
			System.out.println(mainD.protocol);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("전체검색 함수 실행성공");
	}	
	
	// 책 등록정보 데이터 받기(예송)
	void bookRegister(BBMainData data){
		//System.out.println("서버로 넘어온 데이터 : "+data.bookData.bb_name);
		main.dao.bookRegister(data.bookData);
		data.protocol = 2202;
		this.sendData(data);
	}
	// 책 수정 데이터 받기 
	void bookModify(BBMainData data){
		main.dao.bookModify(data.bookData);
		data.protocol = 2203;
		this.sendData(data);
		
	}
	//	메시지 데이터 받기(예송)
	void MessageInsertUser(BBMainData data){
		main.dao.MessageInsertUser(data.msgData);
		data.protocol = 2403;
		this.sendData(data);
	}	
	
	
	void loginProcess(BBMainData data){
		System.out.println("서버로 넘어온 데이터 : " + data.memberData.toString());
		BBMainData sData = main.dao.getSelectLogin(data.memberData);
		
		sData.protocol = 2101;
		this.sendData(sData);
	}
	//  쪽지 리스트 검색
	void messageListProcess(BBMainData data){
		
		System.out.println("받은 쪽지리스트 관련 서버로 넘어온 데이터 : " + data.memberData.toString());
		String id = data.memberData.id;
		
		BBMainData sData = main.dao.getMsgListSelect(id);
		
		System.out.println("BBClientThread.messageFromProcess함수에서 보내는 sData ========== " + sData.msgFromList.toString());
		
		sData.protocol = 2401;
		this.sendData(sData);
	}
	// 쪽지 상세보기 
	void messageDetailProcess(BBMainData data){
		System.out.println("받은 쪽지리스트 관련 서버로 넘어온 데이터 : " + data.memberData.toString());
		int no = data.msgData.no;
		
		BBMainData sData = main.dao.getMsgDetail(no);
		
		System.out.println("BBClientThread.messageDetailProcess함수에서 보내는 sData ========== " + sData.msgData.toString());
		
		sData.protocol = 2402;
		this.sendData(sData);
	}
	
//	// 보낸 쪽지 지리스트 검색
//	void messageToProcess(BBMainData data){
//		System.out.println("전달한 쪽지리스트 관련 서버로 넘어온 데이터 : " + data.msgData.toString());
//		String id = data.memberData.id;
//		
//		BBMainData sData = main.dao.getMsgToSelect(id);
//		
//		System.out.println("BBClientThread.messageToProcess함수에서 보내는 sData ========== " + sData.toString());
//		sData.protocol = 2401;
//		this.sendData(sData);
//	}
		
	public void run(){
		try{
			while(true){
				BBMainData returnData = (BBMainData)oin.readObject();

				System.out.println("넘어온 프로토콜 난바와? : " + returnData.protocol);

				switch(returnData.protocol){
				case 1001:			// 회원 가입
//					joinProcess(returnData);
					break;
				case 1101:			//	로그인
					loginProcess(returnData);
					break;
				case 1201:			// 책 검색 관련
					searchAllProc();   //전체검색 수신
					break;
				case 1202:			// 책 등록 관련
					bookRegister(returnData);
					break;
				case 1203:			// 책 수정 관련
					bookModify(returnData);
					break;
				case 1301:			// 예약 관련
//					rentalProcess(returnData);
					break;
				case 1401: 		// 쪽지(받은,보낸) 관련
					System.out.println("쪽지 처리 관련 정보 : " + returnData.memberData.toString());
					messageListProcess(returnData);
					break;
				case 1402:
					System.out.println("선택한 쪽지 처리(상세보기) 관련 정보 : " + returnData.msgData.toString());
					messageDetailProcess(returnData);
				case 1403: 		// 쪽지 관련(보내는 쪽지)
					MessageInsertUser(returnData);
					break;	
				}
			}
		}catch (Exception e) {
		}finally {
			try{
				oin.close();
				oout.close();
				socket.close();
			}catch (Exception e) {
				main.clientList.remove(this);
			}
		}
	}
	
	/**
	 * 클라이언트에게 데이터를 스트림으로 응답하는 함수
	 * @param BBMainData 
	 * @since	2017.02.21
	 */
	private void sendData(BBMainData sData){
		try{
			System.out.println("sendData -- " + sData.protocol);
			oout.writeObject(sData);
		}catch (Exception e) {
			System.out.println("데이터 응답중 에러-- BBClientThread");
		}
	}

}
