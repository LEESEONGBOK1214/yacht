package 야추게임;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import 야추_서버.방;
import 야추_서버.방관리;
import 야추_서버.유저;


public class 대기화면 extends JPanel implements ActionListener {
	// 1. GameRoom List 가져와서 뿌려주기.
	// 2. 룸 선택시, 입장확인 메세지 보여주기.
	// 3. 확인 선택시, 게임방 입장.
	
	static JPanel 방목록패널;
	static JPanel 버튼목록;
	static 방관리 방관리 = new 방관리();
	대기화면() {
		버튼목록 = new JPanel();

		setLayout(null);

		JButton 방만들기 = new JButton("방만들기");
		JButton 새로고침 = new JButton("새로고침");
		JButton 들어가기 = new JButton("들어가기");
//		JLabel 검색 = new JLabel()

		방목록패널 = new JPanel();
		방목록패널.setName("방목록패널");
		방목록패널.setBackground(Color.orange);

		
		버튼목록.setLayout(null);
		버튼목록.add(방만들기);
		버튼목록.add(들어가기);
		버튼목록.add(새로고침);
		방만들기.setBounds(20, 0, 200, 50);
		들어가기.setBounds(250, 0, 200, 50);
		새로고침.setBounds(480, 0, 200, 50);
		방만들기.addActionListener(this);
		들어가기.addActionListener(this);
		새로고침.addActionListener(this);

		showRoomList();


		방목록패널.setBounds(10, 20, 690, 500);
		버튼목록.setBounds(0, 600, 700, 200);

		add(방목록패널);
		add(버튼목록);
	}

	void showRoomList() {
		방목록패널.removeAll(); // 요소 전부 삭제해고 새로 쓰기.
	}

	void addRoom(유저 만든사람) {
		방 새방 = new 방(만든사람);
		방관리.getRoomList().add(새방);
		showRoomList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
	}

}
