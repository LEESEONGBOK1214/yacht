package ȭ��;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ���ȭ�� extends JPanel {
	private static ���ȭ�� instance = new ���ȭ��();
	private JPanel ��ư�г�;
	private JButton ���ư���;
	private JButton �����ϱ�;
	private JLabel ������Դϴ�;
	private JLabel ����̸���;
	private ���ȭ��() {
		setLayout(null);
		setBackground(Color.pink);
		������Դϴ� = new JLabel("������Դϴ�.");
		Font �����Ʈ = new Font(null,1,50);
		������Դϴ�.setFont(�����Ʈ);
		������Դϴ�.setBounds(150, 170, 600, 300);
		������Դϴ�.setBackground(Color.blue);
		
		����̸��� = new JLabel("���� : ");
		����̸���.setFont(new Font("Serif", Font.BOLD, 20));
		����̸���.setBounds(230, 150, 300, 100);
		����̸���.setBorder(new LineBorder(Color.black));
		
		
		Font ��ư��Ʈ = new Font(null, Font.BOLD, 30);
		���ư��� = new JButton("��������");
		���ư���.setBounds(0, 0, 200, 100);
		���ư���.setFont(��ư��Ʈ);
		
		�����ϱ� = new JButton("���ӽ���");
		�����ϱ�.setBounds(150, 0, 200, 100);
		�����ϱ�.setFont(��ư��Ʈ);
		�����ϱ�.setEnabled(false);
		
		��ư�г� = new JPanel(new GridLayout(0,1));
		��ư�г�.setBounds(200, 450, 300, 100);
		��ư�г�.add(���ư���);
		��ư�г�.add(�����ϱ�);
		
		add(����̸���);
		add(������Դϴ�);
		add(��ư�г�);
	}

	public static ���ȭ�� getInstance() {
		return instance;
	}
	
	public JButton get���ư���() {
		return ���ư���;
	}
	
	public JButton get�����ϱ�() {
		return �����ϱ�;
	}

	public void �����̸�����(String ����̸�) {
		����̸���.setText("���� : " + ����̸�);
//		����ȭ��.get������().set����̸�(����̸�);
		if(����̸�.equals("")) {
			������Դϴ�.setText("������Դϴ�.");
		}else {
			������Դϴ�.setText("��� ����!");
		}
		
	}
}
