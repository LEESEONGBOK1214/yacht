package ȭ��;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ���߰���.����Frame;
import �̺�Ʈ.e_���콺;
import �̺�Ʈ.e_�׼�;

public class ������ extends JPanel {
	JLabel ���[] = new JLabel[12];
	JButton ����[] = new JButton[12];
	String ��ϵ�[] = { "Aces", "Deuces", "Threes", "Fours", "Fives", "Sixes", "Choice", "4 of Kind", "Full House",
			"S.Straight", "L.Straight", "Yatch" };
	int ����[] = new int[12];
	�ֻ���[] �ֻ�����;
	JButton ���ư���;
	������(�ֻ���[] �ֻ�����) {
		setLayout(null);
		setBackground(Color.decode("#CDD1FF"));
		this.�ֻ����� = �ֻ�����;
		
		
		// 1~6 �� 6��
		// ���̽�, 4 of kind, Full house(2, 3),
		// S.straight, L.straight, yatch

		for (int i = 0; i < 2; i++) {
			for (int j = 6 * i; j < 6 * (i + 1); j++) {
				���[j] = new JLabel(��ϵ�[j]);
				���[j].setBounds(20 + 300 * i, 70 * (j % 6) + 50, 100, 50);
				
				����[j] = new JButton();
				����[j].setName("�������ù�ư");
				����[j].setBackground(Color.white);
				����[j].setFocusPainted(false);
				����[j].setBounds(120 + 300 * i, 70 * (j % 6) + 50, 100, 50);
				����[j].addActionListener(new e_�׼�());
				add(���[j]);
				add(����[j]);
			}
		}
		
//		for(int i=0;i<12;i++) {
//			����[i] = new JButton(��ϵ�[i]);
//		}
		���ư��� = new JButton("���ư���");
		���ư���.setName("����������");
		���ư���.setBounds(300, 600, 100, 50);
		���ư���.addMouseListener(new e_���콺());
		add(���ư���);
	}
	
	
	
	
	
	void ��������() {
		System.out.println("�������� >");

		// ���� 0���� �ٲٱ�
		for(int i=0;i<12;i++) {
			����[i] = 0;

		}
		// 1~6��.		
		for(�ֻ��� �ֻ��� : �ֻ�����) {
			if(�ֻ���.���� == 1) {
				����[0] += 1;
			}else if(�ֻ���.���� == 2) {
				����[1] += 2;
			}else if(�ֻ���.���� == 3) {
				����[2] += 3;
			}else if(�ֻ���.���� == 4) {
				����[3] += 4;
			}else if(�ֻ���.���� == 5) {
				����[4] += 5;
			}else if(�ֻ���.���� == 6) {
				����[5] += 6;
			}
			//���̽�
			����[6] += �ֻ���.����;
		}
		// ����ī
		for(int i=0;i<6;i++) {
			if(����[i] >= (i+1)*4) {
				int ����ī = 0;
				for(int j=0;j<5;j++) {
					����ī += �ֻ�����[j].����;
				}
				if(����ī > ����[7]) {
					����[7] = ����ī;
				}
			}
		}
		// Ǯ��
		int a=0, b=0;
		for(int i=0;i<6;i++) {
			if(����[i] >= (i+1)*2) {
				if(����[i] == (i+1)*3) {
					if(����[i] > a)a=����[i];
				}else if(����[i] == (i+1)*2) {
					if(����[i] > b)b=����[i];
				}
			}
		}
		if(a>0 && b>0)����[8] = a+b;
		
		// ������Ʈ
		// ������Ʈ
		if (����[2] > 0 && ����[3] > 0) { // 3, 4�� ������ �־����.
			System.out.print("3, 4");
			if(����[1] >0 && ����[4] >0) {// 2 3 4 5
				System.out.print(", 2, 5");
				if (����[0] > 0 || ����[5] > 0) { // 1 2 3 4 5 || 2 3 4 5 6
					System.out.print(", 1 or 6");
					����[10] = 30;
				}
				����[9] = 15;
			}
			else if(����[0] >0 &&  ����[1] >0) {// 1 2 3 4
				System.out.print(", 1, 2");
				// 1 2 3 4 5�� ������ ó��.
				����[9] = 15;
			}
			else if(����[4] >0 && ����[5] >0) { // 3 4 5 6
				System.out.print(", 5, 6");
				����[9] = 15;
			}
			System.out.println();
		}
		
		
		// ����
		for(int i =0;i<6;i++) {
			if(����[i]== (i+1)*5) {
				����[11] = 50;
				break;
			}
		}
		
		for(int i=0;i<12;i++) {
			remove(����[i]);
			����[i].setText("" + ����[i]);
			System.out.println(��ϵ�[i] + " : " + ����[i].getText());
			add(����[i]);
//			System.out.println(��ϵ�[i] + " : " + ����[i]);
		}
	}
	void ���õ�() {
		// ���������� �ϴܿ� �ֻ��� ǥ��
		�ֻ����� �ֻ������ = ����Frame.get����ȭ��().get������().�ֻ�����;
		�ֻ������.setBounds(100, 500, 500, 70);
		�ֻ������.������();
		
		��������();
		add(�ֻ������);
		repaint();
	}
}
