package ����_����;

import java.net.Socket;
import java.util.ArrayList;


public class ����� {
	private Socket m_socket;
	private static ArrayList<��> roomList; // ���� ����Ʈ

	public �����() {
		roomList = new ArrayList<��>();
	}

	public �� CreateRoom() { // ���� ���� ����(�� ��)
		�� room = new ��();
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public �� CreateRoom(���� _owner) {
		// ������ ���� ������ �� ���(������ �������� ��)
		�� room = new ��(_owner);
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public �� CreateRoom(ArrayList<����> _userList) {
		�� room = new ��(_userList);
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public static void RemoveRoom(�� _room) {
		roomList.remove(_room); // ���޹��� ���� �����Ѵ�.
		System.out.println("Room Deleted!");
	}

	public static ArrayList<��> getRoomList() {
		return roomList;
	}

	public static int RoomCount() {
		return roomList.size();
	} // ���� ũ�⸦ ������


}
