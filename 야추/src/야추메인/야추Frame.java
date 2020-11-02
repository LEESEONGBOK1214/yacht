package 야추메인;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import 야추_서버.유저;
import 야추_클라.로그인;
import 야추_클라.메뉴;
import 야추_클라.회원가입;
import 화면.게임화면;
import 화면.대기화면;
import 화면.방목록화면;

@SuppressWarnings("serial")
public class 야추Frame extends JFrame {
	private static 게임화면 게임화면;

	private 메뉴 메뉴;
	private static 회원가입 회원가입;
	private static 로그인 로그인;
	private static CardLayout 장면; // 카드 레이아웃은 add한 레이아웃들을 하나씩 show 가능.
	private static JPanel 메인화면;
	private static 방목록화면 방목록창;

	static Socket socket;
	Connection conn;
	PreparedStatement ptst;

	String 응답 = null;

	야추Frame() {
		super("yacht!");
		// 기본 설정.
		setSize(720, 700);
		setLocation(600, 200); // 화면 가운데 조정
		setDefaultCloseOperation(3);// 닫기 누르면 종료.
		// =================================================================================================
		try {
			socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8888);
		} catch (UnknownHostException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		// =================================================================================================
		장면 = new CardLayout();
		메인화면 = new JPanel(장면);
		메뉴 = new 메뉴();

		메뉴.get로그인().addActionListener(new 버튼이벤트());
		메뉴.get회원가입().addActionListener(new 버튼이벤트());
		메뉴.get바로시작().addActionListener(new 버튼이벤트());
		메뉴.get방목록보기().addActionListener(new 버튼이벤트());
		메인화면.add(메뉴, "메뉴");

		회원가입 = new 회원가입();
		회원가입.get가입().addActionListener(new 버튼이벤트());
		회원가입.get취소().addActionListener(new 버튼이벤트());
		메인화면.add(회원가입, "회원가입");

		로그인 = new 로그인();
		로그인.getStartButton().addActionListener(new 버튼이벤트());
		로그인.getBackButton().addActionListener(new 버튼이벤트());
		메인화면.add(로그인, "로그인");

		게임화면 = new 게임화면();
		메인화면.add(게임화면, "게임화면");

		방목록창 = 방목록화면.getInstance();
		방목록창.get새로고침().addActionListener(new 버튼이벤트());
		방목록창.get들어가기().addActionListener(new 버튼이벤트());
		방목록창.get로그아웃().addActionListener(new 버튼이벤트());
		방목록창.get방만들기().addActionListener(new 버튼이벤트());
		메인화면.add(방목록창, "방목록화면");

		대기화면 대기화면 = 화면.대기화면.getInstance();
		대기화면.get돌아가기().addActionListener(new 버튼이벤트());
		대기화면.get시작하기().addActionListener(new 버튼이벤트());
		메인화면.add(대기화면, "대기화면");

		장면.show(get메인화면(), "메뉴");

		서버값받기();
		add(get메인화면());
		setVisible(true);
	}

	private void 서버값받기() {
		Thread 방나가기 = new Thread(new Runnable() {
			String 서버응답 = "";

			public void run() {
				BufferedReader in;
				System.out.println(socket.getLocalPort());
				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					while (true) {
						if((서버응답 = in.readLine()) == null) {
							continue;
						}
						String 응답[] = 서버응답.split("/");
						if (응답[0].equals("" + socket.getLocalPort())) {
							System.out.println("해당 yatch파일에서 요청받음.");
							// 여기서 switch 문으로 응답값 분배시키기.
							switch (응답[1]) {
							case "방나가렴":
							case "로그인성공":
								장면.show(메인화면, "방목록화면");
								break;
							case "로그인실패":
								JOptionPane.showMessageDialog(야추Frame.this, "아이디/비밀번호를 확인하세요.");
								break;
							case "회원가입이 완료되었습니다.":
								JOptionPane.showMessageDialog(null, "회원 가입 성공!");
							case "로그아웃성공" :
								장면.show(메인화면, "메뉴");
								break;
							case "방생성성공":
								장면.show(메인화면, "대기화면");
								break;
							case "새로고침":
								새로고침();
								break;
							}
						}
						Thread.sleep(100);
					}
					// while true 밖.
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		방나가기.start();
	}

	private void 새로고침() {

		Thread reflash = new Thread(new Runnable() {

			public void run() {

				BufferedReader in;
				// 2.소켓을 이용하여 서버에 데이터를 보낸다.

				try {
					outprint("새로고침");
					System.out.println("서버로 로그인 데이터 보냄.");
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					while ((응답 = in.readLine()) == null) {
//
//					}
					while (!(응답 = in.readLine()).equals("새로고침끝")) {
						if (응답.split("/").length > 1)
							응답 = 응답.split("/")[1]; // 포트번호 다음거 값 가져옴.
						System.out.println("새로고침 > 응답 : " + 응답);

					}
//					if (응답.contains("새로고침")) {
					// 새로고침
					// while문을 통해 방장이름 송신함.
					// 방장이름 쓕 나옴.

					// 새로고침끝
//					}
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});
		reflash.start();
	}

	// 방에 들어갔을 때 추가해야함..!!!!
//	게임화면 = new 게임화면();
//	메인카드.add(게임화면, "게임판");
	
	protected void outprint(String str) {
		try {
			PrintWriter pw = new PrintWriter(getSocket().getOutputStream(), true);
			pw.println(socket.getLocalPort() + "/" + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 장면.show
	public void 메뉴로() {
		장면.show(메인화면, "메뉴");
	}

	public void 방목록으로() {
		// 방목록화면 세팅.
		장면.show(메인화면, "방목록화면");
	}

	public void 게임화면으로() {
		장면.show(메인화면, "게임화면");
	}

	public void 대기화면으로() {
		장면.show(메인화면, "대기화면");
	}
	// get set

	public static 게임화면 get게임화면() {
		return 게임화면;
	}

	public static 회원가입 get회원가입() {
		return 회원가입;
	}

	public Socket getSocket() {
		return this.socket;
	}

	public static JPanel get메인화면() {
		return 메인화면;
	}

	public static CardLayout get장면() {
		return 장면;
	}
}
