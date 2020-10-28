package 야추_서버;

import java.net.Socket;

import 야추게임.게임화면;

public class 유저 {
	게임화면 room = null; // 유저가 속한 룸이다.
	private Socket m_socket;
	private String 아이디;
	private String 비밀번호;
	private String 이름;
	private boolean 차례; // true면 내 차례.
	private int 내점수 = 0;
	// 게임에 관련된 변수 설정 // ... //

	public 유저(Socket socket) {
		this.m_socket = socket;
	}


	public void 방입장(게임화면 _room) {
		_room.방입장(this); // 룸에 입장시킨 후
		this.room = _room; // 유저가 속한 방을 룸으로 변경한다.(중요)
	}

	public int get내점수() {
		return 내점수;
	}

	public void set내점수(int 내점수) {
		this.내점수 = 내점수;
	}

	public boolean is차례() {
		return 차례;
	}

	public void set차례(boolean 차례) {
		this.차례 = 차례;
	}

	public String get아이디() {
		return 아이디;
	}

	public void set아이디(String 아이디) {
		this.아이디 = 아이디;
	}

	public String get비밀번호() {
		return 비밀번호;
	}

	public void set비밀번호(String 비밀번호) {
		this.비밀번호 = 비밀번호;
	}

	public String get이름() {
		return 이름;
	}

	public void set이름(String 이름) {
		this.이름 = 이름;
	}

	public Socket getM_socket() {
		return m_socket;
	}

	public void setM_socket(Socket m_socket) {
		this.m_socket = m_socket;
	}


}
