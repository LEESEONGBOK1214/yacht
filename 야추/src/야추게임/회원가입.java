package ���߰���;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class ȸ������ extends JPanel// extends ���Թ��
{
	private Font ������Ʈ;
	private JLabel ���̵�,��й�ȣ,�̸�;
	private JTextField ���̵�ޱ�, �̸��ޱ�;
	private JPasswordField ��й�ȣ�ޱ�;
	private JButton ����,���;
	
	public JTextField get���̵�ޱ�() {
		return ���̵�ޱ�;
	}

	public JTextField get��й�ȣ�ޱ�() {
		return ��й�ȣ�ޱ�;
	}

	public JTextField get�̸��ޱ�() {
		return �̸��ޱ�;
	}

	public JButton get����() {
		return ����;
	}

	public JButton get���() {
		return ���;
	}

	public ȸ������(){
		setLayout(null);
		������Ʈ = new Font(null,1,40);
		���̵� = new JLabel(" �� �� �� ");
		���̵�.setFont(������Ʈ);
		���̵�ޱ� = new JTextField();
		���̵�ޱ�.setFont(������Ʈ);
		��й�ȣ = new JLabel("��й�ȣ ");
		��й�ȣ.setFont(������Ʈ);
		��й�ȣ�ޱ� = new JPasswordField();
		��й�ȣ�ޱ�.setFont(������Ʈ);
		��й�ȣ�ޱ�.selectAll(); // ��ü ���� ��,
		��й�ȣ�ޱ�.setEchoChar('*');
		�̸� = new JLabel("  ��  �� ");
		�̸�.setFont(������Ʈ);
		�̸��ޱ� = new JTextField();
		�̸��ޱ�.setFont(������Ʈ);
		
		���� = new JButton("����");
		����.setBackground(Color.blue);
		����.setFont(������Ʈ);
		��� = new JButton("���");
		���.setBackground(Color.red);
		���.setFont(������Ʈ);
		
		���̵�.setBounds(100, 150, 200, 80);
		���̵�ޱ�.setBounds(270, 150, 330, 80);
		��й�ȣ.setBounds(100, 300, 200, 80);
		��й�ȣ�ޱ�.setBounds(270, 300, 330, 80);
		�̸�.setBounds(100, 450, 200, 80);
		�̸��ޱ�.setBounds(270, 450, 330, 80);
		
		����.setBounds(200, 550,150,50);
		���.setBounds(400, 550,150,50);
		
		add(���̵�);
		add(���̵�ޱ�);
		add(��й�ȣ);
		add(��й�ȣ�ޱ�);
		add(�̸�);
		add(�̸��ޱ�);
		add(����);
		add(���);
	}
}
