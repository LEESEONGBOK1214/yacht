package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static DBConnection instance = new DBConnection();

	private DBConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("클래스를 못찾음");
		}
	}

	public static DBConnection getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;
//		String user = "s1901206";
//		String pw = "p1901206";
//		String url = "jdbc:oracle:thin:@net.yju.ac.kr:1521:orcl";
		String user = "timeck";
		String pw = "lenope1214";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		conn = DriverManager.getConnection(url, user, pw);

		return conn;
	}
}
