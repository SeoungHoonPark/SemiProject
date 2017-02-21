package Receive;

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
				case 2101:
					System.out.println("여긴 넘어오냐?");
					setLogin(data);
					break;
				case 2201:					// 책 검색 관련
//					setBSearch(Data);
					break;
				case 2301:					// 예약 관련
//					setRentalProcess(Data);
					break;
				case 2401: 				// 쪽지 관련
					setMessageProcess(data);
					break;
				case 2402: 				// 쪽지 관련
					setMessageProcess(data);
				}
			}
		}catch (Exception e) {			
		}finally {
			System.exit(0);
		}
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
		System.out.println("쪽지 엑션");
		if(data.isSuccess){
			main.data = data;
		}else{
			System.out.println("쪽지 정보 Access 오류");
		}
	}
}
