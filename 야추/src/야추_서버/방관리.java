package ����_����;

import java.util.ArrayList;

import ���߰���.����ȭ��;


public class ����� {
//	private Socket m_socket;
	private static ArrayList<����ȭ��> ����; // ���� ����Ʈ


	public �����() {
		���� = new ArrayList<����ȭ��>();
	}

	public static ����ȭ�� �����(���� _owner) {
		// ������ ���� ������ �� ���(������ �������� ��)
		����ȭ�� room = new ����ȭ��(_owner);
		����.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public static void �����(����ȭ�� ����ȭ��) {
		����.remove(����ȭ��); // ���޹��� ���� �����Ѵ�.
		System.out.println("Room Deleted!");
	}

	public static ArrayList<����ȭ��> getroomList() {
		return ����;
	}

	public static int roomCount() {
		return ����.size();
	} // ���� ũ�⸦ ������


}
