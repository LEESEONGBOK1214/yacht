package ����_Ŭ��;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class �޴� extends ���ȭ�� {
private JButton �α���,ȸ������;
private JButton �ٷν���;
private JButton ���Ϻ���;
	
	public JButton get�α���() {
		return �α���;
	}
	public JButton getȸ������() {
		return ȸ������;
	}
	
	public JButton get�ٷν���() {
		return �ٷν���;
	}

	public JButton get���Ϻ���() {
		return ���Ϻ���;
	}
	public �޴�(){
		setLayout(null);
		Font �޴���Ʈ = new Font(null,0,40);
		
		
		�α��� = new JButton("�α���");
		�α���.setBackground(Color.white);
		�α���.setOpaque(false);
		�α���.setBorderPainted(false);
		�α���.setFocusPainted(false);
		�α���.setFont(�޴���Ʈ);
		�α���.setBounds(100, 250, 250, 100);
		add(�α���);

		ȸ������ = new JButton("ȸ������");
		ȸ������.setBackground(Color.white);
		ȸ������.setOpaque(false);
		ȸ������.setBorderPainted(false);
		ȸ������.setFocusPainted(false);
		ȸ������.setFont(�޴���Ʈ);
		ȸ������.setBounds(360, 250, 250, 100);
		add(ȸ������);

		// �׽��ÿ��

		�ٷν��� = new JButton("�ٷν���");
		�ٷν���.setBounds(300, 350, 100, 100);
		add(�ٷν���);

		���Ϻ��� = new JButton("���Ϻ���");
		���Ϻ���.setBounds(500, 350, 100, 100);
		add(���Ϻ���);
	}
}
