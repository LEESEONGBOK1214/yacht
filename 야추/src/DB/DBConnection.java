package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
    public static Connection dbConn;
    
	public static Connection getConnection() {
		Connection conn = null;
		try {
//			String user = "timeck"; // timeck <
//			String pw = "lenope1214"; // 디비접속 비밀번호
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "s1901206";
			String pw = "p1901206";
			String url = "jdbc:oracle:thin:@net.yju.ac.kr:1521:orcl";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
        }
		return conn;
	}
}