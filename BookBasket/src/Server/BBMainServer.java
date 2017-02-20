package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 	데이터베이스 연결
 * 	대기 소겟 만들고
 * 
 * 	반복하면서 접속 받고
 */
public class BBMainServer {
	public Connection con = null;
	public Statement stmt = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	
	public BBMainServer() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
		}catch (Exception e) {
			System.out.println("로딩 및 접속 에러 " + e);
			e.printStackTrace();
		}
		System.out.println("접속 성공");
	}
	
	/*
	 * 프리페어드 스테이트먼트 처리
	 */
	public Statement getSTMT(){
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			} catch (SQLException e) {
				System.out.println("스테이트먼트 에러 " + e);
				e.printStackTrace();
			}
		return stmt;
	}
	
	/*
	 * 프리페어드 스테이트먼트 처리
	 */
	public PreparedStatement getPSTMT(String sql){
			try {
				System.out.println("호출 sql = " + sql );
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			} catch (SQLException e) {
				System.out.println("프리페어드 스테이트먼트 에러 " + e);
				e.printStackTrace();
			}
		return pstmt;
	}
	
	/*
	 * 자원을 정리
	 */
	public void close(Object obj) {
		try{
			if(obj instanceof Connection){
				Connection temp  = (Connection) obj;
				temp.close();
			}else if(obj instanceof Statement){
				Statement temp = (Statement) obj;
				temp.close();
			}else if(obj instanceof PreparedStatement){
				PreparedStatement temp = (PreparedStatement) obj;
				temp.close();
			}else if( obj instanceof ResultSet){
				((ResultSet) obj).close();
			}
		}catch(Exception e){
			
		}
	}
}
