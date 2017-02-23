package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC_mysql {
	public Connection con;

	public MyJDBC_mysql() {
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/book", "root", "borg");
		}catch(Exception e){
			System.out.println(e + " : mySql 데이터베이스 접속실패");
			e.printStackTrace();
		}
	}

public Statement getStmt() {
	Statement stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return stmt;
}

public PreparedStatement getPstmt(String sql){
	PreparedStatement pstmt = null;
	try {
		pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return pstmt;
}

public void close(Object o){
	try{
		if(o instanceof Connection){
			((Connection) o).close();
		}
		else if(o instanceof Statement){
			((Statement) o).close();
		}
		else if(o instanceof PreparedStatement){
			((PreparedStatement) o).close();
		}
		else if(o instanceof ResultSet){
			((ResultSet) o).close();
		}
	}catch(Exception e){}
}
	
}
