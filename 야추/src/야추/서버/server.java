package ����.����;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.OracleDB;

public class server {
	private static ArrayList<user> �������;
	public static ArrayList<PrintWriter> m_OutputList;

	public static void main(String[] args) {
		try {
			m_OutputList = new ArrayList<PrintWriter>();
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("������ ���۵ƽ��ϴ�.");
//			System.out.println("���� .." + serverSocket.);
			System.out.println("������ ��Ʈ�� " + serverSocket.getLocalPort());
			Socket socket = null;
			������� = new ArrayList<user>();
			while ((socket = serverSocket.accept()) != null) {
				boolean flag = true;
				for (user user : �������) {
					if (socket.getPort() == user.getSocket().getPort()) {
						flag = false;
						break;
					}
				}
				if (flag) {
					m_OutputList.add(new PrintWriter(socket.getOutputStream()));
					user client = new user(socket);
					client.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				new OracleDB().��������();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static ArrayList<user> get�������() {
		return �������;
	}

}
