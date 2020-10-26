package 야추_서버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import DB.OracleDB;

public class 유저 extends Thread {
	방 room; // 유저가 속한 룸이다.
	Socket m_socket;
	private String 아이디;
	private String 비밀번호;
	private String 이름;
	private boolean 차례; // true면 내 차례.
	private int 내점수 = 0;
	// 게임에 관련된 변수 설정 // ... //

	public 유저(String nickName) {
		this.이름 = nickName;
	}

	public 유저(Socket socket) {
		this.m_socket = socket;
	}

	public 유저(Socket socket, String nickName) {
		this.m_socket = socket;
		this.이름 = nickName;
	}

	public void EnterRoom(방 _room) {
		_room.EnterRoom(this); // 룸에 입장시킨 후
		this.room = _room; // 유저가 속한 방을 룸으로 변경한다.(중요)
	}

	public void setSocket(Socket _socket) {
		m_socket = _socket;
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

	public void 회원가입(String[] split) {
		System.out.println("in 회원가입 >");
		OracleDB DB = new OracleDB();
		String id = split[1];
		String pw = split[2];
		String name = split[3];
		
		boolean 결과 = DB.회원가입(id, pw, name);
		if (결과) {
			outprint("회원가입이 완료되었습니다.");
		}else {
			outprint("아이디가 중복됩니다.");
		}
	}

	private void 로그인(String[] split) {
		// TODO Auto-generated method stub
		System.out.println("in 로그인 >");
		OracleDB DB = new OracleDB();
		String id = split[1];
		String pw = split[2];
		System.out.println("split.length : " + split.length);

		boolean 결과 = DB.로그인(id, pw);
		if (결과) {
			System.out.println("로그인 성공");
			this.아이디 = id;
			this.비밀번호 = pw;
			outprint("로그인성공");
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
			break;
		}
	}

	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(m_socket.getOutputStream(), true);
			out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
//					System.out.println("설마 계속 돌고있나?");
				} // end of while 2
				text = null;
				break;
			} // end of while 1


			게임서버.m_OutputList.remove(new PrintWriter(m_socket.getOutputStream()));
			m_socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
