package ȭ��;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DB.OracleDB;
import ����_����.��;
import ����_����.����;


public class ����ȭ�� extends JPanel {
	// 1. GameRoom List �����ͼ� �ѷ��ֱ�.
	// 2. �� ���ý�, ����Ȯ�� �޼��� �����ֱ�.
	// 3. Ȯ�� ���ý�, ���ӹ� ����.
	private static ����ȭ�� instance = new ����ȭ��();
	private static JPanel �����г�;
	private JPanel ��ư���;
	private static ArrayList<��> ���� = new ArrayList<��>(); // ���� ����Ʈ����
	private JButton �游���;
	private JButton ���ΰ�ħ;
	private JButton ����;
	private JButton �α׾ƿ�;
	private ����ȭ��() {
		��ư��� = new JPanel();

		setLayout(null);

		�游��� = new JButton("�游���");
		���ΰ�ħ = new JButton("���ΰ�ħ");
		���� = new JButton("����");
		�α׾ƿ� = new JButton("�α׾ƿ�");

		�����г� = new JPanel();
		�����г�.setName("�����г�");
		�����г�.setBackground(Color.orange);

		��ư���.setLayout(null);
		��ư���.add(�游���);
		��ư���.add(����);
		��ư���.add(���ΰ�ħ);
		��ư���.add(�α׾ƿ�);
		�游���.setBounds(20, 0, 150, 50);
		����.setBounds(192, 0, 150, 50);
		���ΰ�ħ.setBounds(363, 0, 150, 50);
		�α׾ƿ�.setBounds(535, 0, 150, 50);

		�����г�.setBounds(10, 20, 685, 500);
		��ư���.setBounds(0, 600, 700, 200);
		
		add(�����г�);
		add(��ư���);
	}

	public void ��ϻ��ΰ�ħ(int �������� ) {
		�����г�.removeAll(); // ��� ���� �����ذ� ���� ����.
		
//		System.out.println("����.size() : " + �氳��());
		
		if(����.size()==0) {
			JLabel �� = new JLabel("���� �����ϴ�.");
			��.setBounds(200, 200, 100, 20);
			�����г�.add(��);
			
			repaint();
			return;
		}
		
		int i=0;
		for(�� room : ���� ) {
			JLabel �� = new JLabel(room.get�����̸�());
			��.setBounds(20, i*50 + 20, 100, 20);
			�����г�.add(��);
			instance.repaint();
		}
		
		instance.setBackground(Color.blue);
	}

	public �� �����(���� _owner, String title) {
		// ������ ���� ������ �� ���(������ �������� ��)
		�� room = new ��(_owner, title);
		try {
			new OracleDB().�����(room);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		����.add(room);
		System.out.println("����ȭ�� > �����!");
		return room;
	}

	public static ArrayList<��> get����() {
		return ����;
	}

	public static int �氳��() {
		return ����.size();
	} // ���� ũ�⸦ ������

	public JButton get�游���() {
		return �游���;
	}

	public JButton get���ΰ�ħ() {
		return ���ΰ�ħ;
	}

	public JButton get����() {
		return ����;
	}

	public JButton get�α׾ƿ�() {
		return �α׾ƿ�;
	}

	public static ����ȭ�� getInstance() {
		return instance;
	}

}
