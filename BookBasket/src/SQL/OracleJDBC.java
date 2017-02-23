package SQL;
/*
 * 	이 클래스는 앞으로 JDBC 작업을 할 때 필요한 나만의
 * 	부품을 만들 예정이다.
 * 
 * 	즉, JDBC 작업은 반복적인 작업이 많이 포함되어있다.
 * 
 * 	이작업을 좀더 편하게 하기위해서 나만의 부품을
 * 	만들어서 사용할 예정이다.(반복 작업을 자동으로 해주는 
 * 	기능을 만들 예정이다.) 
 */
import		java.sql.*;
public class OracleJDBC {
	//	누군가가 이 부품이 필요해서 new를 시키면
	//	생성자 함수가 실행될 것이고
	//	나는 그 순간에 드라이버 로딩을 자동으로 해주고싶다.
	Connection	con = null;
	public OracleJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	new OracleJDBC();
		}
		catch(Exception e) {
			System.out.println("드라이버 로딩 에러 = " + e);
		}
	}
	//	누군가가 접속을 요구하면 대신 접속해주는 기능을 제공하고 싶다.
	public Connection	getCON() {
		try {
			String	url = "jdbc:oracle:thin:@localhost:1521:orcl";
			con = DriverManager.getConnection(url, "scott", "tiger"); 
		}
		catch(Exception e) {
			System.out.println("접속 에러 = " + e);
		}
		return con;
	}
	//	누군가가 Statement를 만들어 달라고 하면 자동으로
	//	만들어주는 함수
	public Statement	getSTMT() {
		Statement	 stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e) {
			System.out.println("Statement 생성 에러 = " + e);
		}
		return stmt;
	}
	//	닫아주는 함수를 만들고 싶다.
	public void close(Object o/*닫을것을 알려주면(닫을 종류가 많으므로)*/) {
		try {
			if(o instanceof Connection) {
				Connection	temp = (Connection) o;
				temp.close();
			}
			else if(o instanceof Statement) {
				Statement	temp = (Statement) o;
				temp.close();
			}
			else if(o instanceof ResultSet) {
				((ResultSet) o).close();
			}
		}
		catch(Exception e) {}
	}
}















