package ����_����;

import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class �𸣰ڴ� extends Thread {
	String name;
	InetAddress addr;
	Socket socket;
	int port;
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String id = "system";
	static String passwd = "oracle";
	static Connection conn;
	static Statement stmt;

	�𸣰ڴ�(String name, Socket socket) {
		this.name = name;
		this.socket = socket;
		this.addr = socket.getInetAddress();
		this.port = socket.getPort();
	}

	�𸣰ڴ�(Socket socket) {
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
		System.out.println("����� �߰�");
	}
}
