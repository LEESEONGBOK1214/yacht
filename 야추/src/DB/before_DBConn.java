package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class before_DBConn 
{
    public static Connection dbConn;
    
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String user = "timeck"; // timeck <
			String pw = "lenope1214"; // ������� ��й�ȣ
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "s1901206";
//			String pw = "p1901206";
//			String url = "jdbc:oracle:thin:@net.yju.ac.kr:1521:orcl";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database�� ����Ǿ����ϴ�.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB ����̹� �ε� ���� :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB ���ӽ��� : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
        }
		return conn;
	}
}