package ����_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import DB.OracleDB;
import ȭ��.����ȭ��;
import ȭ��.����ȭ��;

public class ���� extends Thread {
	�� room = null; // ������ ���� ���̴�.
	private Socket socket;
	private int port;
	private String ���̵�;
	private String ��й�ȣ;
	private String �̸�;
	private boolean ����; // true�� �� ����.
	private int ������ = 0;
	// ���ӿ� ���õ� ���� ���� // ... //
	static String ���� = "";



	String loginstatus = null;

	public void ȸ������(String[] split) {
		System.out.println("in ȸ������ >");
		OracleDB DB = new OracleDB();
		String id = split[1];
		String pw = split[2];
		String name = split[3];

		boolean ��� = DB.ȸ������(id, pw, name);
		if (���) {
			outprint(this.port + "ȸ�������� �Ϸ�Ǿ����ϴ�.");
		} else {
			outprint("���̵� �ߺ��˴ϴ�.");
		}
	}

	private void �α���(String[] split) {
		// TODO Auto-generated method stub
		System.out.print("in �α��� >");
		OracleDB DB = new OracleDB();
		String id = split[2];
		String pw = split[3];
		System.out.println("split.length : " + split.length);

		boolean ��� = DB.�α���(id, pw);
		if (���) {
			System.out.println("�α��μ���");
			this.���̵� = id;
			this.��й�ȣ = pw;
			outprint(getSocket().getPort() + "�α��μ���");
		} else {
			System.out.println("�α��� ����");
		}
	}

	private void �α׾ƿ�(String[] split) {
		System.out.print("�α׾ƿ� > ");
		OracleDB DB = new OracleDB();
		
		ArrayList<����> ������� = ���Ӽ���.�������;
		for (���� ���� : �������) {
			if(this.port == ����.port) {
				�������.remove(this);
				break;
			}
		}
		
		outprint(getSocket().getPort() + "�α׾ƿ�����");
		
	}
	private void �游���(String[] split) {
		System.out.println("in �游��� >");
		System.out.println("split.length : " + split.length);
		
		System.out.println("���� in > ");
		
		this.room = ����ȭ��.�����(this);
		if(this.room != null) {
			outprint(getSocket().getPort() + "���������");
		}else {
			outprint(getSocket().getPort() + "���������");
		}
		System.out.println("���� end");
		
		if (this.room != null)
			return;
//		this.room = �����.�����(this);
	}

	public void process(String inline) {
		System.out.print("���� > process > ");
		String split[] = inline.split("/"); // '/' ������ ���ڴ�.
		switch (split[1]) {
		case "ȸ������":
			ȸ������(split);
			break;
		case "�α���":
			�α���(split);
			break;
		case "�游���":
			�游���(split);
			break;
		case "�α׾ƿ�":
			�α׾ƿ�(split);
			break;
		}
		System.out.println();
	}


	public void run() {
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			String text;
			while (true) {
				while ((text = tmpbuffer.readLine()) != null) {
					System.out.println("������ ���� �� : " + text);
					System.out.println("���� ���ӵ� ��Ʈ �� : " + getSocket().getPort());
					this.port = getSocket().getPort();
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
	// get set ===============================================

	public ����(Socket socket) {
		this.socket = socket;
	}

	public void ������(�� _room) {
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
}
