package ����_����;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {

	public static ArrayList<PrintWriter> m_OutputList;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("���߼���");
		m_OutputList = new ArrayList<PrintWriter>();
		// ������ ��Ƶ� ��̸���Ʈ.
		try {
			ServerSocket s_socket = new ServerSocket(8888); // ��Ʈ��ȣ
			
			while(true) {
				//���ᵿ�� ��� ���鼭 ������ Ȯ��
				
				Socket c_socket = s_socket.accept();
				ServerThread c_thread = new ServerThread();
				c_thread.setSocket(c_socket);
				
				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));
				
				c_thread.start();
			}
		} catch (IOException e) {
			System.out.println("����");
			e.printStackTrace();
		}
	}

}
