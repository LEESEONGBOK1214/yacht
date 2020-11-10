package 야추메인;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import 야추_회원폼.로그인;
import 야추_회원폼.메뉴;
import 야추_회원폼.회원가입;
import 화면.게임화면;
import 화면.대기화면;
import 화면.방목록화면;

@SuppressWarnings("serial")
public class 야추Frame extends JFrame implements ActionListener, WindowListener {
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
	int 전체턴;
	야추Frame() {
		super("yacht!");
		// 기본 설정.
		setSize(720, 700);
//		setLocation(600, 200); // 화면 가운데 조정 데탑
		setLocation(470, 100); // 노트북
		setDefaultCloseOperation(3);// 닫기 누르면 종료.
		
		// =================================================================================================
		try {
			socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8888);
		} catch (UnknownHostException e1) {
			System.out.println("서버 연결 실패");
			return;
		} catch (IOException e1) {
			System.out.println("서버 연결 실패");
			return;
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

		방목록창 = new 방목록화면();
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
		setResizable(false);
		서버값받기();
		add(get메인화면());
		setVisible(true);
		addWindowListener(this);
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
						while ((서버응답 = in.readLine()) == null) {
							System.out.println("in while 서버응답 : " + 서버응답);
							continue;
						}
						System.out.println("서버응답 : " + 서버응답);
						String 응답[] = 서버응답.split("/");
						if(응답.length<2) {
							System.out.println("서버 응답이 잘못된거 같다.");
							return;
						}
						if (응답[0].equals("broadCast")) {
							// 모든 유저에게 전달하는 브로드캐스트 >
							// 방 생성 삭제 시에 업데이트 시켜야함.
							if (응답[1] == "")
								return;
							switch (응답[1]) {
							case "방업데이트":
								방목록새로고침();
								break;
							}
						} else if (응답[0].equals("" + socket.getLocalPort())) {
							System.out.println("해당 yatch파일에서 요청받음.");
							// 여기서 switch 문으로 응답값 분배시키기.
							switch (응답[1]) {
							case "굴림판으로":
								게임화면.굴림판으로();
								break;
							case "점수판으로":
								게임화면.점수판으로();
								break;
							case "주사위저장함":
								게임화면.주사위저장함(응답[2]); // 상대방이 저장함.
								break;
							case "굴림값받음":
								게임화면.get굴림판().굴리기(응답[2]);
								break;
							case "게임시작함":
								전체턴 = 0;
								게임시작(응답[2], 응답[3], 응답[4], 응답[5]); // 차례 선 정보 전달.
								break;
							case "유저입장":
								유저입장(응답);
								break;
							case "방나가렴":
								화면.대기화면.getInstance().get시작하기().setEnabled(false);
								화면.대기화면.getInstance().상대방이름설정("");
								// break 없어도됨.
							case "로그인성공":
								장면.show(메인화면, "방목록화면");
								방목록새로고침();
								break;
							case "로그인실패":
								JOptionPane.showMessageDialog(야추Frame.this, "아이디/비밀번호를 확인하세요.");
								break;
							case "회원가입이 완료되었습니다.":
								JOptionPane.showMessageDialog(null, "회원 가입 성공!");
							case "로그아웃성공":
								장면.show(메인화면, "메뉴");
								break;
							case "방생성성공":
								장면.show(메인화면, "대기화면");
								break;
							case "새로고침":
								방목록새로고침();
								break;
							case "방입장":
								방입장(응답);
								repaint();
								break;
							case "내턴":
								게임화면.get점수판().점수세팅(응답);
								게임화면.set턴(1);
								턴세팅(true);
								전체턴++;
								System.out.println("전체턴 : " + 전체턴);
								break;
							case "턴종료":
								전체턴++;
								System.out.println("전체턴 : " + 전체턴);
								if (전체턴 == 24) {
									outprint("게임종료");
								}
								턴세팅(false);
								break;
							case "마지막굴림":
								게임화면.get굴림판().마지막굴림();
								break;
							case "게임종료":
								게임종료();
								break;
							}

						}
						Thread.sleep(100);
					}
					// while true 밖.
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}

			private void 방입장(String[] 응답) {
				화면.대기화면.getInstance().상대방이름설정(응답[2]);
				장면.show(메인화면, "대기화면");
			}

			private void 유저입장(String[] 응답) {
				화면.대기화면.getInstance().상대방이름설정(응답[2]);
				화면.대기화면.getInstance().get시작하기().setEnabled(true);
			}

		});
		방나가기.start();
	}

	protected void 게임종료() {
		String 결과 = "";
		int 내점수 = 게임화면.get점수판().get내점수();
		int 상대점수 = 게임화면.get점수판().get상대점수();
		
		if(내점수 > 상대점수) {
			결과 = "승리!";
		}else if(내점수 < 상대점수) {
			결과 = "패배!";
		}else {
			결과 = "무승부..";
		}
		JOptionPane.showMessageDialog(야추Frame.this, "게임종료!! 결과 : " + 결과);
		outprint("방나가기");
	}

	protected void 게임시작(String 순서정하기, String 유저명1, String 유저명2, String 순서) {
		if(순서정하기.equals("0")) { // 0이면 내 굴리기 차례 
			턴세팅(true);
			게임화면.턴 = 1;
			게임화면.get굴림판().get차례표시().setText("내 차례");
		} else {
			턴세팅(false);
			게임화면.get굴림판().get차례표시().setText("상대 차례");
		}

		게임화면.get점수판().get유저점수()[0][0].setText(유저명1);
		게임화면.get점수판().get유저점수()[1][0].setText(유저명2);
		게임화면.get점수판().set유저순서(Integer.parseInt(순서));
		장면.show(메인화면, "게임화면");
	}

	JButton 방입장버튼;
	private void 방목록새로고침() {
		try {
			ArrayList<String> DB방목록;
			DB방목록 = new DB.OracleDB().방목록가져오기();
			Iterator<String> 방목록 = DB방목록.iterator();
			int i = 0;

			방목록화면.get방목록패널().removeAll();

			if (!방목록.hasNext()) {
				JLabel 방없음 = new JLabel("방이 없습니다.");
				방없음.setFont(new Font("Serif", Font.BOLD, 40));
				방없음.setBounds(200, 230, 300, 50);
//				방없음.setBorder(new LineBorder(Color.pink));
				방목록화면.get방목록패널().add(방없음);
				System.out.println("방없음..");
			}
			while (방목록.hasNext()) {
				String 방정보 = 방목록.next();

				System.out.println("방목록 그려주자.");
				// 화면에 그려주기.
				JPanel 방패널 = new JPanel(null);
				방패널.setBounds(12 + (i % 3) * 230, 10 + 35 * ((int) (i / 3)), 200, 30);
				방패널.setBackground(Color.GREEN);
				방패널.setBorder(new LineBorder(Color.black));
//				방패널.setName("방");

				JLabel 방장소켓 = new JLabel(방정보.split("/")[0]);
				방장소켓.setBounds(0, 0, 50, 30);
				방장소켓.setBorder(new LineBorder(Color.black));

				JLabel 방제목 = new JLabel(방정보.split("/")[2]);
				방제목.setBounds(55, 0, 90, 30);

				방입장버튼 = new JButton();
				방입장버튼.setIcon(new ImageIcon(getClass().getResource("/images/입장.png")));
				방입장버튼.setBounds(150, 0, 50, 30);
				방입장버튼.setName(방정보.split("/")[0]); // 방장의 소켓을 넘겨 같은거 찾아 입장시키게 하자.
				방입장버튼.addActionListener(this);
				// 불가 text면 setEnable false로 하자.

				System.out.println("Integer.parseInt(방정보.split(\"/\")[1]) : " + Integer.parseInt(방정보.split("/")[1]));
				if (Integer.parseInt(방정보.split("/")[1]) > 0)
				{
					방입장버튼.setVisible(false);
				}

				방패널.add(방장소켓);
				방패널.add(방제목);
				방패널.add(방입장버튼);
				방목록화면.get방목록패널().add(방패널);
				i++;
			}

			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 방에 들어갔을 때 추가해야함..!!!!
//	게임화면 = new 게임화면();
//	메인카드.add(게임화면, "게임판");

	public static void outprint(String str) {
		try {
			PrintWriter pw = new PrintWriter(getSocket().getOutputStream(), true);
			pw.println(socket.getLocalPort() + "/" + str);
		} catch (IOException e) {
			System.out.println("서버가 연결 안돼있음.");
			System.exit(0);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// 입장 버튼 눌렸을 때
		JButton 버튼 = (JButton) e.getSource();
		outprint("방입장/" + 버튼.getName());

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

	public static Socket getSocket() {
		return socket;
	}

	public static JPanel get메인화면() {
		return 메인화면;
	}

	public static CardLayout get장면() {
		return 장면;
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		outprint("창닫음");
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	private void 턴세팅(boolean 세팅값) {
		게임화면.get굴림판().턴시작(세팅값);
		게임화면.get점수판().턴시작(세팅값);
		게임화면.굴림판으로();
	}

}
