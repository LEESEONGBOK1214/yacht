package ���߰���;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ����_����.GameRoom;
import ����_����.RoomManager;


public class ���ȭ�� extends JPanel {
	// 1. GameRoom List �����ͼ� �ѷ��ֱ�.
	// 2. �� ���ý�, ����Ȯ�� �޼��� �����ֱ�.
	// 3. Ȯ�� ���ý�, ���ӹ� ����.
	
	static JPanel �����г�;
	ArrayList<GameRoom> ����;
	static JPanel ��ư���;
	static RoomManager rm = new RoomManager();
	���ȭ��() {
		���� = RoomManager.getRoomList();
		��ư��� = new JPanel();

		setLayout(new BorderLayout());

		JLabel �游��� = new JLabel("�游���");
		JLabel ���ΰ�ħ = new JLabel("���ΰ�ħ");
		JLabel ���� = new JLabel("����");
//		JLabel �˻� = new JLabel()


		��ư���.add(�游���);
		��ư���.add(����);
		��ư���.add(���ΰ�ħ);

		add(BorderLayout.CENTER, �����г�);
		add(BorderLayout.SOUTH, ��ư���);
	}

	void showRoomList() {
		�����г�.removeAll(); // ��� ���� �����ذ� ���� ����.
		
//		for(int i=0;i<rm.RoomCount();i++) {
//			����.add(rm.CreateRoom(_userList))
//		}
	}
}
