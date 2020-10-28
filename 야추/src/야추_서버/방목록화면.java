package 야추_서버;

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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import 야추게임.게임화면;
import 야추게임.야추Frame;

public class 방목록화면 extends JPanel implements ActionListener {
	// 1. GameRoom List 가져와서 뿌려주기.
	// 2. 룸 선택시, 입장확인 메세지 보여주기.
	// 3. 확인 선택시, 게임방 입장.

	static JPanel 방목록패널;
	static JPanel 버튼목록;
	private static ArrayList<게임화면> 방목록 = new ArrayList<게임화면>(); // 방의 리스트방목록

	public 방목록화면() {
		버튼목록 = new JPanel();

		setLayout(null);

		JButton 방만들기 = new JButton("방만들기");
		JButton 새로고침 = new JButton("새로고침");
		JButton 들어가기 = new JButton("들어가기");
		JButton 로그아웃 = new JButton("로그아웃");

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

		목록보여주기();

		방목록패널.setBounds(10, 20, 685, 500);
		버튼목록.setBounds(0, 600, 700, 200);

		add(방목록패널);
		add(버튼목록);
	}

	static void 목록보여주기() {
		방목록패널.removeAll(); // 요소 전부 삭제해고 새로 쓰기.
	}

	static void 방만들기() {
		System.out.print("방만들기 > ");
		목록보여주기();
		try {
//			for (유저 유저 : 게임서버.유저목록) {
//				if(유저.get아이디() == ) {
//					
//				}
//			}
			Socket socket = 야추Frame.getSocket();
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ost = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(ost, true);

			System.out.print("서버로 요청 : ");
			pw.println("방만들기/" + socket.getLocalPort());
			System.out.println("방만들기/" + socket.getLocalPort());
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
						/*
						 * 응답 값으로 유저 만들어주고, 방 생성 되도록 해야함.
						 * 
						 */

						if (응답.equals("방만들기 성공.")) {
							게임화면 새방 = new 게임화면(만든사람);
							방목록.add(새방);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		String 입력버튼 = e.getActionCommand();
		switch (입력버튼) {
		case "방만들기":
//			방생성();
			방만들기();
			break;
		case "들어가기":
			break;
		case "새로고침":
			break;
		case "로그아웃":
			야추Frame.메뉴로();
			break;
		}
	}



	public static 게임화면 방생성(유저 _owner) {
		// 유저가 방을 생성할 때 사용(유저가 방장으로 들어감)
		게임화면 room = new 게임화면(_owner);
		방목록.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public static void 방삭제(게임화면 게임화면) {
		방목록.remove(게임화면); // 전달받은 룸을 제거한다.
		System.out.println("Room Deleted!");
	}

	public static ArrayList<게임화면> getroomList() {
		return 방목록;
	}

	public static int roomCount() {
		return 방목록.size();
	} // 룸의 크기를 리턴해
}
