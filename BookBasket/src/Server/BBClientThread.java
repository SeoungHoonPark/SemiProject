package Server;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
/*
 *  	실제로 클라이언트하고 통신하는 클래스
 */
import java.net.Socket;
import java.util.Vector;

import Data.BBBookData;
import Data.BBMainData;
import Data.BBMemberData;

public class BBClientThread extends Thread {
	
	public BBMainServer main;
	//	필수정보
	public Socket 			socket;
	public ObjectInputStream 	oin;
	public ObjectOutputStream	oout;	
	
	//	부가정보
	public String	id;
	public int no;
	public String name;
	public String iid;
	public String pw;
	public String email;
	public String phone;
	public String jdate;
	
	public String bnameRequested;  // 사용자가 입력한 책이름 검색요청 입력값.
	public String idRequested;		// 사용자 id 
	
	public BBClientThread(BBMainServer m, Socket s)throws Exception {
		main = m;
		socket = s;
		
		InputStream 	in  = socket.getInputStream();
		OutputStream 	out = socket.getOutputStream();
		
		oout = new ObjectOutputStream(out);
		oin	 = new ObjectInputStream(in);
		
	}
	
	void searchAllProc(){
		
		BBBookData bookD = new BBBookData();
		
		Vector list = new Vector();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("전체검색 함수 실행성공");
	}
	
	void availableSearch(){
		
		BBBookData bookD = new BBBookData();
		Object[][] obj = null;
		try{	
			main.rs = main.pstmtAvailableSearch.executeQuery();
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
			System.out.println("bbclientthread 대출가능 검색 함수 예외");
		}finally {
			main.db.close(main.rs);
		}
		BBMainData mainD = new BBMainData();
		mainD.protocol = 2205;
	//	mainD.list = list;
		mainD.objectArray = obj;
		
		System.out.println("대출가능검색 응답프로토콜 :"+mainD.protocol);
		
		try {
			oout.writeObject(mainD);
			System.out.println(mainD.protocol);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("대출가능검색 함수 실행성공");
	}
	
	void booknameSearch(){
		
				
		
		Object[][] obj = null;
		try{	
		    main.pstmtBooknameSearch.setString(1, bnameRequested);
	
			main.rs = main.pstmtBooknameSearch.executeQuery();
			main.rs.last();
			int row = main.rs.getRow();
			main.rs.beforeFirst();
			System.out.println("rs row 길이" + row);
			
			int c=0;
			
			
			obj = new Object[row][7];
			while(main.rs.next()==true){
				obj[c][0]=main.rs.getInt("bb_no");
				obj[c][1]=main.rs.getString("bb_name");
				obj[c][2]=main.rs.getString("bb_writer");
				obj[c][3]=main.rs.getString("bb_date");
				obj[c][4]=main.rs.getString("bb_status");
				obj[c][5]=main.rs.getInt("bb_visibleYN");
				obj[c][6]=main.rs.getString("bb_rcode");
				c++;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("bbclientthread 책이름 검색 함수 예외");
		}finally {
			main.db.close(main.rs);
		}
		BBMainData mainD = new BBMainData();
		mainD.protocol = 2207;
	//	mainD.list = list;
		mainD.objectArray = obj;
		
		System.out.println("책이름 검색 응답프로토콜 :"+mainD.protocol);
		
		try {
			oout.writeObject(mainD);
			System.out.println(mainD.protocol);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("책이름 검색 함수 실행성공");
	}
	
	void mybookSearch(){
		
		BBBookData bookD = new BBBookData();
		BBMemberData memberD = new BBMemberData();
		
		Object[][] obj = null;
		try{		
			
			System.out.println("테슷 343"+idRequested);
			main.pstmtMybookSearch.setString(1, idRequested);
				
			main.rs = main.pstmtMybookSearch.executeQuery();
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
			System.out.println("bbclientthread 나의 책 검색 함수 예외");
		}finally {
			main.db.close(main.rs);
		}
		BBMainData mainD = new BBMainData();
		mainD.protocol = 2206;
		mainD.objectArray = obj;
		
		System.out.println("나의책 검색 응답프로토콜 :"+mainD.protocol);
		
		try {
			oout.writeObject(mainD);
			System.out.println(mainD.protocol);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("나의책 검색 함수 실행성공");
	}
	//------------------------ 예약테이블 관련 함수. 마지막 테이블에 입력이 안되고 있음
	void rentalFInitProc(){
				
		Object[][] obj = null;
		try{	
			main.rs = main.pstmtRentalFInit.executeQuery();
			main.rs.last();
			int row = main.rs.getRow();
			System.out.println("rs row 길이" + row);
			
			int c=0;
			
			
			obj = new Object[row][6];
			while(main.rs.next()==true){
				obj[c][0]=main.rs.getInt("br_no");
				obj[c][1]=main.rs.getString("br_bno");
				obj[c][2]=main.rs.getString("br_rid");
				obj[c][3]=main.rs.getString("br_date");
				obj[c][4]=main.rs.getString("br_status");
				c++;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("bbclientthread 예약테이블 설정함수 예외");
		}finally {
			main.db.close(main.rs);
		}
		BBMainData mainD = new BBMainData();
		mainD.protocol = 2208;
	//	mainD.list = list;
		mainD.objectArray = obj;
		
		System.out.println("전체검색 응답프로토콜 :"+mainD.protocol);
		
		try {
			oout.writeObject(mainD);
			System.out.println(mainD.protocol);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("예약창 테이블데이터주입 함수 실행성공");
		
	}
	
	void rentalTInitProc(){
		
		Object[][] obj = null;
		try{	
			main.rs = main.pstmtRentalTInit.executeQuery();
			main.rs.last();
			int row = main.rs.getRow();
			System.out.println("rs row 길이" + row);
			
			int c=0;
			
			
			obj = new Object[row][6];
			while(main.rs.next()==true){
				obj[c][0]=main.rs.getInt("br_no");
				obj[c][1]=main.rs.getInt("br_bno");
				obj[c][2]=main.rs.getString("br_rid");
				obj[c][3]=main.rs.getString("br_date");
				obj[c][4]=main.rs.getInt("br_status");
				c++;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("bbclientthread 예약테이블 설정함수 예외");
		}finally {
			main.db.close(main.rs);
		}
		BBMainData mainD = new BBMainData();
		mainD.protocol = 2302;
	//	mainD.list = list;
		mainD.objectArray = obj;
		
		System.out.println("전체검색 응답프로토콜 :"+mainD.protocol);
		
		try {
			oout.writeObject(mainD);
			System.out.println(mainD.protocol);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("예약창 테이블데이터주입 함수 실행성공");		
	}
	//------------------------ 예약테이블 관련 함수. 마지막 테이블에 입력이 안되고 있음
	
	// 책 등록정보 데이터 받기(예송)
		void bookRegister(BBMainData data){
//			System.out.println("서버로 넘어온 데이터 : "+data.bookData.bb_name);
//			data.bookData.bb_ownerid = id;
//			System.out.println("로그인한 사람: "+id);
			boolean result = main.dao.bookRegister(data.bookData);
			
			BBMainData sData = new BBMainData();
			sData.protocol = 2202;
			sData.isSuccess = result;
			
			this.sendData(sData);
		}
		// 책 수정 데이터 받기 (예송)
		void bookModify(BBMainData data){
			boolean result = main.dao.bookModify(data.bookData);
			BBMainData sData = new BBMainData();
			
			sData.protocol = 2203;
			sData.isSuccess = result;
			this.sendData(sData);
			
		}
		// 책 삭제 데이터 받기(예송)
		void bookDelete(BBMainData data){
			boolean result = main.dao.bookDelete(data.bookData);
			BBMainData sData = new BBMainData();
			sData.protocol = 2204;
			sData.isSuccess = result;
			this.sendData(sData);
		}
		//	메시지 데이터 받기(예송)
		void MessageInsertUser(BBMainData data){
//			data.bookData.bb_ownerid = id;
			boolean result = main.dao.MessageInsertUser(data.msgData, data.bookData);
			BBMainData sData = new BBMainData();
			sData.protocol = 2403;
			sData.isSuccess = result;
			this.sendData(sData);
		}
		// 아이디 체크
		public void idCheckProc(BBMainData d){
			System.out.println("서버로 넘어온 데이터 : " + d.memberData.id);
			boolean isSuccess = false ;
			try{
				main.idS.setString(1, d.memberData.id);

				main.rs = main.idS.executeQuery();
				if (main.rs.next()){
					isSuccess = true ;
					name = main.rs.getString("bm_name");
					System.out.println(name);
				}
				else{
					isSuccess = false ;
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			finally{
				main.db.close(main.rs);
			}
			

			BBMainData mainD = new BBMainData();
			mainD.protocol = 2001 ;
			mainD.isSuccess = isSuccess ;
			
			try{
				oout.writeObject(mainD);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		// 회원가입
		public void joinProc(BBMainData d){
			System.out.println("서버로 넘어온 데이터 : " + d.memberData.id);
			boolean isSuccess = false ;
			try{
				main.joinS.setString(1, d.memberData.id);
				main.joinS.setString(2, d.memberData.pw);
				main.joinS.setString(3, d.memberData.name);
				main.joinS.setString(4, d.memberData.email);
				main.joinS.setString(5, d.memberData.phone);
				
				main.rs = main.joinS.executeQuery();
				if (main.rs.next()){
					isSuccess = true ;
					
					System.out.println("insert 실행결과 : " + isSuccess);
				}
				else{
					isSuccess = false ;
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			finally{
				main.db.close(main.rs);
			}
			
			BBMainData mainD = new BBMainData();
			BBMemberData memberData = new BBMemberData();
			mainD.protocol = 2011 ;
			mainD.isSuccess = isSuccess ;
			
			try{
				oout.writeObject(mainD);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
	//-------승훈형--------	
		// 로그인
		public void loginProc(BBMainData d){
			System.out.println("서버로 넘어온 데이터 :" + d.memberData.id + ":");
			System.out.println("서버로 넘어온 데이터 :" + d.memberData.pw + ":");
			boolean isSuccess = false ;
			try{
				main.loginS.setString(1, d.memberData.id);
				main.loginS.setString(2, d.memberData.pw);
				
				main.rs = main.loginS.executeQuery();
				if (main.rs.next()){
					System.out.println("성");
					isSuccess = true ;
					iid = main.rs.getString("bm_id");
					pw = main.rs.getString("bm_pw");
					this.name = main.rs.getString("bm_name");
					System.out.println(pw);
					System.out.println(iid);
				}
				else{
					System.out.println("실");

					isSuccess = false ;
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			finally{
				main.db.close(main.rs);
			}
			BBMainData mainD = new BBMainData();
			BBMemberData loginD = new BBMemberData();
			mainD.protocol = 2101 ;
			mainD.isSuccess = isSuccess ;
			loginD.name = name;
			loginD.id = iid;
			loginD.pw = pw ;
			mainD.memberData = loginD;
			try{
				oout.writeObject(mainD);
			}
			catch(Exception e){
				e.printStackTrace();
			}
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

//-------승훈형--------
	
	public void run() {
		try {
			while(true){
				BBMainData returnData = (BBMainData)oin.readObject();
				System.out.println("116넘어온 프로토콜 난바와? : " + returnData.protocol);
				System.out.println("117클라이언스 스레드 객체수신 체크");

				switch(returnData.protocol){
				case 1001:			// 회원 가입
//					joinProcess(returnData);
					idCheckProc(returnData);
					break;
				case 1011:		// 회원가입
					joinProc(returnData);
					break;
				case 1101:			//	로그인
					System.out.println("로그인 스레드로 넘어올까? " + returnData.protocol);
					loginProc(returnData);
					idRequested = returnData.memberData.id;
					System.out.println("내책검색용 아이디 확인: " +idRequested);
					
					break;
				case 1201:			// 책 검색 관련
//					bookSearch(returnData);
					searchAllProc();   //전체검색 수신
					break;
				case 1202:			// 책 등록 관련
					bookRegister(returnData);
					break;
				case 1203:			// 책 수정 관련
					bookModify(returnData);
					break;
				case 1204:			// 책 삭제 관련
					bookDelete(returnData);
					break;
				case 1205:
					availableSearch(); //대출가능검색 수신
					break;
				case 1206:
					mybookSearch();		// 나의 책 검색. 
					break;
				case 1207:
					booknameSearch();		// 책이름 검색. 
					break;
				case 1301:			// 예약 관련
					rentalFInitProc();
//					rentalProcess(returnData);
				case 1302:
					rentalTInitProc();
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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BBClientThread 서버클라이언트 스레드 객체수신예외");
		}
	}
	
	private void sendData(BBMainData sData){
		try{
			System.out.println("sendData -- " + sData.protocol);
			oout.writeObject(sData);
		}catch (Exception e) {
			System.out.println("데이터 응답중 에러-- BBClientThread");
		}
	}
}
