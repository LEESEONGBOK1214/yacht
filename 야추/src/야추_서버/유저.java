package 야추_서버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import DB.OracleDB;
import 화면.게임화면;

public class 유저 extends Thread {
	게임화면 room = null; // 유저가 속한 룸이다.
	private Socket socket;
	private String 아이디;
	private String 비밀번호;
	private String 이름;
	private boolean 차례; // true면 내 차례.
	private int 내점수 = 0;
	// 게임에 관련된 변수 설정 // ... //
	static String 응답 = "";



	String loginstatus = null;

	public void 회원가입(String[] split) {
		System.out.println("in 회원가입 >");
		OracleDB DB = new OracleDB();
		String id = split[1];
		String pw = split[2];
		String name = split[3];

		boolean 결과 = DB.회원가입(id, pw, name);
		if (결과) {
			outprint("회원가입이 완료되었습니다.");
		} else {
			outprint("아이디가 중복됩니다.");
		}
	}

	private void 로그인(String[] split) {
		// TODO Auto-generated method stub
		System.out.print("in 로그인 >");
		OracleDB DB = new OracleDB();
		String id = split[1];
		String pw = split[2];
		System.out.println("split.length : " + split.length);

		boolean 결과 = DB.로그인(id, pw);
		if (결과) {
			System.out.println("로그인성공");
			this.아이디 = id;
			this.비밀번호 = pw;
			outprint(getSocket().getPort() + "로그인성공");
		} else {
			System.out.println("로그인 실패");
		}
	}

	private void 로그아웃(String[] split) {
		System.out.print("로그아웃 > ");
		OracleDB DB = new OracleDB();
		
		ArrayList<유저> 유저목록 = 게임서버.유저목록;
		for (유저 유저 : 유저목록) {
			
		}
	}
	private void 방만들기(String[] split) {
		System.out.println("in 방만들기 >");
		System.out.println("split.length : " + split.length);
		if (this.room != null)
			return;
//		this.room = 방관리.방생성(this);
	}

	public void process(String inline) {
		System.out.print("서버 > process > ");
		String split[] = inline.split("/"); // '/' 단위로 끊겠다.
		switch (split[0]) {
		case "회원가입":
			회원가입(split);
			break;
		case "로그인":
			로그인(split);
			break;
		case "방만들기":
//			방만들기(split);
			break;
		case "로그아웃":
			로그아웃(split);
			break;
		}
	}


	public void run() {
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			String text;
			while (true) {
				while ((text = tmpbuffer.readLine()) != null) {
					System.out.println("서버로 들어온 값 : " + text);
					System.out.println("서버 접속된 포트 값 : " + getSocket().getPort());
					process(text);
				} // end of while 2
				text = null;
				break;
			} // end of while 1

			getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// get set ===============================================

	public 유저(Socket socket) {
		this.socket = socket;
	}

	public void 방입장(게임화면 _room) {
//		_room.방입장(this); // 룸에 입장시킨 후
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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket _socket) {
		this.socket = _socket;
	}
}
