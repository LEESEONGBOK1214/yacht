package ����_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import DB.OracleDB;

public class GameUser extends Thread {
	GameRoom room; // ������ ���� ���̴�.
	Socket m_socket;
	String nickName;

	// ���ӿ� ���õ� ���� ���� // ... //

	public GameUser() {
		// �ƹ��� ������ ���� ���� ������ ���� ��
	}

	public GameUser(Socket socket) {
		this.m_socket = socket;
	}

	public GameUser(Socket socket, String nickName) {
		this.m_socket = socket;
		this.nickName = nickName;
	}

	public void EnterRoom(GameRoom _room) {
		_room.EnterRoom(this); // �뿡 �����Ų ��
		this.room = _room; // ������ ���� ���� ������ �����Ѵ�.(�߿�)
	}

	public void setSocket(Socket _socket) {
		m_socket = _socket;
	}

	public void ȸ������(String[] split) {
		System.out.println("in ȸ������ >");
		OracleDB DB = new OracleDB();
		DB.ȸ������(split[1], split[2], split[3]);
	}

	private void �α���(String[] split) {
		// TODO Auto-generated method stub
		System.out.println("in �α��� >");
		OracleDB DB = new OracleDB();
		if (DB.�α���(split[1], split[2], split[3])) {
			System.out.println("�α��� ����");
		} else {
			System.out.println("�α��� ����");
		}
	}

	public void process(String inline) {
		String split[] = inline.split("/"); // '/' ������ ���ڴ�.
		switch (split[0]) {
		case "ȸ������":
			ȸ������(split);
			break;
		case "�α���":
			�α���(split);
		}
	}


	public void run() {
		super.run();
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));

			String text;

			while (true) {
				while ((text = tmpbuffer.readLine()) != null) {
					System.out.println("������ ���� �� : " + text);
					System.out.println("���� ���ӵ� ��Ʈ �� : " + m_socket.getPort());
					process(text);
				} // end of while 2
				text = null;
				break;
			} // end of while 1


			GameServer.m_OutputList.remove(new PrintWriter(m_socket.getOutputStream()));
			m_socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
