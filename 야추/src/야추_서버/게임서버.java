package 야추_서버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import DB.OracleDB;

class 서버스레드 extends Thread {
	public static ArrayList<유저> 유저목록 = new ArrayList<유저>();
	public static ArrayList<PrintWriter> m_OutputList;

	서버스레드() {
		try {
			ServerSocket s_socket = new ServerSocket(8888); // 포트번호
			m_OutputList = new ArrayList<PrintWriter>();

			while (true) {
				// 연결동안 계속 돌면서 데이터 확인

				Socket c_socket = s_socket.accept();
				// 연결된 창의 포트를 가져옴
				유저 서버유저 = new 유저(c_socket);
				// 가져온 포트로 유저 생성.
				서버유저.setM_socket(c_socket);

				유저목록.add(서버유저);
				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));
			}
		} catch (IOException e) {
			System.out.println("오류");
			e.printStackTrace();
		}
	}

	public static void 회원가입(String[] split) {
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

	public static 유저 serach유저(Socket _socket) {
		for (유저 검색유저 : 유저목록) {
			if (검색유저.getM_socket() == _socket)
				return 검색유저;
		}
		return null;
	}
	
	private static void 로그인(String[] split) {
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

	private static void 방만들기(String[] split) {
		System.out.println("in 방만들기 >");
		System.out.println("split.length : " + split.length);
		if (this.room != null)
			return;
		this.room = 방목록화면.방생성(this);
	}

	public void process(String inline) {
		String split[] = inline.split("/"); // '/' 단위로 끊겠다.
		switch (split[0]) {
		case "회원가입":
			게임서버.회원가입(split);
			break;
		case "로그인":
			로그인(split);
			break;
		case "방만들기":
			방만들기(split);
			break;
		}
	}


	public void run() {
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(getM_socket().getInputStream()));
			String text;
			while (true) {
				while ((text = tmpbuffer.readLine()) != null) {
					System.out.println("서버로 들어온 값 : " + text);
					System.out.println("서버 접속된 포트 값 : " + getM_socket().getPort());
					process(text);
				} // end of while 2
				text = null;
				break;
			} // end of while 1

			게임서버.m_OutputList.remove(new PrintWriter(getM_socket().getOutputStream()));
			getM_socket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getM_socket().getOutputStream(), true);
			out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class 게임서버 extends Thread {

	public static void main(String[] args) {
		System.out.println("게임 서버가 활성화 됐습니다.");
		서버스레드 스레드 = new 서버스레드();

		스레드.start();
		System.out.println("게임 서버가 비활성화 됐습니다.");
	}
}
