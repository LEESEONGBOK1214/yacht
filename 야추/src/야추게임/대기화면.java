package ���߰���;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ����_����.��;
import ����_����.�����;
import ����_����.����;


public class ���ȭ�� extends JPanel implements ActionListener {
	// 1. GameRoom List �����ͼ� �ѷ��ֱ�.
	// 2. �� ���ý�, ����Ȯ�� �޼��� �����ֱ�.
	// 3. Ȯ�� ���ý�, ���ӹ� ����.
	
	static JPanel �����г�;
	static JPanel ��ư���;
	static ����� ����� = new �����();
	���ȭ��() {
		��ư��� = new JPanel();

		setLayout(null);

		JButton �游��� = new JButton("�游���");
		JButton ���ΰ�ħ = new JButton("���ΰ�ħ");
		JButton ���� = new JButton("����");
//		JLabel �˻� = new JLabel()

		�����г� = new JPanel();
		�����г�.setName("�����г�");
		�����г�.setBackground(Color.orange);

		
		��ư���.setLayout(null);
		��ư���.add(�游���);
		��ư���.add(����);
		��ư���.add(���ΰ�ħ);
		�游���.setBounds(20, 0, 200, 50);
		����.setBounds(250, 0, 200, 50);
		���ΰ�ħ.setBounds(480, 0, 200, 50);
		�游���.addActionListener(this);
		����.addActionListener(this);
		���ΰ�ħ.addActionListener(this);

		showRoomList();


		�����г�.setBounds(10, 20, 690, 500);
		��ư���.setBounds(0, 600, 700, 200);

		add(�����г�);
		add(��ư���);
	}

	void showRoomList() {
		�����г�.removeAll(); // ��� ���� �����ذ� ���� ����.
	}

	void addRoom(���� ������) {
		�� ���� = new ��(������);
		�����.getRoomList().add(����);
		showRoomList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
	}

}
