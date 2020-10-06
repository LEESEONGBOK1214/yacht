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

public class 야추Frame extends JFrame implements ActionListener {
	private 주사위Panel 주사위패널;
	private 점수선택Panel 선택패널;
	private 게임Panel 게임패널;
	
	private 회원가입 회원가입;
	private 메뉴 메뉴;
	private 로그인 로그인;
	private CardLayout 카드; // 카드 레이아웃은 add한 레이아웃들을 하나씩 show 가능.
	private JPanel 메인카드;
//	private 전적확인 scorecheck;
	private String[] scores;
	private String yourname;
	private 대기화면 waitpage;
	String 응답;
	Socket socket;
	Connection conn;
	PreparedStatement ptst;
	
	야추Frame() {
		super("yacht!");
		// 기본 설정.
		setSize(700, 700);
		setLocation(1920 / 2 - this.getWidth() / 2, 1080 / 2 - this.getHeight() / 2); // 화면 가운데 조정
		
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
//		System.out.println("socket 다음");
		카드 = new CardLayout();
		메인카드 = new JPanel(카드);
		
		메뉴 = new 메뉴();
		
		메뉴.get게임시작().addActionListener(this);
		메뉴.get회원가입().addActionListener(this);
		메인카드.add(메뉴,"메뉴");
		
		회원가입 = new 회원가입();
		회원가입.get가입().addActionListener(this);
		회원가입.get취소().addActionListener(this);
		메인카드.add(회원가입,"회원가입");
		
		로그인 = new 로그인();
		로그인.getStartButton().addActionListener(this);
		로그인.getBackButton().addActionListener(this);
		메인카드.add(로그인,"로그인");
		
		카드.show(메인카드, "메뉴");
		
		
		add(메인카드);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		switch(e.getActionCommand()) {
		
		case "게임시작" :
			카드.show(메인카드, "로그인");
			break;
			
		case "회원가입" :
			카드.show(메인카드, "회원가입");
			break;
			
//		case "초기화" :
//			System.out.println("초기화눌림?");
//			기록조회();
//			break;
			
		case "가입":
			회원가입();
			break;
			
		case "취소" :
		case "뒤로" :
			카드.show(메인카드, "메뉴");
			break;
			
//		case "시작" :
//			로그인();
//			break;
		}
	}
	

	String loginstatus = null;
	private void 로그인() {
		if(!로그인.getIdTextField().getText().equals("")) {
			if(!로그인.getPasswdTextField().getText().equals("")) {
				
				Thread loginthread = new Thread(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						// 서버가 보낸 채팅 입력받는 in
						BufferedReader in;
						// 서버에 출력해줄 os
						OutputStream os;
						
						try {
							// 서버로부터 출력할 수 있도록 가져온다.
							os = socket.getOutputStream();
							OutputStreamWriter ost = new OutputStreamWriter(os);
							PrintWriter pw = new PrintWriter(ost,true);
							
							String 아이디 = 로그인.getIdTextField().getText();
							String 비밀번호 = 로그인.getPasswdTextField().getText();
							// 서버로    로그인/ 아이디 / 비밀번호 보냄. 
							pw.println("로그인/"+ 아이디+"/"+ 비밀번호);
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							while((loginstatus=in.readLine()) == null) {
								
							}
							
							if(loginstatus.equals("로그인성공")) {
								try {
//											Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),320);
									BufferedReader in1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
									os = socket.getOutputStream();
									ost = new OutputStreamWriter(os);
									pw = new PrintWriter(ost,true);
									pw.println(로그인.getIdTextField().getText());
									
									waitpage = new 대기화면();
									메인카드.add(waitpage,"대기화면");
									카드.show(메인카드, "대기화면");
									System.out.println("로그인성공");
									Thread thread = new Thread(new Runnable() {
										
										@Override
										public void run() {
											
											try {
												OutputStream os = socket.getOutputStream();
												OutputStreamWriter ost = new OutputStreamWriter(os);
												PrintWriter pw = new PrintWriter(ost,true);
												pw.println("아이디줘");
												while((yourname = in1.readLine()) == null) {
													System.out.println(yourname);
												}
												System.out.println("상대방 이름 : "+yourname);
												게임패널 = new 게임Panel(로그인.getIdTextField().getText(),yourname, socket);
												메인카드.add(게임패널,"게임판");
												카드.show(메인카드, "게임판");
												
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									thread.start();
									
								
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
//										yourname=br.readLine();
//										socket.close();
							}else {
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
			}else {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
		}
	}// end of 로그인 Method

	private void 회원가입() {
		if (!회원가입.get아이디받기().getText().equals("")) {
			if (!회원가입.get비밀번호받기().getText().equals("")) {
				if (!회원가입.get이름받기().getText().equals("")) {
					try {
						OutputStream os = socket.getOutputStream();
						OutputStreamWriter ost = new OutputStreamWriter(os);
						PrintWriter pw = new PrintWriter(ost, true);
						String 아이디 = 회원가입.get아이디받기().getText();
						String 비밀번호 = 회원가입.get비밀번호받기().getText();
						String 이름 = 회원가입.get이름받기().getText();
						pw.println("회원가입/" + 아이디 + "/" + 비밀번호 + "/" + 이름);
						System.out.println("회원가입 pw.println 밑.");
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						Thread thread = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									while ((응답 = in.readLine()) == null) {

									}
									JOptionPane.showMessageDialog(null, 응답);
									System.out.println("응답 : " + 응답);
									if (응답.equals("회원가입이 완료되었습니다.")) {
										카드.show(메인카드, "메뉴");
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
}
