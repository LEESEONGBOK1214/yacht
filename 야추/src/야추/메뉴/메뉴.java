package ����.�޴�;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class �޴� extends ���ȭ�� {
private JButton �α���,ȸ������;

	
	public JButton get�α���() {
		return �α���;
	}
	public JButton getȸ������() {
		return ȸ������;
	}
	
	public �޴�(){
		setLayout(null);
		Font �޴���Ʈ = new Font(null,0,40);
		
		Color ���ڻ� = Color.green;
		�α��� = new JButton("�α���");
		�α���.setBackground(Color.green);
		�α���.setOpaque(false);
		�α���.setBorderPainted(false);
		�α���.setFocusPainted(false);
		�α���.setFont(�޴���Ʈ);
		�α���.setForeground(���ڻ�);
		�α���.setBounds(100, 250, 250, 100);
		add(�α���);

		ȸ������ = new JButton("ȸ������");
		ȸ������.setBackground(Color.green);
		ȸ������.setOpaque(false);
		ȸ������.setBorderPainted(false);
		ȸ������.setFocusPainted(false);
		ȸ������.setFont(�޴���Ʈ);
		ȸ������.setForeground(���ڻ�);
		ȸ������.setBounds(360, 250, 250, 100);
		add(ȸ������);

		// �׽��ÿ��
	}
}
