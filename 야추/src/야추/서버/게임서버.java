package 야추.서버;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.OracleDB;

public class 게임서버 {
	private static ArrayList<유저> 유저목록;
	public static ArrayList<PrintWriter> m_OutputList;

	public static void main(String[] args) {
		try {
			m_OutputList = new ArrayList<PrintWriter>();
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("서버가 시작됐습니다.");
//			System.out.println("서버 .." + serverSocket.);
			System.out.println("서버의 포트는 " + serverSocket.getLocalPort());
			Socket socket = null;
			유저목록 = new ArrayList<유저>();
			while ((socket = serverSocket.accept()) != null) {
				boolean flag = true;
				for (유저 user : 유저목록) {
					if (socket.getPort() == user.getSocket().getPort()) {
						flag = false;
						break;
					}
				}
				if (flag) {
					m_OutputList.add(new PrintWriter(socket.getOutputStream()));
					유저 client = new 유저(socket);
					client.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				new OracleDB().서버종료();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static ArrayList<유저> get유저목록() {
		return 유저목록;
	}

}
