package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Receive.BBReceiveThread;

public class LoginServer {
		ServerSocket server;
		ArrayList clientList;
		//PreparedStatement loginS;
		BBMainServer db;
		ResultSet rs;
		
		public LoginServer() {
			try{
				server = new ServerSocket(9902);
				clientList = new ArrayList();
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
			db = new BBMainServer();
			
			//String login = "select * from b_member where bm_id=? and m_pw=?";
			//loginS = db.getPSTMT(login);
			
			//System.out.println("접속 대기");
			
			while(true){
				try{					
					Socket socket = server.accept();
					BBClientThread thread = new BBClientThread(this, socket);
					clientList.add(thread);
					thread.start();
				}catch (Exception e) {
					System.out.println("접속 에러" + e);
					e.printStackTrace();
				}
			}
		}
		
		public static void main(String[] args) {
			new LoginServer();
		}
}
