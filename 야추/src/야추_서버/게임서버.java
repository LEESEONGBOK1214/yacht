package ����_����;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ���Ӽ��� {
	static ArrayList<����> ������� = new ArrayList<����>();

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("������ ���۵ƽ��ϴ�.");
			System.out.println("������ ��Ʈ�� " + serverSocket.getLocalPort());
			Socket socket = null;
			while ((socket = serverSocket.accept()) != null) {
				boolean flag = true;
				for (���� value : �������) {
					if (socket.getPort() == value.getSocket().getPort()) {
						// ���� ������ ������... �ȵǴϱ�.
						flag = false;
						// �÷��׸� �ɾ� ���� ��Ŵ.
						break;
					}
				}
				// ���� ������ ������ �ȵ�.
				if (flag) {
					���� client = new ����(socket);
					client.start();
					�������.add(client);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
