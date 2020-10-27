package 야추게임;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import 야추_서버.게임서버;
import 야추_서버.방관리;
import 야추_서버.유저;

public class 대기화면 extends JPanel implements ActionListener {
	// 1. GameRoom List 가져와서 뿌려주기.
	// 2. 룸 선택시, 입장확인 메세지 보여주기.
	// 3. 확인 선택시, 게임방 입장.
	야추Frame 메인화면;
	static JPanel 방목록패널;
	static JPanel 버튼목록;
	static 방관리 방관리 = new 방관리();

	대기화면(야추Frame 메인화면) {
		this.메인화면 = 메인화면;
		버튼목록 = new JPanel();

		setLayout(null);

		JButton 방만들기 = new JButton("방만들기");
		JButton 새로고침 = new JButton("새로고침");
		JButton 들어가기 = new JButton("들어가기");
		JButton 로그아웃 = new JButton("로그아웃");
//		JLabel 검색 = new JLabel()

		방목록패널 = new JPanel();
		방목록패널.setName("방목록패널");
		방목록패널.setBackground(Color.orange);

		버튼목록.setLayout(null);
		버튼목록.add(방만들기);
		버튼목록.add(들어가기);
		버튼목록.add(새로고침);
		버튼목록.add(로그아웃);
		방만들기.setBounds(20, 0, 150, 50);
		들어가기.setBounds(192, 0, 150, 50);
		새로고침.setBounds(363, 0, 150, 50);
		로그아웃.setBounds(535, 0, 150, 50);

		방만들기.addActionListener(this);
		들어가기.addActionListener(this);
		새로고침.addActionListener(this);
		로그아웃.addActionListener(this);

		showRoomList();

		방목록패널.setBounds(10, 20, 685, 500);
		버튼목록.setBounds(0, 600, 700, 200);

		add(방목록패널);
		add(버튼목록);
	}

	void showRoomList() {
		방목록패널.removeAll(); // 요소 전부 삭제해고 새로 쓰기.
	}

	void 방만들기(String 아이디) {
		유저 만든사람 = null;
		showRoomList();
		try {
			for (유저 유저 : 게임서버.유저목록) {
				if(유저.get아이디() == ) {
					
				}
			}
			Socket socket = 만든사람.getM_socket();
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ost = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(ost, true);

			pw.println("방만들기/" + 만든사람.get아이디());
			System.out.println("회원가입 pw.println 밑.");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String 응답 = "";
					try {

						while ((응답 = in.readLine()) == null) {

						}
						JOptionPane.showMessageDialog(null, 응답);
						System.out.println("응답 : " + 응답);
						if (응답.equals("방만들기 성공.")) {
							게임화면 새방 = new 게임화면(만든사람);
							방관리.getroomList().add(새방);
							메인화면.게임화면으로(만든사람);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();

//							if(rs != null && rs.next()) {
//								JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
//							}else {
//								String sql = "insert into 윷놀이 (id,passwd,name) values ('"+makeInfo.get아이디받기().getText()+"','"
//										+makeInfo.get비밀번호받기().getText()+"','"+makeInfo.get이름받기().getText()+"')";
//								try {
//									stmt.executeUpdate(sql);
//									JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
//									card.show(mainCard, "메뉴");
//								} catch (SQLException e1) {
//									e1.printStackTrace();
//								}
//								System.out.println("없노");
//							}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//						catch (NullPointerException e) {
//							System.out.println("야추Frame() -> 회원가입() -> 이름받는 곳에서 null 발생.");
//						}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		String 입력버튼 = e.getActionCommand();
		switch (입력버튼) {
		case "방만들기":

			방만들기();
			break;
		case "들어가기":
			break;
		case "새로고침":
			break;
		case "로그아웃":
			break;
		}
	}

}
