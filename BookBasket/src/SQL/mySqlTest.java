package SQL;
import	java.sql.Connection;
import	java.sql.DriverManager;
import	java.sql.ResultSet;
import	java.sql.SQLException;
import	java.sql.Statement;
public class mySqlTest {

	public mySqlTest() {
		try {
			Connection con = null;
			
			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "borg");
			con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "borg");
			
			Statement st = null;
			ResultSet rs = null;
			
			st = con.createStatement();
//			rs = st.executeQuery("SHOW DATABASES");
			rs = st.executeQuery("select * from Member");
			
			if(st.execute("select * from Member")){
				rs = st.getResultSet();
			}
			
			while(rs.next()){
				String str1 = rs.getNString(1);
				String str2 = rs.getNString(2);
				System.out.println("ID : " + str1 + "  PW : " + str2);
			}
			
			
		} catch (SQLException sqex) {
			System.out.println("SQLException: "+ sqex.getMessage());
			System.out.println("SQLState: "+sqex.getSQLState());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	public static void main(String[] args) {
		new mySqlTest();
	}

}
