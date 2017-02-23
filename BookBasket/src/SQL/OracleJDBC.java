package SQL;
/*
 * 	�� Ŭ������ ������ JDBC �۾��� �� �� �ʿ��� ������
 * 	��ǰ�� ���� �����̴�.
 * 
 * 	��, JDBC �۾��� �ݺ����� �۾��� ���� ���ԵǾ��ִ�.
 * 
 * 	���۾��� ���� ���ϰ� �ϱ����ؼ� ������ ��ǰ��
 * 	���� ����� �����̴�.(�ݺ� �۾��� �ڵ����� ���ִ� 
 * 	����� ���� �����̴�.) 
 */
import		java.sql.*;
public class OracleJDBC {
	//	�������� �� ��ǰ�� �ʿ��ؼ� new�� ��Ű��
	//	������ �Լ��� ����� ���̰�
	//	���� �� ������ ����̹� �ε��� �ڵ����� ���ְ�ʹ�.
	Connection	con = null;
	public OracleJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	new OracleJDBC();
		}
		catch(Exception e) {
			System.out.println("����̹� �ε� ���� = " + e);
		}
	}
	//	�������� ������ �䱸�ϸ� ��� �������ִ� ����� �����ϰ� �ʹ�.
	public Connection	getCON() {
		try {
			String	url = "jdbc:oracle:thin:@localhost:1521:orcl";
			con = DriverManager.getConnection(url, "scott", "tiger"); 
		}
		catch(Exception e) {
			System.out.println("���� ���� = " + e);
		}
		return con;
	}
	//	�������� Statement�� ����� �޶�� �ϸ� �ڵ�����
	//	������ִ� �Լ�
	public Statement	getSTMT() {
		Statement	 stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e) {
			System.out.println("Statement ���� ���� = " + e);
		}
		return stmt;
	}
	//	�ݾ��ִ� �Լ��� ����� �ʹ�.
	public void close(Object o/*�������� �˷��ָ�(���� ������ �����Ƿ�)*/) {
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















