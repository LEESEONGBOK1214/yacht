package ����_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import DB.OracleDB;
import ���߰���.����ȭ��;

public class ���� extends Thread {
	����ȭ�� room = null; // ������ ���� ���̴�.
	private Socket m_socket;
	private String ���̵�;
	private String ��й�ȣ;
	private String �̸�;
	private boolean ����; // true�� �� ����.
	private int ������ = 0;
	// ���ӿ� ���õ� ���� ���� // ... //

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
			this.���̵� = id;
			this.��й�ȣ = pw;
			outprint("�α��μ���");
		} else {
			System.out.println("�α��� ����");
		}
	}

	private void �游���(String[] split) {
		System.out.println("in �游��� >");
		System.out.println("split.length : " + split.length);
		if (this.room != null)
			return;
		this.room = �����.�����(this);
	}

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
			�游���(split);
			break;
		}
	}


	public void run() {
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(getM_socket().getInputStream()));
			String text;
			while (true) {
				while ((text = tmpbuffer.readLine()) != null) {
					System.out.println("������ ���� �� : " + text);
					System.out.println("���� ���ӵ� ��Ʈ �� : " + getM_socket().getPort());
					process(text);
				} // end of while 2
				text = null;
				break;
			} // end of while 1


			���Ӽ���.m_OutputList.remove(new PrintWriter(getM_socket().getOutputStream()));
			getM_socket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getM_socket().getOutputStream(), true);
			out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ����(Socket socket) {
		this.m_socket = socket;
	}


	public void ������(����ȭ�� _room) {
		_room.������(this); // �뿡 �����Ų ��
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

	public Socket getM_socket() {
		return m_socket;
	}

	public void setM_socket(Socket m_socket) {
		this.m_socket = m_socket;
	}


}
