package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BBJDBC {
	
	public BBJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e) {
			System.out.println("드라이버 로딩 에러 = " + e);
		}
	}
	
	public Connection getCon(){
		Connection con = null;
		try {
			String	url = "jdbc:oracle:thin:@localhost:1521:orcl";
			con = DriverManager.getConnection(url, "scott", "tiger"); 
		}
		catch(Exception e) {
			System.out.println("접속 에러 = " + e);
		}
		return con;
	}
	public Statement getStmt(Connection con){
		Statement	stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e) {
			System.out.println("Statement 생성 에러 = " + e);
		}
		return stmt;
	}
	
	public PreparedStatement getPstmt(Connection con, String sql) {
		PreparedStatement	stmt = null;
		try {
			stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e) {
			System.out.println("PreparedStatement 생성 에러 = " + e);
		}
		return stmt;
	}
	// 자원정리
	public void close(Object o) {
		try {
			if(o instanceof Connection) {
				((Connection) o).close();
			}
			else if(o instanceof Statement) {
				((Statement) o).close();
			}
			else if(o instanceof PreparedStatement) {
				((PreparedStatement) o).close();
			}
			else if(o instanceof ResultSet) {
				((ResultSet) o).close();
			}
		}
		catch(Exception e) {}
	}

}
