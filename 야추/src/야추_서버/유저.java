package ����_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import DB.OracleDB;
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
		String id = split[2];
		String pw = split[3];
		String name = split[4];

		boolean ��� = DB.ȸ������(id, pw, name);
		if (���) {
			outprint(this.port + "ȸ�������� �Ϸ�Ǿ����ϴ�.");
		} else {
			outprint("���̵� �ߺ��˴ϴ�.");
		}
	}

	private void �α���(String[] split) {
		System.out.print("in �α��� >");
		OracleDB DB = new OracleDB();
		String id = split[2];
		String pw = split[3];
		System.out.println("split.length : " + split.length);

		String �̸� = DB.�α���(id, pw);
		if (�̸� != null) {
			System.out.println("�α��μ���");
			this.�̸� = �̸�;
			this.���̵� = id;
			this.��й�ȣ = pw;
			
			this.port = getSocket().getPort();
			���Ӽ���.get�������().add(this);
			System.out.println("���Ӽ���.get�������().size() : " + ���Ӽ���.get�������().size());
			
			outprint("�α��μ���");
			���ΰ�ħ();
		} else {
			System.out.println("�α��� ����");
		}
	}

	private void �α׾ƿ�(String[] split) {
		System.out.print("�α׾ƿ� > ");
		
		ArrayList<����> ������� = ���Ӽ���.get�������();
		System.out.println("======================");
		for (���� ���� : �������) {
			System.out.println("����.port = " + ����.port);
			System.out.println("this.port = "+this.port);
			if(this.port == ����.port) {
				�������.remove(this);
				break;
			}
		}
		System.out.println("�������.size : " + ���Ӽ���.get�������().size());
		System.out.println("======================");
		
		outprint("�α׾ƿ�����");
		
	}
	private void �游���(String[] split) {
		System.out.println("in �游��� >");
		System.out.println("split.length : " + split.length);
		
		System.out.println("�游��� in > ");
		
		
		����ȭ�� ���ȭ�� = ����ȭ��.getInstance();
		this.room = ���ȭ��.�����(this);
		if(this.room != null) {
			outprint("���������");
			���ȭ��.��ϻ��ΰ�ħ();
		}else {
			outprint("���������");
		}
		System.out.println("���� end");
		
//		this.room = �����.�����(this);
	}
	
	private void ���ΰ�ħ() {
		����ȭ�� ���ȭ�� = ����ȭ��.getInstance();
		���ȭ��.��ϻ��ΰ�ħ();
	}
	
	private void �泪����() {
		System.out.print("�泪���� > ");
		System.out.println("��size : " + ����ȭ��.get����().size());
		
		
		System.out.println("this.room : " + this.room);
		for(�� �� : ����ȭ��.get����()) {
			System.out.println("�� : " + ��);
			if(��.equals(this.room)) {
				// �� ������ ���� �� ȥ�ڶ�� �� ����.
				System.out.println("���� ���ε� �� ?");
				if(��.������.indexOf(this) != -1) { // ������ -1 ��ȯ��.
					��.������.remove(��.������.indexOf(this));
					����ȭ��.get����().remove(this.room);
					outprint("�泪����");
				}
				if(!(this.room.get������().size() == 0)) {
					����ȭ��.get����().remove(this.room);
					System.out.println("�� ����! ");
					System.out.println("��size : " + ����ȭ��.get����().size());
				}
			}
		}
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
		case "���ΰ�ħ":
			���ΰ�ħ();
			break;
		case "�泪����":
			�泪����();
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
					process(text);
				} // end of while 2
				text = null;
				break;
			} // end of while 1
			socket.close();
			���Ӽ���.get�������().remove(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			out.println(getSocket().getPort() + str);
		} catch (IOException e) {
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
