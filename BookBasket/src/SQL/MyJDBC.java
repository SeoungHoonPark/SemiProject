package SQL;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {
	public Connection con;
	public MyJDBC() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Statement getSTMT(){
		Statement stmt = null;
		try{
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	public PreparedStatement getPSTMT(String sql){
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	public void close(Object o) {
		try{
			if(o instanceof Connection){
				((Connection)o).close();
			}
			else if(o instanceof Statement){
				((Statement)o).close();
			}
			else if(o instanceof PreparedStatement){
				((PreparedStatement)o).close();
			}
			else if(o instanceof ResultSet){
				((ResultSet)o).close();
			}
		}
		catch (Exception e) {
		}
		
	}
}
