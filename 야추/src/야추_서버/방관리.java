package 야추_서버;

import java.util.ArrayList;

import 야추게임.게임화면;


public class 방관리 {
//	private Socket m_socket;
	private static ArrayList<게임화면> 방목록; // 방의 리스트


	public 방관리() {
		방목록 = new ArrayList<게임화면>();
	}

	public static 게임화면 방생성(유저 _owner) {
		// 유저가 방을 생성할 때 사용(유저가 방장으로 들어감)
		게임화면 room = new 게임화면(_owner);
		방목록.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public static void 방삭제(게임화면 게임화면) {
		방목록.remove(게임화면); // 전달받은 룸을 제거한다.
		System.out.println("Room Deleted!");
	}

	public static ArrayList<게임화면> getroomList() {
		return 방목록;
	}

	public static int roomCount() {
		return 방목록.size();
	} // 룸의 크기를 리턴해


}
