package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import SQL.MyJDBC;

/*
 * 	대기 소겟 만들고
 * 
 * 	반복하면서 접속 받고
 */
public class BBMainServer extends Thread {
	public Connection con = null;
	public Statement stmt = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	
	PreparedStatement 	pstmtTotalSearch,   	//전체검색 
	pstmtAvailableSearch;	//대출가능검색
//	pstmtBooknameSearch;	//책이름검색
//	pstmtMybookSearch;		//내책검색 : 나중에 DAO로 이식할 예정.
	MyJDBC		db;
	
	public static ServerSocket server;
	public ArrayList clientList;
	public DataAccessObject dao;
	
	/**
	 * 서버 소켓을 생성한다
	*/
	public BBMainServer() {
		try{
			server = new ServerSocket(9991);
			clientList = new ArrayList();	
			dao = new DataAccessObject();
		}catch (Exception e) {
			System.exit(0);
		}
		
		db = new MyJDBC();
		String totalSearch 		= "SELECT bb_no, bb_name, bb_writer, bb_ownerID,bb_status, bb_rcode, bb_date, bb_visibleyn FROM b_book where bb_visibleYN='Y'";
		String availableSearch 	= "SELECT bb_no, bb_name, bb_writer, bb_ownerID,bb_status, bb_rcode ,bb_date ,bb_visibleyn FROM b_book where bb_visibleYN='Y' and bb_rcode='10'";
		pstmtTotalSearch 		= db.getPSTMT(totalSearch);
		pstmtAvailableSearch 	= db.getPSTMT(availableSearch);
	//	pstmtBooknameSearch 	= db.getPSTMT( ?? );
	//	pstmtMybookSearch 		= db.getPSTMT( ?? );
		
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
	
	public static void main(String[] args) {
		new BBMainServer().start();
	}
}