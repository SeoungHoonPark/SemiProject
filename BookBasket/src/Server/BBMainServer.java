package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Data.BBMainData;
import SQL.MyJDBC;

/*
 * 	데이터베이스 연결
 * 	대기 소겟 만들고
 * 
 * 	반복하면서 접속 받고
 */


public class BBMainServer extends Thread {
	//ServerSocket 		server;
	
	public Connection con = null;
	public Statement stmt = null;
	public PreparedStatement pstmt = null;
	
	PreparedStatement 	pstmtTotalSearch,   	//전체검색 
						pstmtAvailableSearch,	//대출가능검색
						pstmtBooknameSearch,	//책이름검색
						pstmtMybookSearch,		//내책검색 : 나중에 DAO로 이식할 예정.
						pstmtRentalFInit,		//예약테이블 검색
						pstmtRentalTInit;		//예약테이블 검색
	
	public PreparedStatement loginS = null ;
	public PreparedStatement idS = null ;
	public PreparedStatement joinS = null ;
						
	Statement			stmtTotalSearch;
	MyJDBC		db;
	public ResultSet rs = null;
	
	public static ServerSocket server;
	public ArrayList clientList;
	public DataAccessObject dao;
	/**
	 * 서버 소켓을 생성한다
	*/
	public BBMainServer() {
		try{
			server = new ServerSocket(8889);
			clientList = new ArrayList();	
			dao = new DataAccessObject();
		}catch(Exception e){
			System.out.println("BBMainServer 서버소켓 초기화처리 예외");
			e.printStackTrace();
			System.exit(0);
		}
		
		BBMainData returnData = new BBMainData();
		db = new MyJDBC();
		
		// 로그인 & 회원가입
		String loginStr = "Select * from b_member where bm_id=? and bm_pw=?";
		String idSql = "Select bm_name from b_member where bm_id=?";
		String joinSql = "insert into b_member values((select (nvl(max(bm_no), 0)+1) from b_member), ?, ?, ?, ?, ?, sysdate)";
		loginS = db.getPSTMT(loginStr);
		idS = db.getPSTMT(idSql);
		joinS = db.getPSTMT(joinSql);
		
		String totalSearch 		= "SELECT bb_no, bb_name, bb_writer, bb_ownerID,bb_status, bb_rcode, bb_date, bb_visibleyn FROM b_book where bb_visibleYN='Y'";
		String availableSearch 	= "SELECT bb_no, bb_name, bb_writer, bb_ownerID,bb_status, bb_rcode ,bb_date ,bb_visibleyn FROM b_book where bb_visibleYN='Y' and bb_rcode='10'";
		String BooknameSearch	= "SELECT * FROM B_BOOK where bb_name=? and bb_visibleYN='Y'";
		String mybookSearch		= "SELECT * FROM B_BOOK where bb_ownerid=? and bb_visibleYN='Y'";
		String rentalFInit		= "SELECT * FROM B_RSRV";
		String rentalTInit		= "SELECT * FROM B_RSRV";
		
		pstmtTotalSearch 		= db.getPSTMT(totalSearch);
		pstmtAvailableSearch 	= db.getPSTMT(availableSearch);
		pstmtBooknameSearch  	= db.getPSTMT(BooknameSearch);
		pstmtMybookSearch 		= db.getPSTMT(mybookSearch);
		pstmtRentalFInit		= db.getPSTMT(rentalFInit);
		pstmtRentalTInit		= db.getPSTMT(rentalTInit);
		
		System.out.println(" 접속 대기 ");
		
	}
	
	public void run(){
		while(true){
			try{
				System.out.println("접속 대기");
				Socket socket = server.accept();
				BBClientThread client = new BBClientThread(this, socket);
				clientList.add(client);
				client.start();
				
			}catch (Exception e) {
			}
		}
	}
	
	public static void main(String[] args){
		new BBMainServer().start();
	}
}
