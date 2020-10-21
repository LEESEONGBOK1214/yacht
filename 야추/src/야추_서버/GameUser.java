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
	String id;
	String pw;
	String name;
	String nickName;
	int port;

	// ���ӿ� ���õ� ���� ���� // ... //

	public GameUser() {
		// �ƹ��� ������ ���� ���� ������ ���� ��
	}

	public GameUser(Socket socket) {
		this.m_socket = socket;
	}

	public GameUser(Socket socket, String nickName) {
		this.m_socket = socket;
		this.name = nickName;
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

		String id = split[1];
		String pw = split[2];
		String name = split[3];
		
		boolean ��� = DB.ȸ������(id, pw, name);
		if (���) {
			outprint("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		}else {
			outprint("���̵� �ߺ��˴ϴ�.");
		}
		//         id        pw        name
		DB.ȸ������(split[1], split[2], split[3]);
	}

	private void �α���(String[] split) {
		// TODO Auto-generated method stub
		System.out.println("in �α��� >");
		OracleDB DB = new OracleDB();
		String id = split[1];
		String pw = split[2];
		System.out.println("split.length : " + split.length);

		boolean ��� = DB.�α���(id, pw);
		if (���) {
			System.out.println("�α��� ����");
			this.id = id;
			this.pw = pw;
			outprint("�α��μ���");
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

	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(m_socket.getOutputStream(), true);
			out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					this.port = m_socket.getPort();
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
