package ����.����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import DB.OracleDB;
import ����.ȭ��.����ȭ��;

public class ���� extends Thread {
	�� room = null; // ������ ���� ���̴�.
	private Socket socket;
	private int port;
	private String ���̵�;
	private String ��й�ȣ;
	private String �̸�;
	// ���ӿ� ���õ� ���� ���� // ... //
	static String ���� = "";
	private static ArrayList<��> ���� = ����ȭ��.get����();
	String loginstatus = null;

	public void ȸ������(String[] split) {
		System.out.println("in ȸ������ >");
		OracleDB DB = new OracleDB();
		String id = split[2];
		String pw = split[3];
		String name = split[4];

		boolean ��� = false;
		try {
			��� = DB.ȸ������(id, pw, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (���) {
			outprint("ȸ�������� �Ϸ�Ǿ����ϴ�.");
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

		String �̸� = null;
		try {
			�̸� = DB.�α���(id, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (�̸� != null) {
			System.out.println("�α��μ���");
			this.�̸� = �̸�;
			this.���̵� = id;
			this.��й�ȣ = pw;

			this.port = getSocket().getPort();
			���Ӽ���.get�������().add(this);
			System.out.println("���Ӽ���.get�������().size() : " + ���Ӽ���.get�������().size());

			outprint("�α��μ���");
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
			System.out.println("this.port = " + this.port);
			if (this.port == ����.port) {
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

		this.room = ����ȭ��.�����(this, split[2]);
		if (this.room != null) {
			outprint("���������");
			broadCast("�������Ʈ");
		} else {
			outprint("���������");
		}
		System.out.println("���� end");

//		this.room = �����.�����(this);
	}

	private void ���ΰ�ħ() {
		outprint("���ΰ�ħ");
	}

	private void �泪����() {
		System.out.print("�泪���� > ");

		�� �� = this.room;
		if ((��.������.indexOf(this) == 0)) {
			try {
				new OracleDB().�����(this.port);
			} catch (SQLException e) {
				System.out.println("���������..");
				e.printStackTrace();
			}
			System.out.println("������ ����..!");
			get����().remove(this.room);
			broadCast("�������Ʈ");
			// ������ �������� ���� �濡 �ִ� ���� ��������.
			if (��.������.size() > 1) {
				outprint(��.������.get(1).socket.getPort(), "�泪����");
				��.������.get(1).room = null;
			}
		} else {
			try {
				new OracleDB().�泪����(this.room.get������().get(0).port, this.port);
				outprint(this.room.������.get(0).port, "��������");
			} catch (SQLException e) {
				System.out.println("���������..");
//				e.printStackTrace();
			}
		}
		outprint("�泪����");
		broadCast("�������Ʈ");
		this.room = null;
	}

	public void ������(String[] split) {
//		_room.������(this); // �뿡 �����Ų ��
		Iterator<��> Iter���� = ����.iterator();
		while (Iter����.hasNext()) {
			�� in_room = Iter����.next();
			int ������Ʈ = Integer.parseInt(split[2]);
			int �˻���Ʈ = in_room.������.get(0).socket.getPort();
//			System.out.println("���� �˻����� ������ ��Ʈ��ȣ : " + �˻���Ʈ);
//			System.out.println("������ ��Ʈ��ȣ : " + ������Ʈ);
			if (������Ʈ == �˻���Ʈ) { // split[2]�� ���� ������ �Ѿ��,
				this.room = in_room; // ������ ���� ���� ������ �����Ѵ�.(�߿�)
				in_room.������.add(this);
				try {
					new OracleDB().������(������Ʈ, this.port);
				} catch (SQLException e) {
					e.printStackTrace();
//					System.out.println("\n\n\n\n���������\n\n\n\n");
				}
//				System.out.println("======================");
//				for (���� ���� : ����ȭ��.get����().get(����ȭ��.get����().indexOf(in_room)).������) {
//					System.out.println(����.port);
//				}
//				System.out.println("======================");
				outprint("������/" + in_room.������.get(0).�̸�);
				outprint(������Ʈ, "��������/" + this.�̸�);
				broadCast("�������Ʈ");
			}
		}

	}

	private void ���ӽ���() {
//		System.out.println("======================");
		int ������ = new Random().nextInt(2);
		String �̸������� = this.room.������.get(0).�̸� + "/" + this.room.������.get(1).�̸�;
		outprint(this.room.������.get(1).port, "���ӽ�����/" + ������ + "/" + �̸������� + "/" + 1);
		outprint("���ӽ�����/" + (������ + 1) % 2 + "/" + �̸������� + "/" + 0);
//		System.out.println("======================");
	}

	private void ������(String split[]) {
		// split[2] = �ֻ��� ������ ���ݼ�.
		if (this.room.������.get(0).port == this.port) {
			outprint(this.room.������.get(1).port, "����������/" + split[2]);
		} else {
			outprint(this.room.������.get(0).port, "����������/" + split[2]);
		}
	}
	
	private void �ֻ�������(String[] split) {
		if (this.room.������.get(0).port == this.port) {
			outprint(this.room.������.get(1).port, "�ֻ���������/" + split[2]);
		} else {
			outprint(this.room.������.get(0).port, "�ֻ���������/" + split[2]);
		}
	}
	
	private void ����������() {
		if (this.room.������.get(0).port == this.port) {
			outprint(this.room.������.get(1).port, "����������/");
		} else {
			outprint(this.room.������.get(0).port, "����������/");
		}
	}

	private void ����������() {
		if (this.room.������.get(0).port == this.port) {
			outprint(this.room.������.get(1).port, "����������/");
		} else {
			outprint(this.room.������.get(0).port, "����������/");
		}
	}

	private void ������(String split[]) {
		if (this.room.������.get(0).port == this.port) {
			outprint(this.room.������.get(1).port, "����/" + split[2] + "/" + split[3] + "/");
		} else {
			outprint(this.room.������.get(0).port, "����/" + split[2] + "/" + split[3] + "/");
		}
		outprint("������/");
	}

	private void ����������() {
		if (this.room.������.get(0).port == this.port) {
			outprint(this.room.������.get(1).port, "����������/");
		} else {
			outprint(this.room.������.get(0).port, "����������/");
		}
	}

	private void ��������() {
		outprint(this.room.������.get(0).port, "��������/");
		outprint(this.room.������.get(1).port, "��������/");
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
		case "������":
			������(split);
			break;
		case "â����":
			����();
			break;
		case "���ӽ���":
			���ӽ���();
			break;
		case "������":
			������(split);
			break;
		case "�ֻ�������":
			�ֻ�������(split);
			break;
		case "����������":
			����������();
			break;
		case "����������":
			����������();
			break;
		case "��������":
			������(split);
			break;
		case "����������":
			����������();
			break;
		case "��������":
			��������();
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
		} catch (IOException e) {
			System.out.println(port + " - " + �̸� + "���� ����!");
		}

	}

	private void ����() {
		// DB���� �����ϰ� �������� �����ؾ���.
		try {
			if (this.room != null)
				if (this.room.������.get(0).port == this.port) {
					outprint(this.room.������.get(1).port, "������");
					this.room.������.get(1).room = null;
				} else {
					outprint(this.room.������.get(0).port, "������");
					this.room.������.get(0).room = null;
				}
			this.room = null;
			new DB.OracleDB().�����(this.room.������.get(0).port);
			broadCast("�������Ʈ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		���Ӽ���.get�������().remove(this);
	}

	public void broadCast(String str) {
		PrintWriter out;
		Iterator<PrintWriter> opw = ���Ӽ���.m_OutputList.iterator();
		while (opw.hasNext()) {
			out = new PrintWriter(opw.next(), true);
			out.println("broadCast/" + str);
		}
		System.out.println("broadCast/" + str);
	}

	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			System.out.print("�������� > ");
			System.out.println(getSocket().getPort() + "/" + str);
			out.println(getSocket().getPort() + "/" + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void outprint(int port, String str) {
		PrintWriter out;
		Iterator<PrintWriter> opw = ���Ӽ���.m_OutputList.iterator();
		while (opw.hasNext()) {
			out = new PrintWriter(opw.next(), true);
			out.println(port + "/" + str);
		}
		System.out.println(port + "/" + str);
	}
	// get set ===============================================

	public ����(Socket socket) {
		this.socket = socket;
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

	public static ArrayList<��> get����() {
		return ����;
	}
}
