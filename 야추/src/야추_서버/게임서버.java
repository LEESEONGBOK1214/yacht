package ����_����;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ���Ӽ��� {
	public static ArrayList<����> ������� = new ArrayList<����>();
	public static ArrayList<PrintWriter> m_OutputList;

	public static void main(String[] args) {
		System.out.println("���� ������ Ȱ��ȭ �ƽ��ϴ�.");
		try {
			ServerSocket s_socket = new ServerSocket(8888); // ��Ʈ��ȣ
			m_OutputList = new ArrayList<PrintWriter>();

			while (true) {
				// ���ᵿ�� ��� ���鼭 ������ Ȯ��

				Socket c_socket = s_socket.accept();
				// ����� â�� ��Ʈ�� ������
				���� c_thread = new ����(c_socket);
				// ������ ��Ʈ�� ���� ����.
				c_thread.setM_socket(c_socket);

				�������.add(c_thread);
				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));

				c_thread.start();
			}
		} catch (IOException e) {
			System.out.println("����");
			e.printStackTrace();
		}

		System.out.println("���� ������ ��Ȱ��ȭ �ƽ��ϴ�.");
	}

	public PrintWriter serach����(Socket _socket) {
		for(PrintWriter pw : m_OutputList) {
			if()
		}
		return null;
	}
}
