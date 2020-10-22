package ����_����;

import java.net.Socket;
import java.util.ArrayList;


public class RoomManager {
	private Socket m_socket;
	private static ArrayList<GameRoom> roomList; // ���� ����Ʈ

	public RoomManager() {
		roomList = new ArrayList<GameRoom>();
	}

	public GameRoom CreateRoom() { // ���� ���� ����(�� ��)
		GameRoom room = new GameRoom();
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public GameRoom CreateRoom(GameUser _owner) {
		// ������ ���� ������ �� ���(������ �������� ��)
		GameRoom room = new GameRoom(_owner);
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public GameRoom CreateRoom(ArrayList<GameUser> _userList) {
		GameRoom room = new GameRoom(_userList);
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public static void RemoveRoom(GameRoom _room) {
		roomList.remove(_room); // ���޹��� ���� �����Ѵ�.
		System.out.println("Room Deleted!");
	}

	public static ArrayList<GameRoom> getRoomList() {
		return roomList;
	}

	public static int RoomCount() {
		return roomList.size();
	} // ���� ũ�⸦ ������


}
