package �̻��Ŭ������;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

	public static ArrayList<PrintWriter> m_OutputList;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("������ ���۵ƽ��ϴ�.");
		m_OutputList = new ArrayList<PrintWriter>();
		// ArrayList�� m_OutputList ��ü�� �����ϰ� �Ҵ��ϰԵ�.
		// �� ��ü�� ������ ������ �����ϴ� Ŭ���̾�Ʈ�� "Output"�� ��Ƶ� �迭.
		// Ŭ���̾�Ʈ�� �����ϰ� �Ǹ�, Ŭ���̾�Ʈ�� "OutputStream"��
		// �� ��ü�� ��Ƶΰ� �˴ϴ�.
		// �� �ڵ�� �ؿ��ִ� 	m_OutputList.add �κ�.
		try {
			ServerSocket s_socket = new ServerSocket(8888);
			// ���� ����, ��Ʈ��ȣ 8888��ȣ�� ��.
			
			while(true)
			{
				Socket c_socket = s_socket.accept();
//				ClientManagerThread c_thread = new ClientManagerThread();
//				c_thread.setSocket(c_socket);
//				// �������Ͽ��� Ŭ���̾�Ʈ�� �����Ǹ� "Socket"Ŭ����
//				// ��, Ŭ���̾�Ʈ ������ �����Ͽ� "ClientManagerThread"�� ��Ƽ�
//				// �ش� �����带 �����ϰ� ��. -> c_thread.start();
//				
//				
//				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));
//				// �� �κп��� OutputStream���� ��Ƶδ� ��ɾ�.
//				
//				
//				c_thread.start();
				
				
				// ClientManagerThread ���Ϸ�.
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}