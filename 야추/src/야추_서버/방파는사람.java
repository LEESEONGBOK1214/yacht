package ����_����;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//public class MainServer {
//	// 1. ���� ��� ��� �ۼ��ϱ�.
//	// �������� ������ ���� Ŭ���̾�Ʈ�� ����ϴ� ���α׷���.
//	// ��� Ŭ���� : "ServerSocket", "Socket"
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		try {
//			ServerSocket s_socket = new ServerSocket(8888);
//			// serversocket ���ϸ� = new serversocket(��Ʈ��ȣ);
//
//			// --------------------------------------------------------
//			Socket c_socket = s_socket.accept();
//			// accept : Ŭ���̾�Ʈ�� �����°��� ����ϴ� ����.
//			// => Ŭ���̾�Ʈ�� ��Ʈ��ȣ(8888) ��Ʈ�� ������ �õ��Ѵٸ�,
//			// accept�� ��⸦ Ǯ��, Ŭ���̾�Ʈ�� �����Ű��
//			// "Socket" Ŭ������ �����Ͽ� ��ȯ�ϰ� �˴ϴ�.
//			// �׷��� "accept" �޼ҵ�� ���� ���� "c_socket"��
//			// �ٷ� Ŭ���̾�Ʈ�� 1:1 ����� �����Դϴ�.
//			// => c_socket���� Ŭ���̾�Ʈ�� ����� ����.
//			// --------------------------------------------------------
//
//			// --------------------------------------------------------
//			OutputStream output_data = c_socket.getOutputStream();
//			// c_socket.getOutputStream()
//			// Ŭ���̾�Ʈ�� ����� �������κ��� "OUtputStream" ��ü�� �����ɴϴ�.
//			// �� "OutputStream" ��ü�� �������� ������ �޼����� �ۼ��Ͽ�
//			// �����ϱ⸸ �ϸ� �˴ϴ�.
//			// --------------------------------------------------------
//
//			// --------------------------------------------------------
//			String sendDataString = "Welcome to My Server";
//			output_data.write(sendDataString.getBytes());
//			// �츮�� ������ "Welcome to My Server"��� �޼����� "write" �޼ҵ带
//			// �� �����ϴ� ����Դϴ�.
//			// --------------------------------------------------------
//			s_socket.close();
//			c_socket.close();
//
//			// ��� :
//			// 1. ServerSocket�� Ŭ���̾�Ʈ �����û�� ���������� ���.
//			// 2. Ŭ���̾�Ʈ�� ���� ��û�� �ִٸ� Socket�� �����Ͽ� �����Ŵ
//			// 3. ����� Socket���� �����͸� Stream(��Ʈ��) ���·� �ְ� �޴´�.
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}

public class ���Ĵ»�� {
	public static void main(String[] args) {
		System.out.println("���� �����Դϴ�.");
		try {
			ServerSocket s_socket = new ServerSocket(8888);
			
			Socket c_socket = s_socket.accept();
			
			ReceiveThread rec_thread = new ReceiveThread();
			rec_thread.setSocket(c_socket);
			
			SendThread send_thread = new SendThread();
			send_thread.setSocket(c_socket);
			
			rec_thread.start();
			send_thread.start();



		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
