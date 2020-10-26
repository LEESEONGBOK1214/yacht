package 具眠_辑滚;

import java.net.Socket;
import java.util.ArrayList;


public class 规包府 {
	private Socket m_socket;
	private static ArrayList<规> roomList; // 规狼 府胶飘

	public 规包府() {
		roomList = new ArrayList<规>();
	}

	public 规 CreateRoom() { // 冯阑 货肺 积己(后 规)
		规 room = new 规();
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public 规 CreateRoom(蜡历 _owner) {
		// 蜡历啊 规阑 积己且 锭 荤侩(蜡历啊 规厘栏肺 甸绢皑)
		规 room = new 规(_owner);
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public 规 CreateRoom(ArrayList<蜡历> _userList) {
		规 room = new 规(_userList);
		roomList.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public static void RemoveRoom(规 _room) {
		roomList.remove(_room); // 傈崔罐篮 冯阑 力芭茄促.
		System.out.println("Room Deleted!");
	}

	public static ArrayList<规> getRoomList() {
		return roomList;
	}

	public static int RoomCount() {
		return roomList.size();
	} // 冯狼 农扁甫 府畔秦


}
