package 야추게임;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

public class 야추Frame extends JFrame implements ActionListener {
	private static 게임화면 게임화면;

	private 메뉴 메뉴;
	private static 회원가입 회원가입;
	private static 로그인 로그인;
	private static CardLayout 장면; // 카드 레이아웃은 add한 레이아웃들을 하나씩 show 가능.
	private static JPanel 메인화면;
	private static 방목록화면 방목록화면;

	Socket socket;
	Connection conn;
	PreparedStatement ptst;

	String 응답 = null;

	야추Frame() {
		super("yacht!");
		// 기본 설정.
		setSize(720, 700);
		setLocation(400, 50); // 화면 가운데 조정
		setDefaultCloseOperation(3);// 닫기 누르면 종료.
		// =================================================================================================
		try {
			socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8888);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// =================================================================================================
		장면 = new CardLayout();
		메인화면 = new JPanel(장면);
		메뉴 = new 메뉴();

		메뉴.get게임시작().addActionListener(this);
		메뉴.get회원가입().addActionListener(this);
		메뉴.get바로시작().addActionListener(this);
		메뉴.get방목록보기().addActionListener(this);
		메인화면.add(메뉴, "메뉴");

		회원가입 = new 회원가입();
		회원가입.get가입().addActionListener(this);
		회원가입.get취소().addActionListener(this);
		메인화면.add(회원가입, "회원가입");

		로그인 = new 로그인();
		로그인.getStartButton().addActionListener(this);
		로그인.getBackButton().addActionListener(this);
		메인화면.add(로그인, "로그인");

		게임화면 = new 게임화면();
		메인화면.add(게임화면, "게임화면");

		방목록화면 = new 방목록화면();
		방목록화면.get새로고침().addActionListener(this);
		방목록화면.get들어가기().addActionListener(this);
		방목록화면.get로그아웃().addActionListener(this);
		방목록화면.get방만들기().addActionListener(this);
		메인화면.add(방목록화면, "방목록화면");

		장면.show(get메인화면(), "메뉴");

		add(get메인화면());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "게임시작":
			장면.show(get메인화면(), "로그인");
			break;
		case "회원가입":
			장면.show(get메인화면(), "회원가입");
			break;
		case "가입":
			회원가입();
			break;
		case "취소":
		case "뒤로":
			장면.show(get메인화면(), "메뉴");
			break;
		case "시작":
			로그인();
			break;
		case "방만들기":
//			방생성();
			방만들기();
			break;
		case "들어가기":
			break;
		case "새로고침":
			break;
		case "로그아웃":
			로그아웃();
			break;
		// 테스팅용들
		case "바로시작":
			장면.show(get메인화면(), "게임화면");
			break;
		case "방목록보기":
			장면.show(get메인화면(), "방목록화면");
			break;
		}
	}


	private void 로그인() {
		if (!로그인.getIdTextField().getText().equals("")) {
			if (!로그인.getPasswdTextField().getText().equals("")) {
				System.out.println("야추Frame > 로그인() > ");
				Thread loginthread = new Thread(new Runnable() {

					public void run() {
						// TODO Auto-generated method stub
						BufferedReader in;
						// 2.소켓을 이용하여 서버에 데이터를 보낸다.
						OutputStream os;

						try {
							os = socket.getOutputStream();
							OutputStreamWriter ost = new OutputStreamWriter(os);
							PrintWriter pw = new PrintWriter(ost, true);
							유저 로그인유저 = new 유저(socket);
							String uid = 로그인.getIdTextField().getText();
							String upw = 로그인.getPasswdTextField().getText();
							pw.println(socket.getLocalPort() + "/로그인/" + uid + "/" + upw);
							System.out.println("서버로 로그인 데이터 보냄.");
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							while ((응답 = in.readLine()) == null) {

							}
//							System.out.println("loginstatus : " + loginstatus);
							if (응답.equals(socket.getLocalPort() + "로그인성공")) {
//								try {
//											Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),320);
//									BufferedReader in1 = new BufferedReader(
//											new InputStreamReader(socket.getInputStream()));
//									os = socket.getOutputStream();
//									ost = new OutputStreamWriter(os);
//									pw = new PrintWriter(ost, true);
//									pw.println(로그인.getIdTextField().getText());

//
//									System.out.println("메인카드, \"대기화면\"할 차롄데...");
//									
									장면.show(메인화면, "방목록화면");
									System.out.println("로그인성공");

//								} catch (IOException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//										yourname=br.readLine();
//										socket.close();
							} else {
								System.out.println("로그인실패");
								JOptionPane.showMessageDialog(야추Frame.this, "아이디/비밀번호를 확인하세요.");
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				loginthread.start();
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
		}
	}// end of 로그인 Method

	public void 회원가입() {

		야추_클라.회원가입 회원가입 = 야추Frame.get회원가입();
		if (!회원가입.get아이디받기().getText().equals("")) {
			if (!회원가입.get비밀번호받기().getText().equals("")) {
				if (!회원가입.get이름받기().getText().equals("")) {
					try {
						OutputStream os = this.socket.getOutputStream();
						OutputStreamWriter ost = new OutputStreamWriter(os);
						PrintWriter pw = new PrintWriter(ost, true);
						String 아이디 = 회원가입.get아이디받기().getText();
						String 비밀번호 = 회원가입.get비밀번호받기().getText();
						String 이름 = 회원가입.get이름받기().getText();
//						System.out.println(pw);
						pw.println("회원가입/" + 아이디 + "/" + 비밀번호 + "/" + 이름);
						System.out.println("회원가입 pw.println 밑.");
						BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
						Thread thread = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {

									while ((응답 = in.readLine()) == null) {

									}

									System.out.println("응답 : " + 응답);
									System.out.println("socket.getLocalPort() : " + socket.getLocalPort());
									if (응답.equals(socket.getLocalPort() + "회원가입이 완료되었습니다.")) {
										JOptionPane.showMessageDialog(null, "회원 가입 성공!");
										장면.show(메인화면, "메뉴");
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						thread.start();

//						if(rs != null && rs.next()) {
//							JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
//						}else {
//							String sql = "insert into 윷놀이 (id,passwd,name) values ('"+makeInfo.get아이디받기().getText()+"','"
//									+makeInfo.get비밀번호받기().getText()+"','"+makeInfo.get이름받기().getText()+"')";
//							try {
//								stmt.executeUpdate(sql);
//								JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
//								card.show(mainCard, "메뉴");
//							} catch (SQLException e1) {
//								e1.printStackTrace();
//							}
//							System.out.println("없노");
//						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					catch (NullPointerException e) {
//						System.out.println("야추Frame() -> 회원가입() -> 이름받는 곳에서 null 발생.");
//					}

				} else {
					JOptionPane.showMessageDialog(null, "이름을 확인하세요");
				}
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요");
			}
		} else {
			JOptionPane.showMessageDialog(null, "아이디를 확인하세요");
		}
	}// end of 회원가입 Method

	public void 방만들기() {
		System.out.print("방만들기 > ");
//		목록보여주기();
		try {
//			for (유저 유저 : 게임서버.유저목록) {
//				if(유저.get아이디() == ) {
//					
//				}
//			}
			Socket socket = this.socket;
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ost = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(ost, true);

			System.out.print("서버로 요청 : ");
			pw.println(socket.getLocalPort() + "/방만들기");
			System.out.println(socket.getLocalPort() + "/방만들기");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					String 응답 = "";
					try {

						while ((응답 = in.readLine()) == null) {

						}
//						JOptionPane.showMessageDialog(null, 응답);
						System.out.println("응답 : " + 응답);
						/*
						 * 응답 값으로 유저 만들어주고, 방 생성 되도록 해야함.
						 * 
						 */

						if (응답.equals(socket.getLocalPort() + "방생성성공")) {
							System.out.println("방생성 해야하는데..?");
							대기화면으로();
//							게임화면 = new 게임화면();
//							게임화면으로();
//							게임화면 새방 = new 게임화면(만든사람);
//							방목록.add(새방);
//							야추Frame.게임화면으로(만든사람);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	

	// 방에 들어갔을 때 추가해야함..!!!!
//	게임화면 = new 게임화면();
//	메인카드.add(게임화면, "게임판");
	public void 로그아웃() {
		// 로그인 돼 있고 서버에서 로그아웃 메세지 보내면 메뉴창으로 가기.
		System.out.print("로그아웃 > ");
//		목록보여주기();
		try {
//			for (유저 유저 : 게임서버.유저목록) {
//				if(유저.get아이디() == ) {
//					
//				}
//			}
			Socket socket = this.socket;
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ost = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(ost, true);

			System.out.print("서버로 요청 : ");
			pw.println(socket.getLocalPort() + "/로그아웃"	);
			System.out.println("로그아웃/" + socket.getLocalPort());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String 응답 = "";
					try {

						while ((응답 = in.readLine()) == null) {

						}
//						JOptionPane.showMessageDialog(null, 응답);
						System.out.println("응답 : " + 응답);
						System.out.println("비교 : " + socket.getLocalPort() + "로그아웃성공");
						/*
						 * 응답 값으로 유저 만들어주고, 방 생성 되도록 해야함.
						 * 
						 */
						
						if (응답.equals(socket.getLocalPort() + "로그아웃성공")) {
							메뉴로();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		// 게임화면 세팅.
		// 방목록에서 가져와 추가하고 거기로 이동시켜야함.
		장면.show(메인화면, "게임화면");
	}
	
	protected void 대기화면으로() {
		대기화면 대기화면 = 화면.대기화면.getInstance();
		메인화면.add(대기화면, "대기화면");
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

	public JPanel get메인화면() {
		return 메인화면;
	}
}
