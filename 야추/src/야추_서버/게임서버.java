package ����_����;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.OracleDB;

public class ���Ӽ��� {
	private static ArrayList<����> �������;
	public static ArrayList<PrintWriter> m_OutputList;

	public static void main(String[] args) {
		try {
			m_OutputList = new ArrayList<PrintWriter>();
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("������ ���۵ƽ��ϴ�.");
			System.out.println("������ ��Ʈ�� " + serverSocket.getLocalPort());
			Socket socket = null;
			������� = new ArrayList<����>();
			while ((socket = serverSocket.accept()) != null) {
				for (���� value : �������) {
					if (socket.getPort() == value.getSocket().getPort()) {
						// ���� ������ ������... �ȵǴϱ�.
						// �÷��׸� �ɾ� ���� ��Ŵ.
						break;
					}
				}
				// ���� ������ ������ �ȵ�.
				m_OutputList.add(new PrintWriter(socket.getOutputStream()));
				���� client = new ����(socket);
				client.start();
//					�������.add(client);
//				System.out.println("���Ӽ��� > �������.size() : " + �������.size());
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

	public static ArrayList<����> get�������() {
		return �������;
	}

}
