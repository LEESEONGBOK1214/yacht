package ���߰���;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ������ extends JPanel {
	JLabel ���[] = new JLabel[12];
	String ��ϵ�[] = { "Aces", "Deuces", "Threes", "Fours", "Fives", "Sixes", "Choice", "4 of Kind", "Full House",
			"S.Straight", "L.Straight", "Yatch" };

	JButton ���ư���;
	������() {
		setLayout(null);
		setBackground(Color.decode("#CDD1FF"));
		// 1~6 �� 6��
		// ���̽�, 4 of kind, Full house(2, 3),
		// S.straight, L.straight, yatch

		for (int i = 0; i < 2; i++) {
			for (int j = 6 * i; j < 6 * (i + 1); j++) {
				���[j] = new JLabel(��ϵ�[j]);
				���[j].setBounds(20 + 300 * i, 70 * (j % 6) + 50, 100, 50);
				add(���[j]);
			}
		}
		
		���ư��� = new JButton("���ư���");
		���ư���.setName("����������");
		���ư���.setBounds(300, 600, 100, 50);
		���ư���.addMouseListener(new �̺�Ʈ_���콺());
		add(���ư���);
	}

	void ���õ�() {
		�ֻ����� �ֻ������ = ����ȭ��.������.�ֻ�����;
		�ֻ������.setBounds(100, 500, 500, 70);
		�ֻ������.������();

		add(�ֻ������);
	}
}
