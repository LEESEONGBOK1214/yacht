package ���߰���;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class �޴� extends ���ȭ�� {
private JButton ���ӽ���,ȸ������;
	
	public JButton get���ӽ���() {
		return ���ӽ���;
	}
	public JButton getȸ������() {
		return ȸ������;
	}

	�޴�(){
		setLayout(null);
		Font �޴���Ʈ = new Font(null,0,40);
		
		
		���ӽ��� = new JButton("���ӽ���");
		���ӽ���.setBackground(Color.white);
		���ӽ���.setOpaque(false);
		���ӽ���.setBorderPainted(false);
		���ӽ���.setFocusPainted(false);
		���ӽ���.setFont(�޴���Ʈ);
		���ӽ���.setBounds(100, 250, 250, 100);
		add(���ӽ���);
		
		
		ȸ������ = new JButton("ȸ������");
		ȸ������.setBackground(Color.white);
		ȸ������.setOpaque(false);
		ȸ������.setBorderPainted(false);
		ȸ������.setFocusPainted(false);
		ȸ������.setFont(�޴���Ʈ);
		ȸ������.setBounds(360, 250, 250, 100);
		add(ȸ������);
	}
}
