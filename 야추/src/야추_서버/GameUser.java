package 야추_서버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import DB.OracleDB;

public class GameUser extends Thread {
	GameRoom room; // 유저가 속한 룸이다.
	Socket m_socket;
	String nickName;

	// 게임에 관련된 변수 설정 // ... //

	public GameUser() {
		// 아무런 정보가 없는 깡통 유저를 만들 때
	}

	public GameUser(Socket socket) {
		this.m_socket = socket;
	}

	public GameUser(Socket socket, String nickName) {
		this.m_socket = socket;
		this.nickName = nickName;
	}

	public void EnterRoom(GameRoom _room) {
		_room.EnterRoom(this); // 룸에 입장시킨 후
		this.room = _room; // 유저가 속한 방을 룸으로 변경한다.(중요)
	}

	public void setSocket(Socket _socket) {
		m_socket = _socket;
	}

	public void 회원가입(String[] split) {
		System.out.println("in 회원가입 >");
		OracleDB DB = new OracleDB();
		DB.회원가입(split[1], split[2], split[3]);
	}

	private void 로그인(String[] split) {
		// TODO Auto-generated method stub
		System.out.println("in 로그인 >");
		OracleDB DB = new OracleDB();
		if (DB.로그인(split[1], split[2], split[3])) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
	}

	public void process(String inline) {
		String split[] = inline.split("/"); // '/' 단위로 끊겠다.
		switch (split[0]) {
		case "회원가입":
			회원가입(split);
			break;
		case "로그인":
			로그인(split);
		}
	}


	public void run() {
		super.run();
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));

			String text;

			while (true) {
				while ((text = tmpbuffer.readLine()) != null) {
					System.out.println("서버로 들어온 값 : " + text);
					System.out.println("서버 접속된 포트 값 : " + m_socket.getPort());
					process(text);
				} // end of while 2
				text = null;
				break;
			} // end of while 1


			GameServer.m_OutputList.remove(new PrintWriter(m_socket.getOutputStream()));
			m_socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
