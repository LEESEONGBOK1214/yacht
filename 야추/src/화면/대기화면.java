package ȭ��;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ���ȭ�� extends JPanel {
	private static ���ȭ�� instance = new ���ȭ��();

	private ���ȭ��() {
		setLayout(null);
		setBackground(Color.pink);
		JLabel ������Դϴ� = new JLabel("������Դϴ�.");
		Font ��Ʈ = new Font(null,1,40);
		������Դϴ�.setFont(��Ʈ);
		������Դϴ�.setBounds(200, 200, 300, 300);
		add(������Դϴ�);
	}

	public static ���ȭ�� getInstance() {
		return instance;
	}
}
