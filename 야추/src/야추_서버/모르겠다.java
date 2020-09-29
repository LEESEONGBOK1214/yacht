package 야추_서버;

import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class 모르겠다 extends Thread {
	String name;
	InetAddress addr;
	Socket socket;
	int port;
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String id = "system";
	static String passwd = "oracle";
	static Connection conn;
	static Statement stmt;

	모르겠다(String name, Socket socket) {
		this.name = name;
		this.socket = socket;
		this.addr = socket.getInetAddress();
		this.port = socket.getPort();
	}

	모르겠다(Socket socket) {
		this.socket = socket;
		this.addr = socket.getInetAddress();
		this.port = socket.getPort();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,passwd);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("사용자 추가");
	}
}
