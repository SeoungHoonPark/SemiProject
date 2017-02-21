package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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