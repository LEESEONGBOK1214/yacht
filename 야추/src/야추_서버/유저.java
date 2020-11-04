package 야추_서버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import DB.OracleDB;
import 야추메인.yatch;
import 화면.방목록화면;

public class 유저 extends Thread {
	방 room = null; // 유저가 속한 룸이다.
	private Socket socket;
	private int port;
	private String 아이디;
	private String 비밀번호;
	private String 이름;
	private boolean 차례; // true면 내 차례.
	private int 내점수 = 0;
	// 게임에 관련된 변수 설정 // ... //
	static String 응답 = "";
	private static ArrayList<방> 방목록 = 방목록화면.get방목록();
	String loginstatus = null;

	public void 회원가입(String[] split) {
		System.out.println("in 회원가입 >");
		OracleDB DB = new OracleDB();
		String id = split[2];
		String pw = split[3];
		String name = split[4];

		boolean 결과 = false;
		try {
			결과 = DB.회원가입(id, pw, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (결과) {
			outprint("회원가입이 완료되었습니다.");
		} else {
			outprint("아이디가 중복됩니다.");
		}
	}

	private void 로그인(String[] split) {
		System.out.print("in 로그인 >");
		OracleDB DB = new OracleDB();
		String id = split[2];
		String pw = split[3];
		System.out.println("split.length : " + split.length);

		String 이름 = null;
		try {
			이름 = DB.로그인(id, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (이름 != null) {
			System.out.println("로그인성공");
			this.이름 = 이름;
			this.아이디 = id;
			this.비밀번호 = pw;

			this.port = getSocket().getPort();
			게임서버.get유저목록().add(this);
			System.out.println("게임서버.get유저목록().size() : " + 게임서버.get유저목록().size());

			outprint("로그인성공");
		} else {
			System.out.println("로그인 실패");
		}
	}

	private void 로그아웃(String[] split) {
		System.out.print("로그아웃 > ");

		ArrayList<유저> 유저목록 = 게임서버.get유저목록();
		System.out.println("======================");
		for (유저 유저 : 유저목록) {
			System.out.println("유저.port = " + 유저.port);
			System.out.println("this.port = " + this.port);
			if (this.port == 유저.port) {
				유저목록.remove(this);
				break;
			}
		}
		System.out.println("유저목록.size : " + 게임서버.get유저목록().size());
		System.out.println("======================");

		outprint("로그아웃성공");

	}

	private void 방만들기(String[] split) {
		System.out.println("in 방만들기 >");
		System.out.println("split.length : " + split.length);

		System.out.println("방만들기 in > ");

		this.room = 방목록화면.방생성(this, split[2]);
		if (this.room != null) {
			outprint("방생성성공");
			broadCast("방업데이트");
		} else {
			outprint("방생성실패");
		}
		System.out.println("응답 end");

//		this.room = 방관리.방생성(this);
	}

	private void 새로고침() {
		outprint("새로고침");
	}

	private void 방나가기() {
		System.out.print("방나가기 > ");
		System.out.println("방size : " + get방목록().size());
		
		System.out.println("this.room : " + this.room);
		Iterator<방> 방목록 = get방목록().iterator();
		System.out.println("방목록 사이즈 : " + get방목록().size());
		while (방목록.hasNext()) {
//			if(!방목록.hasNext()) {
//				return;
//			}
			방 방 = 방목록.next();
			System.out.println("======================");
			for(유저 유저 : 방목록화면.get방목록().get(방목록화면.get방목록().indexOf(방)).유저들) {
				System.out.println(유저.port);
			}
			System.out.println("======================");
			boolean flag = false;
			System.out.println("방 : " + 방);
			if (방.equals(this.room)) {
				System.out.println("입장방이랑 같넹!?");
				System.out.println("인덱스 :" + 방.유저들.indexOf(this));
				if((방.유저들.indexOf(this) == 0)) {
					try {
						new OracleDB().방삭제(this.port);
					} catch (SQLException e) {
						System.out.println("방삭제실패..");
						e.printStackTrace();
					}
					// 같은 방에 있던 유저한테도 보내줘야함..
					System.out.println("방장이 나감..!");
					if(방.유저들.size()>1) {
						System.out.println("같이있던 유저 : " + 방.유저들.get(1).socket.getPort() + "방나가렴");
						outprint(방.유저들.get(1).socket.getPort(), "방나가렴");
					}
					outprint("방나가렴");
					get방목록().remove(this.room);
					broadCast("방업데이트");
					return;
				}
				else if ((방.유저들.indexOf(this) > 0) ) {
					방.유저들.remove(this);
					return;
				}
			}
		}
	}

	public void process(String inline) {
		System.out.print("서버 > process > ");
		String split[] = inline.split("/"); // '/' 단위로 끊겠다.
		switch (split[1]) {
		case "회원가입":
			회원가입(split);
			break;
		case "로그인":
			로그인(split);
			break;
		case "방만들기":
			방만들기(split);
			break;
		case "로그아웃":
			로그아웃(split);
			break;
		case "새로고침":
			새로고침();
			break;
		case "방나가기":
			방나가기();
			break;
		case "방입장":
			방입장(split);
			break;
		case "창닫음":
			종료();
			break;
		}
		System.out.println();
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
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void 종료() {
		// DB에도 삭제하고 서버에도 삭제해야함.
		try {
			String 삭제결과 = "";
			if(this.room != null)삭제결과 =  new DB.OracleDB().방삭제(port);
			broadCast(삭제결과);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		게임서버.get유저목록().remove(this);
	}

	public void broadCast(String str) {
		PrintWriter out;
		Iterator opw = 게임서버.m_OutputList.iterator();
		while (opw.hasNext()) {
			out = new PrintWriter((PrintWriter) opw.next(), true);
			out.println("broadCast/" + str);
		}
		System.out.println("broadCast/" + str);
	}

	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			System.out.print("서버응답 > ");
			System.out.println(getSocket().getPort() + "/" + str);
			out.println(getSocket().getPort() + "/" + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void outprint(int port, String str) {
		PrintWriter out;
		Iterator opw = 게임서버.m_OutputList.iterator();
		while (opw.hasNext()) {
			out = new PrintWriter((PrintWriter) opw.next(), true);
			out.println(port + "/" + str);
		}
		System.out.println(port + "/" + str);
	}
	// get set ===============================================

	public 유저(Socket socket) {
		this.socket = socket;
	}

	public void 방입장(String[] split) {
//		_room.방입장(this); // 룸에 입장시킨 후
		Iterator<방> Iter방목록 = 방목록.iterator();
		while(Iter방목록.hasNext()) {
			방 in_room = Iter방목록.next();
			int 방장포트 = Integer.parseInt(split[2]);
			int 유저포트 = in_room.유저들.get(0).socket.getPort();
			System.out.println("현재 검색중인 유저의 포트번호 : " + 유저포트);
			System.out.println("방장의 포트번호 : " + 방장포트);
			if(방장포트 == 유저포트) { // split[2]이 방장 소켓이 넘어옴,
				this.room = in_room; // 유저가 속한 방을 룸으로 변경한다.(중요)
				방목록화면.get방목록().get(방목록화면.get방목록().indexOf(in_room)).유저들.add(this);
				System.out.println("======================");
				for(유저 유저 : 방목록화면.get방목록().get(방목록화면.get방목록().indexOf(in_room)).유저들) {
					System.out.println(유저.port);
				}
				System.out.println("======================");
				outprint("방입장");
				outprint(방장포트, "유저입장/" + this.이름);
			}
		}
		
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

	public static ArrayList<방> get방목록() {
		return 방목록;
	}
}
