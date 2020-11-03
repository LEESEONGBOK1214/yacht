package 야추_서버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import DB.OracleDB;
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

		boolean 결과=false;
		try {
			결과 = DB.회원가입(id, pw, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (결과) {
			outprint("/회원가입이 완료되었습니다.");
		} else {
			outprint("/아이디가 중복됩니다.");
		}
	}

	private void 로그인(String[] split) {
		System.out.print("in 로그인 >");
		OracleDB DB = new OracleDB();
		String id = split[2];
		String pw = split[3];
		System.out.println("split.length : " + split.length);

		String 이름=null;
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

			outprint("/로그인성공");
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

		outprint("/로그아웃성공");

	}

	private void 방만들기(String[] split) {
		System.out.println("in 방만들기 >");
		System.out.println("split.length : " + split.length);

		System.out.println("방만들기 in > ");

		방목록화면 목록화면 = 방목록화면.getInstance();
		this.room = 목록화면.방생성(this, split[2]);
		if (this.room != null) {
			outprint("/방생성성공");
		} else {
			outprint("/방생성실패");
		}
		System.out.println("응답 end");

//		this.room = 방관리.방생성(this);
	}

	private void 새로고침() {
//		System.out.println("서버에서의 방목록.size() : " + get방목록().size() );
		outprint("/새로고침");
//		getInstance();
//		outprint("새로고침" + get방목록().size());
		// while문으로 size만큼 방장이름 뿌려주면 될 것 같은데!
		// 새로고침끝 으로 확인하고!
//		outprint("새로고침");
//		System.out.println("새로고침");
//		Iterator<방> 목록 = get방목록().listIterator();
//		while(목록.hasNext()) {
//			String 방장이름 = 목록.next().get방장이름();
//			System.out.println(socket.getPort() + "/" + 방장이름);
//			outprint("/" + 방장이름);
//		}
//		System.out.println("새로고침끝");
//		outprint("새로고침끝");
	}

	private void 방나가기() {
		System.out.print("방나가기 > ");
		System.out.println("방size : " + get방목록().size());

		System.out.println("this.room : " + this.room);
		Iterator<방> 방목록 = get방목록().iterator();
		while (방목록.hasNext()) {
			방 방 = 방목록.next();
			boolean flag = false;
			System.out.println("방 : " + 방);
			if (방.equals(this.room)) {
				// 방 나가기 만약 나 혼자라면 방 삭제.
				if ((방.유저들.indexOf(this)!= -1)) {
					방.유저들.remove(방.유저들.indexOf(this));
					flag = true;
					
					if (방.유저들.size() <= 0) { // 없으면 -1 반환함.
						get방목록().remove(this.room);
						try {
							new OracleDB().방삭제(this.port);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						System.out.println("방 삭제! ");
						System.out.println("방size : " + get방목록().size());
						outprint("/방나가렴");
					}
					
					
				}
				

				if (flag)
					break;
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
		}finally {
			// 여기서 유저 정보 싹다 초기화.
			종료();
		}

	}

	private void 종료() {
		// DB에도 삭제하고 서버에도 삭제해야함.
		try {
			new DB.OracleDB().방삭제(port);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		게임서버.get유저목록().remove(this);
	}

	public void broadCast(String str) {
		PrintWriter out;
		try {
			out = new PrintWriter(getSocket().getOutputStream(), true);
			out.println("broadCast/"+str);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void outprint(String str) {
		try {
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			System.out.print("서버응답 > ");
			System.out.println(getSocket().getPort() + str);
			out.println(getSocket().getPort() + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// get set ===============================================

	public 유저(Socket socket) {
		this.socket = socket;
	}

	public void 방입장(방 _room) {
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

	public static ArrayList<방> get방목록() {
		return 방목록;
	}
}
