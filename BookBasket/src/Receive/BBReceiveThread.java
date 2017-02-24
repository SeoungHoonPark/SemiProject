package Receive;

import java.util.Vector; 

import javax.swing.JOptionPane;
import Data.BBMainData;
import Main.BBMain;

public class BBReceiveThread extends Thread {
	public BBMain main;
	public BBReceiveThread(BBMain m) {
		main = m;
	}
	
	
	public void run() {
		try{
			while(true){
				BBMainData data = (BBMainData)main.oin.readObject();
				if(data == null){
					break;
				}
				switch(data.protocol){

				case 2001:					// 아이디 체크
					idCheckProc(data);		
					break;
				case 2011:					// 회원가입
					joinProc(data);
					break;
				case 2101:
					System.out.println("여긴 넘어오냐?");
					setLogin(data);
					break;
				case 2201:					// 전체 검색 
					tsReceive(data);
//					setBSearch(Data);
					break;
				case 2202:					// 책 등록 관련
					setBRegister(data);
					break;
				case 2203:					// 책 수정 관련
					setBModify(data);
					break;
				case 2204:					// 책 삭제 관련
					setBDelete(data);
					break;	
				case 2205:
					avReceive(data);	   //  대출가능 검색		
					break;
				case 2206:
					mbReceive(data);	   //  내책  검색		
					break;
				case 2207:
					bnReceive(data);		// 책이름 검색
					break;	
				case 2301:					// 예약 관련
//					setRentalProcess(Data);
					rsFReceive(data);		// 신청받은 예약창  테이블 설정
					break;
				case 2302:
					rsTReceive(data);		//  신청한 예약창  테이블 설정
					break;
				case 2401: 				// 쪽지 관련
					setMessageProcess(data);
					break;
				case 2402:					// 쪽지 상세보기 관련
					setMessageDetail(data);
					
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
	}
	
	// 아이디 체크
		public void idCheckProc(BBMainData data){
			if(!(data.isSuccess)){
				JOptionPane.showMessageDialog(main.loginDlg.joinDlg, "사용 가능한 ID 입니다.");
			}
			else{
				main.loginDlg.joinDlg.id_t.setText("");
				JOptionPane.showMessageDialog(main.loginDlg.joinDlg, "사용할 수 없는 ID 입니다.");
			}
		}
		// 회원가입 처리
		public void joinProc(BBMainData data){
			if(data.isSuccess){
				String idStr = main.loginDlg.joinDlg.id_t.getText();
				main.loginDlg.idF.setText(idStr);
				JOptionPane.showMessageDialog(main.loginDlg.joinDlg, "ID : " + idStr + " 님으로 회원 가입이 완료되었습니다.");
				main.loginDlg.setVisible(true);
				main.loginDlg.joinDlg.dispose();
			}
			else{
				JOptionPane.showMessageDialog(main.loginDlg.joinDlg, "회원 가입이 입력사항을 확인하세요!");
			}
		}
		
		void setBRegister(BBMainData data){
			if(data.isSuccess == true){
				JOptionPane.showMessageDialog(main, "등록이 완료되었습니다.");
			}
			else{
			}
		}
		// 책 수정 성공여부
		void setBModify(BBMainData data){
			if(data.isSuccess == true){
				JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
				main.bookSearchMain.searchAllProc();
			}
			else{
			}
		}
		// 책 삭제 성공여부
		void setBDelete(BBMainData data){
			if(data.isSuccess == true){
				JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
			}
			else{
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
		
	public void avReceive(BBMainData data){
		Object[] obj = new Object[data.objectArray.length];
		int length = data.objectArray.length;
		System.out.println("객체배열 길이 : " + length);
		
		main.bookSearchMain.tmodel.setRowCount(0);
		
		for(int i=0; i < data.objectArray.length;i++){
			main.bookSearchMain.tmodel.addRow(data.objectArray[i]);
		}
	}
	
	public void mbReceive(BBMainData data){
		
		Object[] obj = new Object[data.objectArray.length]; 
		int length = data.objectArray.length;
		System.out.println("객체배열 길이 : " + length);
		
		main.bookSearchMain.tmodel.setRowCount(0);
		
		for(int i=0; i < data.objectArray.length;i++){
			main.bookSearchMain.tmodel.addRow(data.objectArray[i]);
		}
	}
	
	public void bnReceive(BBMainData data){
		Object[] obj = new Object[data.objectArray.length];
		int length = data.objectArray.length;
		System.out.println("객체배열 길이 : " + length);
		
		main.bookSearchMain.tmodel.setRowCount(0);
		
		for(int i=0; i < data.objectArray.length;i++){
			main.bookSearchMain.tmodel.addRow(data.objectArray[i]);
		}
	}
	//------------------------ 예약테이블 관련 함수. 마지막 테이블에 입력이 안되고 있음
	public void rsFReceive(BBMainData data){
		Object[] obj = new Object[data.objectArray.length];
//	System.out.println(data.list.get(0));
		int length = data.objectArray.length;
		System.out.println("객체배열 길이 : " + length);
		
	//	main.bookRentalMain
	//	main.bookSearchMain.tmodel.setRowCount(0);
		main.bookRentalMain.FromRental.tmodel.setRowCount(0);
		
		for(int i=0; i < data.objectArray.length;i++){
	//		main.bookSearchMain.tmodel.addRow(data.objectArray[i]);
		main.bookRentalMain.FromRental.tmodel.addRow(data.objectArray[i]);
		System.out.println("rsTR 배열값 "+data.objectArray[i]);
		} 
	}
	
	public void rsTReceive(BBMainData data){
		Object[] obj = new Object[data.objectArray.length];
//		System.out.println(data.list.get(0));
			int length = data.objectArray.length;
			System.out.println("객체배열 길이 : " + length);
			
		//	main.bookRentalMain
		//	main.bookSearchMain.tmodel.setRowCount(0);
			main.bookRentalMain.ToRental.tmodel.setRowCount(0);
			
			for(int i=0; i < data.objectArray.length;i++){
		//		main.bookSearchMain.tmodel.addRow(data.objectArray[i]);
			main.bookRentalMain.ToRental.tmodel.addRow(data.objectArray[i]);
			System.out.println("rsTR 배열값 "+data.objectArray[i]);
			}
	}
	//------------------------ 예약테이블 관련 함수. 마지막 테이블에 입력이 안되고 있음
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
			System.out.println("BBReceiveThread.setMessageProcess.FromList --->	 " +	data.msgFromList.size());
			System.out.println("BBReceiveThread.setMessageProcess.ToList --->	 " +	data.msgToList.size());			
			main.data = data;
			main.msgMain.setVisible(true);
			main.msgMain.DisplayModel1();
			main.msgMain.DisplayModel2();
		}else{
			System.out.println("쪽지 정보 Access 오류");
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
	
}
