package ���߰���;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ����ȭ�� extends JPanel implements ActionListener{
	�ֻ����� �ֻ�����;
	�ֻ��� �ֻ�����[];
	������ ������;
	������ ������;
	JPanel ��ư��;
	JButton ������;
	int ��;
	String ���� = "";
	boolean ����; // true�� �� ����.
	public ����ȭ��() {

		// �� �Ӽ� ����
		setBackground(Color.pink);
		setLayout(null);
		
		// ��ü �ʱ�ȭ
		�ֻ����� = new �ֻ�����(�ֻ�����);
		��ư�� = new JPanel();
		������ = new ������();
		
		
		������ = new JButton("������");
		
		// ��ü ����
//		�ֻ�����. // 50, 50���� 400 50ĭ��ŭ.
		�ֻ�����.setBorder(new LineBorder(Color.red));
		
		������.setBounds(300, 500, 100, 50);
		������.setBackground(Color.white);
		������.setOpaque(false);
		������.addActionListener(this);
//		������.setBorderPainted(false);
//		������.setFocusPainted(false);
		
		// ����
		add(�ֻ�����);
		add(������);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void �ֻ�������(�ֻ��� �ֻ���){
		// ��� �� �����ϰ� ���� �׷��ֱ� ����.
		removeAll();
		
		// ������=true�� �ֻ��� ����ŭ �׸���
		for(int i=0;i<5;i++) { // �ֻ����� ������ 5��.
			if()
		}
	}
}
