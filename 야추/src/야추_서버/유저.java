package ����_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import DB.OracleDB;
import ȭ��.����ȭ��;

public class ���� extends Thread {
	����ȭ�� room = null; // ������ ���� ���̴�.
	private Socket socket;
	private String ���̵�;
	private String ��й�ȣ;
	private String �̸�;
	private boolean ����; // true�� �� ����.
	private int ������ = 0;
	// ���ӿ� ���õ� ���� ���� // ... //
	static String ���� = "";

	public ����(Socket socket) {
		this.socket = socket;
	}

	public void ������(����ȭ�� _room) {
//		_room.������(this); // �뿡 �����Ų ��
		this.room = _room; // ������ ���� ���� ������ �����Ѵ�.(�߿�)
	}

	public int get������() {
		return ������;
	}

	public void set������(int ������) {
		this.������ = ������;
	}

	public boolean is����() {
		return ����;
	}

	public void set����(boolean ����) {
		this.���� = ����;
	}

	public String get���̵�() {
		return ���̵�;
	}

	public void set���̵�(String ���̵�) {
		this.���̵� = ���̵�;
	}

	public String get��й�ȣ() {
		return ��й�ȣ;
	}

	public void set��й�ȣ(String ��й�ȣ) {
		this.��й�ȣ = ��й�ȣ;
	}

	public String get�̸�() {
		return �̸�;
	}

	public void set�̸�(String �̸�) {
		this.�̸� = �̸�;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket _socket) {
		this.socket = _socket;
	}

	String loginstatus = null;

	public void ȸ������(String[] split) {
		System.out.println("in ȸ������ >");
		OracleDB DB = new OracleDB();
		String id = split[1];
		String pw = split[2];
		String name = split[3];

		boolean ��� = DB.ȸ������(id, pw, name);
		if (���) {
			outprint("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		} else {
			outprint("���̵� �ߺ��˴ϴ�.");
		}
	}
	
	pu


	public void process(String inline) {
		String split[] = inline.split("/"); // '/' ������ ���ڴ�.
		switch (split[0]) {
		case "ȸ������":
			ȸ������(split);
			break;
		case "�α���":
			�α���(split);
			break;
		case "�游���":
//			�游���(split);
			break;
		}
	}

	public void run() {
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			String text;
			while (true) {
				while ((text = tmpbuffer.readLine()) != null) {
					System.out.println("������ ���� �� : " + text);
					System.out.println("���� ���ӵ� ��Ʈ �� : " + getSocket().getPort());
					process(text);
				} // end of while 2
				text = null;
				break;
			} // end of while 1

			getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
