package 야추.화면;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DB.OracleDB;
import 야추.서버.방;
import 야추.서버.유저;

@SuppressWarnings("serial")
public class 방목록화면 extends JPanel {
	// 1. GameRoom List 가져와서 뿌려주기.
	// 2. 룸 선택시, 입장확인 메세지 보여주기.
	// 3. 확인 선택시, 게임방 입장.
	private static 방목록화면 instance = new 방목록화면();
	private JPanel 방목록패널;
	private JPanel 버튼목록;
	private JPanel 유저정보패널;
	private static ArrayList<방> 방목록 = new ArrayList<방>(); // 방의 리스트방목록
	private JButton 방만들기;
	private JButton 새로고침;
	private JButton 로그아웃;

	JLabel 유저명;
	JLabel 승률;
	JLabel 랭킹;

	public 방목록화면() {
		setLayout(null);
		setBackground(Color.decode("#d789d7"));

		버튼목록 = new JPanel(null);
		방만들기 = new JButton("방만들기");
		새로고침 = new JButton("새로고침");
		로그아웃 = new JButton("로그아웃");
		유저명 = new JLabel("이름 : ", JLabel.CENTER);
		승률 = new JLabel("승률 : ", JLabel.CENTER);
		랭킹 = new JLabel("랭킹 : ", JLabel.CENTER);

		방목록패널 = new JPanel();
		방목록패널.setName("방목록패널");
		방목록패널.setBackground(Color.orange);

		유저정보패널 = new JPanel(new GridLayout());
		유저정보패널.add(유저명);
		유저정보패널.setBackground(Color.decode("#f3bad6"));
		유저정보패널.add(승률);
		유저정보패널.add(랭킹);

//		버튼목록.setLayout();
		버튼목록.setBackground(Color.decode("#d789d7"));
		버튼목록.add(방만들기);
		버튼목록.add(새로고침);
		버튼목록.add(로그아웃);

		방목록패널.setBounds(10, 20, 690, 550);
		버튼목록.setBounds(0, 600, 700, 200);
		
		int 좌우간격 = 224;
		int 버튼크기 = 200;
		방만들기.setBounds(0, 0, 버튼크기, 50);
		새로고침.setBounds((좌우간격 + 20) * 1, 0, 버튼크기, 50);
		로그아웃.setBounds((좌우간격 + 20) * 2, 0, 버튼크기, 50);

		방목록패널.setBounds(10, 20, 690, 480);
		유저정보패널.setBounds(10, 510, 690, 80);
		버튼목록.setBounds(10, 600, 690, 50);

		add(방목록패널);
		add(유저정보패널);
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

	public JButton get로그아웃() {
		return 로그아웃;
	}

	public JPanel get방목록패널() {
		return 방목록패널;
	}

	public void set방목록패널(JPanel 방목록패널) {
		this.방목록패널 = 방목록패널;
	}

	public static 방목록화면 getInstance() {
		return instance;
	}

	public void 유저정보세팅(String[] 응답) {
		// 0 포트
		// 1 로그인성공
		// 2 유저명
		// 3 승률
		// 4 랭킹
		Font 유저정보폰트 = new Font(null, Font.PLAIN, 30);
		유저명.setFont(유저정보폰트);
		승률.setFont(유저정보폰트);
		랭킹.setFont(유저정보폰트);
		유저명.setText("이름 : " + 응답[2]);
		승률.setText("승률 : " + 응답[3]);
		랭킹.setText("랭킹 : " + 응답[4]);
	}

}
