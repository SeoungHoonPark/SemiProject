package Receive;

import javax.swing.JOptionPane;

import Data.BBMainData;
import Main.BBMain;

public class BBReceiveThread extends Thread {
	public BBMain main;

	public BBReceiveThread(BBMain m) {
		main = m;
	}

	public void run() {
		//	계속해서 서버에서 오는 데이터 받기
		try{
			while(true){
				BBMainData data = (BBMainData) main.oin.readObject();
				if(data == null){
					break;
				}
				// 서버로 부터 넘어온 프로토콜을 확인하고 적절하게 함수처리
				switch (data.protocol) {
				case 2001:
//					setJoin(data);			//회원 가입
					break;
				case 2101:
					setLogin(data);
					break;
				case 2201:					// 책 검색 관련
					tsReceive(data);
					break;
				case 2202:					// 책 등록 관련
					setBRegister(data);
					break;
				case 2203:					// 책 수정 관련
					setBModify(data);
					break;					
				case 2301:					// 예약 관련
//					setRentalProcess(Data);
					break;
				case 2401: 				// 쪽지 관련
					setMessageProcess(data);
					break;
				case 2402:					// 쪽지 상세보기 관련
					setMessageDetail(data);
				}
			}
		}catch (Exception e) {			
		}finally {
			System.exit(0);
		}
	}
	
	void setMessageDetail(BBMainData data) {
System.out.println("쪽지 상세보기 성공 여부를 찍자 ---> " + data.isSuccess);
		if(data.isSuccess){///dataSetting
			main.msgMain.msgView.dataSetting();
		}else{
			System.out.println("쪽지 상세보기 실패");
		}
	}

	public void tsReceive(BBMainData data){
		Object[] obj = new Object[data.objectArray.length];
//	System.out.println(data.list.get(0));
		int length = data.objectArray.length;
		System.out.println("객체배열 길이 : " + length);
		
		main.bookSearchMain.tmodel.setRowCount(0);
		
		for(int i=0; i < data.objectArray.length;i++){
			main.bookSearchMain.tmodel.addRow(data.objectArray[i]);
		}
	}
	// 책 등록 성공 여부
	void setBRegister(BBMainData data){
		if(data.isSuccess == true){
			JOptionPane.showMessageDialog(null, "등록이 완료되었습니다.");
		}
		else{
		}
	}
	// 책 수정 성공여부
	void setBModify(BBMainData data){
		if(data.isSuccess == true){
			JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
		}
		else{
		}
	}
	//  예약신청 메시지 보내기 성공여부
	void sendMessage(BBMainData data){
		JOptionPane.showMessageDialog(null, "예약신청이 완료되었습니다.");
	}
	void setLogin(BBMainData data){
		System.out.println("로그인 성공 여부를 찍어보자 ---> " + data.isSuccess);
		if(data.isSuccess){
			main.showMain();
		}else{
			System.out.println("로그인 실패");
		}
	}
	void setMessageProcess(BBMainData data){
		System.out.println("쪽지 액션");
		if(data.isSuccess){
			System.out.println("BBReceiveThread.setMessageProcess.FromList 리져브th레드--->	 " +	data.msgFromList.toString());
			System.out.println("BBReceiveThread.setMessageProcess.ToList 리져브th레드--->	 " +	data.msgToList.toString());
			
			main.data = data;
		}else{
			System.out.println("쪽지 정보 Access 오류");
		}
	}
}
