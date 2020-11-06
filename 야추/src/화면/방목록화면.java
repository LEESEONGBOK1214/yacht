package 화면;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import DB.OracleDB;
import 야추_서버.방;
import 야추_서버.유저;


@SuppressWarnings("serial")
public class 방목록화면 extends JPanel {
	// 1. GameRoom List 가져와서 뿌려주기.
	// 2. 룸 선택시, 입장확인 메세지 보여주기.
	// 3. 확인 선택시, 게임방 입장.
	
	private static JPanel 방목록패널;
	private JPanel 버튼목록;
	private static ArrayList<방> 방목록 = new ArrayList<방>(); // 방의 리스트방목록
	private JButton 방만들기;
	private JButton 새로고침;
	private JButton 들어가기;
	private JButton 로그아웃;
	public 방목록화면() {
		버튼목록 = new JPanel();
		setLayout(null);
		방만들기 = new JButton("방만들기");
		새로고침 = new JButton("새로고침");
		들어가기 = new JButton("들어가기");
		로그아웃 = new JButton("로그아웃");

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

		방목록패널.setBounds(10, 20, 685, 550);
		버튼목록.setBounds(0, 600, 700, 200);
		
		add(방목록패널);
		add(버튼목록);
	}


	public static 방 방생성(유저 _owner, String title) {
		// 유저가 방을 생성할 때 사용(유저가 방장으로 들어감)
		방 room = new 방(_owner, title);
		try {
			new OracleDB().방생성(room);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		방목록.add(room);
		System.out.println("방목록화면 > 방생성!");
		return room;
	}

	public static ArrayList<방> get방목록() {
		return 방목록;
	}

	public static int 방개수() {
		return 방목록.size();
	} // 룸의 크기를 리턴해

	public JButton get방만들기() {
		return 방만들기;
	}

	public JButton get새로고침() {
		return 새로고침;
	}

	public JButton get들어가기() {
		return 들어가기;
	}

	public JButton get로그아웃() {
		return 로그아웃;
	}

	public static JPanel get방목록패널() {
		return 방목록패널;
	}

	public static void set방목록패널(JPanel 방목록패널) {
		방목록화면.방목록패널 = 방목록패널;
	}

}
