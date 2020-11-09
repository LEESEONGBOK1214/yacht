package 야추_서버;

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
			System.out.println("서버의 포트는 " + serverSocket.getLocalPort());
			Socket socket = null;
			유저목록 = new ArrayList<유저>();
			while ((socket = serverSocket.accept()) != null) {
				for (유저 value : 유저목록) {
					if (socket.getPort() == value.getSocket().getPort()) {
						// 같은 유저가 있으면... 안되니까.
						// 플래그를 걸어 종료 시킴.
						break;
					}
				}
				// 같은 유저가 있을때 안들어감.
				m_OutputList.add(new PrintWriter(socket.getOutputStream()));
				유저 client = new 유저(socket);
				client.start();
//					유저목록.add(client);
//				System.out.println("게임서버 > 유저목록.size() : " + 유저목록.size());
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
