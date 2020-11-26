package ����.ȭ��;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DB.OracleDB;
import ����.����.��;
import ����.����.����;

@SuppressWarnings("serial")
public class ����ȭ�� extends JPanel {
	// 1. GameRoom List �����ͼ� �ѷ��ֱ�.
	// 2. �� ���ý�, ����Ȯ�� �޼��� �����ֱ�.
	// 3. Ȯ�� ���ý�, ���ӹ� ����.
	private static ����ȭ�� instance = new ����ȭ��();
	private JPanel �����г�;
	private JPanel ��ư���;
	private JPanel ���������г�;
	private static ArrayList<��> ���� = new ArrayList<��>(); // ���� ����Ʈ����
	private JButton �游���;
	private JButton ���ΰ�ħ;
	private JButton �α׾ƿ�;

	JLabel ������;
	JLabel �·�;
	JLabel ��ŷ;

	public ����ȭ��() {
		setLayout(null);
		setBackground(Color.decode("#d789d7"));

		��ư��� = new JPanel(null);
		�游��� = new JButton("�游���");
		���ΰ�ħ = new JButton("���ΰ�ħ");
		�α׾ƿ� = new JButton("�α׾ƿ�");
		������ = new JLabel("�̸� : ", JLabel.CENTER);
		�·� = new JLabel("�·� : ", JLabel.CENTER);
		��ŷ = new JLabel("��ŷ : ", JLabel.CENTER);

		�����г� = new JPanel();
		�����г�.setName("�����г�");
		�����г�.setBackground(Color.orange);

		���������г� = new JPanel(new GridLayout());
		���������г�.add(������);
		���������г�.setBackground(Color.decode("#f3bad6"));
		���������г�.add(�·�);
		���������г�.add(��ŷ);

//		��ư���.setLayout();
		��ư���.setBackground(Color.decode("#d789d7"));
		��ư���.add(�游���);
		��ư���.add(���ΰ�ħ);
		��ư���.add(�α׾ƿ�);

		�����г�.setBounds(10, 20, 690, 550);
		��ư���.setBounds(0, 600, 700, 200);
		
		int �¿찣�� = 224;
		int ��ưũ�� = 200;
		�游���.setBounds(0, 0, ��ưũ��, 50);
		���ΰ�ħ.setBounds((�¿찣�� + 20) * 1, 0, ��ưũ��, 50);
		�α׾ƿ�.setBounds((�¿찣�� + 20) * 2, 0, ��ưũ��, 50);

		�����г�.setBounds(10, 20, 690, 480);
		���������г�.setBounds(10, 510, 690, 80);
		��ư���.setBounds(10, 600, 690, 50);

		add(�����г�);
		add(���������г�);
		add(��ư���);
	}

	public static �� �����(���� _owner, String title) {
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

	public JButton get�α׾ƿ�() {
		return �α׾ƿ�;
	}

	public JPanel get�����г�() {
		return �����г�;
	}

	public void set�����г�(JPanel �����г�) {
		this.�����г� = �����г�;
	}

	public static ����ȭ�� getInstance() {
		return instance;
	}

	public void ������������(String[] ����) {
		// 0 ��Ʈ
		// 1 �α��μ���
		// 2 ������
		// 3 �·�
		// 4 ��ŷ
		Font ����������Ʈ = new Font(null, Font.PLAIN, 30);
		������.setFont(����������Ʈ);
		�·�.setFont(����������Ʈ);
		��ŷ.setFont(����������Ʈ);
		������.setText("�̸� : " + ����[2]);
		�·�.setText("�·� : " + ����[3]);
		��ŷ.setText("��ŷ : " + ����[4]);
	}

}
