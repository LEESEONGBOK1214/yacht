package 야추.서버;

import java.util.ArrayList;

public class room {
	String 제목;
	ArrayList<user> 유저들;
	boolean 시작;
	
	public room(user _owner, String title) {
		유저들 = new ArrayList<user>();
		유저들.add(_owner);
		this.제목 = title;
		this.시작 = false;
	}

	public room() {
	}

	public String 입장(user 유저) {
		if(유저들.size()<2) {
			유저들.add(유저);
			return "방 생성성공";
		}else {
			return "방이 꽉찼습니다.";
		}
		
	}
	
	
	// get set
	public String get방장이름() {
		return 유저들.get(0).get이름();
	}
	
	public ArrayList<user> get유저들() {
		return 유저들;
	}

	public boolean is대기중() {
		return 시작;
	}

	public void set대기중(boolean 값) {
		this.시작 = 값;
	}

	public String get제목() {
		return 제목;
	}

	public void set제목(String 제목) {
		this.제목 = 제목;
	}
}
