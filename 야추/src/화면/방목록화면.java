package ȭ��;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import ����_����.��;
import ����_����.����;


public class ����ȭ�� extends JPanel {
	// 1. GameRoom List �����ͼ� �ѷ��ֱ�.
	// 2. �� ���ý�, ����Ȯ�� �޼��� �����ֱ�.
	// 3. Ȯ�� ���ý�, ���ӹ� ����.

	private JPanel �����г�;
	private JPanel ��ư���;
	private static ArrayList<��> ���� = new ArrayList<��>(); // ���� ����Ʈ����
	private JButton �游���;
	private JButton ���ΰ�ħ;
	private JButton ����;
	private JButton �α׾ƿ�;
	public ����ȭ��() {
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

		��Ϻ����ֱ�();

		�����г�.setBounds(10, 20, 685, 500);
		��ư���.setBounds(0, 600, 700, 200);

		add(�����г�);
		add(��ư���);
	}

	public void ��Ϻ����ֱ�() {
		�����г�.removeAll(); // ��� ���� �����ذ� ���� ����.
	}

	public static �� �����(���� _owner) {
		// ������ ���� ������ �� ���(������ �������� ��)
		�� room = new ��();
		����.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public static void �����(�� ����ȭ��) {
		����.remove(����ȭ��); // ���޹��� ���� �����Ѵ�.
		System.out.println("Room Deleted!");
	}

	public static ArrayList<��> getroomList() {
		return ����;
	}

	public static int roomCount() {
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

}
