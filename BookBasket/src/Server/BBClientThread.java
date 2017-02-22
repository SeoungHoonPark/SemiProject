package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

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
	
	void loginProcess(BBMainData data){
		System.out.println("서버로 넘어온 데이터 : " + data.memberData.toString());
		BBMainData sData = main.dao.getSelectLogin(data.memberData);
		
		sData.protocol = 2101;
		this.sendData(sData);
	}
	// 받은 쪽지 리스트 검색
	void messageListProcess(BBMainData data){
		
		System.out.println("받은 쪽지리스트 관련 서버로 넘어온 데이터 : " + data.memberData.toString());
		String id = data.memberData.id;
		
		BBMainData sData = main.dao.getMsgListSelect(id);
		
		System.out.println("BBClientThread.messageFromProcess함수에서 보내는 sData ========== " + sData.msgFromList.toString());
		
		sData.protocol = 2401;
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
//					bookSearch(returnData);
					break;
				case 1301:			// 예약 관련
//					rentalProcess(returnData);
					break;
				case 1401: 		// 쪽지(받은,보낸) 관련
					System.out.println("쪽지 처리 관련 정보 : " + returnData.memberData.toString());
					messageListProcess(returnData);
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
