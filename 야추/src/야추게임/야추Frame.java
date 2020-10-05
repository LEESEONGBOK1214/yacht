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
			socket = new Socket(InetAddress.getLocalHost().getHostAddress(),320);
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
			
//		case "가입" :
//			회원가입();
//			break;
			
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
						BufferedReader in;
						//2.소켓을 이용하여 서버에 데이터를 보낸다.
						OutputStream os;
						
						try {
							os = socket.getOutputStream();
							OutputStreamWriter ost = new OutputStreamWriter(os);
							PrintWriter pw = new PrintWriter(ost,true);
							pw.println("로그인/"+로그인.getIdTextField().getText()+"/"+로그인.getPasswdTextField().getText());
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
	}
}
