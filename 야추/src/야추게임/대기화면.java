package 야추게임;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import 야추_서버.RoomManager;


public class 대기화면 extends JPanel {
	// 1. GameRoom List 가져와서 뿌려주기.
	// 2. 룸 선택시, 입장확인 메세지 보여주기.
	// 3. 확인 선택시, 게임방 입장.
	
	static JPanel 방목록;
	static JPanel 버튼목록;
	static RoomManager rm = new RoomManager();
	대기화면() {
		방목록 = new JPanel();
		버튼목록 = new JPanel();

		setLayout(new BorderLayout());

		JLabel 방만들기 = new JLabel("방만들기");
		JLabel 새로고침 = new JLabel("새로고침");
		JLabel 들어가기 = new JLabel("들어가기");
//		JLabel 검색 = new JLabel()


		버튼목록.add(방만들기);
		버튼목록.add(들어가기);
		버튼목록.add(새로고침);

		add(BorderLayout.CENTER, 방목록);
		add(BorderLayout.SOUTH, 버튼목록);
	}

	void showRoomList() {
		방목록.removeAll(); // 요소 전부 삭제해고 새로 쓰기.
		
//		for(int i=0;i<rm.RoomCount();i++) {
//			방목록.add(rm.CreateRoom(_userList))
//		}
	}
}
