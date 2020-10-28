package ����_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import DB.OracleDB;

class ���������� extends Thread {
	public static ArrayList<����> ������� = new ArrayList<����>();
	public static ArrayList<PrintWriter> m_OutputList;

	����������() {
		try {
			ServerSocket s_socket = new ServerSocket(8888); // ��Ʈ��ȣ
			m_OutputList = new ArrayList<PrintWriter>();

			while (true) {
				// ���ᵿ�� ��� ���鼭 ������ Ȯ��

				Socket c_socket = s_socket.accept();
				// ����� â�� ��Ʈ�� ������
				���� �������� = new ����(c_socket);
				// ������ ��Ʈ�� ���� ����.
				��������.setM_socket(c_socket);

				�������.add(��������);
				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));
			}
		} catch (IOException e) {
			System.out.println("����");
			e.printStackTrace();
		}
	}

	public static void ȸ������(String[] split) {
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

	public static ���� serach����(Socket _socket) {
		for (���� �˻����� : �������) {
			if (�˻�����.getM_socket() == _socket)
				return �˻�����;
		}
		return null;
	}
	
	private static void �α���(String[] split) {
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

	private static void �游���(String[] split) {
		System.out.println("in �游��� >");
		System.out.println("split.length : " + split.length);
		if (this.room != null)
			return;
		this.room = ����ȭ��.�����(this);
	}

	public void process(String inline) {
		String split[] = inline.split("/"); // '/' ������ ���ڴ�.
		switch (split[0]) {
		case "ȸ������":
			���Ӽ���.ȸ������(split);
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

	public static void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getM_socket().getOutputStream(), true);
			out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class ���Ӽ��� extends Thread {

	public static void main(String[] args) {
		System.out.println("���� ������ Ȱ��ȭ �ƽ��ϴ�.");
		���������� ������ = new ����������();

		������.start();
		System.out.println("���� ������ ��Ȱ��ȭ �ƽ��ϴ�.");
	}
}
