package 야추_서버;
//package 야추_서버;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class 방아님 extends Thread {
//	유저 user_A; // 방장
//	유저 user_B; // 방장
//	String roomName; // 방 이름
//
//	public 방아님() { // 아무도 없는 방을 생성할 때
//		userList = new ArrayList<유저>();
//	}
//
//	public 방아님(유저 _user) {
//		// 유저가 방을 만들때
//		userList = new ArrayList<유저>();
//		userList.add(_user); // 유저를 추가시킨 후
//		this.user_A = _user; // 방장을 유저로 만든다.
//	}
//
//
//	public void EnterRoom(유저 _user) {
//		user_B = _user;
//	}
//
//	public void ExitRoom(유저 _user) { 
//		_user = null;
//		if (user_A == null && user_B == null) {
//	    //모든 인원이 다 방을 나갔다면 
//			방관리.RemoveRoom(this); 
//			// 이 방을 제거한다. 
//			return; 
//		} 
//		if (userList.size() < 2) {
//			// 방에 남은 인원이 1명 이하라
//			this.user_A = userList.get(0);
//			// 리스트의 첫번째 유저가 방장이 된다. return;
//		}
//	}
//
//	// 게임 로직
//	@SuppressWarnings("unused")
//	public void Broadcast(byte[] data) {
//		for (유저 user : userList) {
//			// 방에 속한 유저의 수만큼 반복
//			// 각 유저에게 데이터를 전송하는 메서드 호출~
//			// ex) user.SendData(data);
//			try {
//				user.getM_socket().getOutputStream().write(data);
//				// 이런식으로 바이트배열을 보낸다. //
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void SetOwner(유저 _user) {
//		this.user_A = _user; // 특정 사용자를 방장으로 변경한다.
//	}
//
//	public void SetRoomName(String _name) {
//		// 방 이름을 설정
//		this.roomName = _name;
//	}
//
//	public String GetRoomName() {
//		// 방 이름을 가져옴
//		return roomName;
//	}
//
//	public int GetUserSize() { //
//		// 유저의 수를 리턴
//		return userList.size();
//	}
//
//	public 유저 GetOwner() {
//		// 방장을 리턴
//		return user_A;
//	}
//}
