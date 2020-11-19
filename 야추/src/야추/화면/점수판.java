package ����.ȭ��;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ����.����Frame;

enum scores{
	aces, deuces, threes, fours, fives, sixes, subtotal, choice, fourOfKind, fullHouse, sStraight, lStraight, yatch,
	bonus
}


@SuppressWarnings("serial")
public class ������ extends JPanel implements ActionListener {
	int size = 14;
	JLabel ���[] = new JLabel[size];
	private JButton ���ù�ư[] = new JButton[size];
	JLabel �������[] = new JLabel[size];
	String ��ϵ�[] = { "Aces", "Deuces", "Threes", "Fours", "Fives", "Sixes", "subtotal", "Choice", "4 of Kind",
			"Full House", "S.Straight", "L.Straight", "Yatch", "bonus" };
	int ����[] = new int[size];
	�ֻ���[] �ֻ�����;
	private JButton ���ư���;
	private JLabel ��������[][] = new JLabel[2][2];
	private int ��������;

	������(�ֻ���[] �ֻ�����) {
		setLayout(null);
		setBackground(Color.decode("#CDD1FF"));
		this.�ֻ����� = �ֻ�����;

		// 1~6 �� 6��
		// ���̽�, 4 of kind, Full house(2, 3),
		// S.straight, L.straight, yatch

		// ���� �θ��� ���� ���̱�
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				get��������()[i][j] = new JLabel("0"); // [����][�̸�, ����]
				get��������()[i][j].setBounds(480 + 160 * j, 200 + 130 * i, 150 - 100 * j, 50);
				get��������()[i][j].setBorder(new LineBorder(Color.yellow));
				add(get��������()[i][j]);
			}
		}


		for (int i = 0; i < 2; i++) {
			int �¿찣�� = 200;
			int ���ϰ��� = 65;
			int len = (size % 2 == 0) ? size : size + 1;

			for (int j = len / 2 * i; j < len / 2 * (i + 1); j++) {
				���[j] = new JLabel(��ϵ�[j]);
				���[j].setBounds(20 + �¿찣�� * i, ���ϰ��� * (j % (len / 2)) + 30, 100, 50);

				���ù�ư[j] = new JButton();
				���ù�ư[j].setName(��ϵ�[j]);
				���ù�ư[j].setBackground(Color.white);
				���ù�ư[j].setFocusPainted(false);
				���ù�ư[j].setBounds(70 + (�¿찣�� + 20) * i, ���ϰ��� * (j % (len / 2)) + 30, 50, 50);
				���ù�ư[j].addActionListener(this);
				���ù�ư[j].setEnabled(false);
				
				�������[j] = new JLabel("0");
				�������[j].setName(��ϵ�[j]);
				�������[j].setHorizontalAlignment(JLabel.CENTER);

				JPanel tmp = new JPanel(new BorderLayout());
				tmp.setBounds(130 + (�¿찣�� + 20) * i, ���ϰ��� * (j % (len / 2)) + 30, 50, 50);
				tmp.setBackground(Color.gray);
				tmp.setBorder(new LineBorder(Color.black));
				tmp.add(�������[j], BorderLayout.CENTER);

				add(���[j]);
				add(���ù�ư[j]);
				add(tmp);
			}
		}
//		for(int i=0;i<12;i++) {
//			����[i] = new JButton(��ϵ�[i]);
//		}
		���ư��⼳��();
	}

	private void ���ư��⼳��() {
		���ư��� = new JButton("���ư���");
		���ư���.setName("����������");
		���ư���.setVisible(false);
		���ư���.setBounds(300, 600, 100, 50);
		���ư���.addActionListener(this);
		add(���ư���);
	}

	void ��������() {
		System.out.println("�������� >");

		// ���� 0���� �ٲٱ�
		for (int i = 0; i < size; i++) {
			����[i] = 0;

		}
		// 1~6��.
		System.out.println("�ֻ��� ���� ==================================");
		for (�ֻ��� �ֻ��� : �ֻ�����) {
			System.out.println(�ֻ���.����);
			����[�ֻ���.���� - 1] += �ֻ���.����;
			// ���̽�
			����[scores.choice.ordinal()] += �ֻ���.����;
		}
		System.out.println("�ֻ��� ���� ==================================");
		
//		// ���ʽ� -> ���� �� �� �߰�.
//		for (int i = 0; i < 6; i++) {
//			����[scores.bonus.ordinal()] += ����[i];
//		}

		 
		// ����ī
		for (int i = 0; i < 6; i++) {
			if (����[i] >= (i + 1) * 4) {
				int ����ī = 0;
				for (int j = 0; j < 5; j++) {
					����ī += �ֻ�����[j].����;
				}
				if (����ī > ����[scores.fourOfKind.ordinal()]) {
					����[scores.fourOfKind.ordinal()] = ����ī;
				}
			}
		}
		// Ǯ��
		int a = 0, b = 0;
		for (int i = 0; i < 6; i++) {
			if (����[i] >= (i + 1) * 2) {
				if (����[i] == (i + 1) * 3) {
					if (����[i] > a)
						a = ����[i];
				} else if (����[i] == (i + 1) * 2) {
					if (����[i] > b)
						b = ����[i];
				}
			}
		}
		if (a > 0 && b > 0)
			����[scores.fullHouse.ordinal()] = a + b;

		// ������Ʈ
		// ������Ʈ
		if (����[scores.threes.ordinal()] > 0 && ����[scores.fours.ordinal()] > 0) { // 3, 4�� ������ �־����.
			System.out.print("3, 4");
			if (����[scores.deuces.ordinal()] > 0 && ����[scores.fives.ordinal()] > 0) {// 2 3 4 5
				System.out.print(", 2, 5");
				if (����[scores.aces.ordinal()] > 0 || ����[scores.sixes.ordinal()] > 0) { // 1 2 3 4 5 || 2 3 4 5 6
					System.out.print(", 1 or 6");
					����[10] = 30;
				}
				����[9] = 15;
			} else if (����[scores.aces.ordinal()] > 0 && ����[scores.deuces.ordinal()] > 0) {// 1 2 3 4
				System.out.print(", 1, 2");
				// 1 2 3 4 5�� ������ ó��.
				����[scores.sStraight.ordinal()] = 15;
			} else if (����[scores.fives.ordinal()] > 0 && ����[scores.sixes.ordinal()] > 0) { // 3 4 5 6
				System.out.print(", 5, 6");
				����[scores.sStraight.ordinal()] = 15;
			}
			System.out.println();
		}

		// ����
		for (int i = 0; i < 6; i++) {
			if (����[i] == (i + 1) * 5) {
				����[scores.yatch.ordinal()] = 50;
				break;
			}
		}

		for (int i = 0; i < size; i++) {
			remove(���ù�ư[i]);
			���ù�ư[i].setText("" + ����[i]);
			System.out.println(��ϵ�[i] + " : " + ���ù�ư[i].getText());
			add(���ù�ư[i]);
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

	public void set����̸�(String ����̸�) {
		get��������()[1][0].setText(����̸�);
		get��������()[1][1].setText("0");
	}

	public JLabel[][] get��������() {
		return ��������;
	}

	public void set��������(JLabel ��������[][]) {
		this.�������� = ��������;
	}

	public JButton get���ư���() {
		return ���ư���;
	}

	public void set���ư���(JButton ���ư���) {
		this.���ư��� = ���ư���;
	}

	public int get��������() {
		return ��������;
	}

	public void set��������(int ��������) {
		this.�������� = ��������;
	}

	public void ���ù�ư��(boolean b) {
		System.out.println("������ > ���ù�ư�� >");
		for (int i = 0; i < ���ù�ư.length; i++) {
			System.out.println(i);
			if (!���ù�ư[i].getName().equals("����")) {
				���ù�ư[i].setEnabled(b);
			}
		}
		���ù�ư[scores.subtotal.ordinal()].setEnabled(false);
		���ù�ư[scores.bonus.ordinal()].setEnabled(false);
	}

	public void ��������(String[] ����) {
		for (int i = 0; i < ��ϵ�.length; i++) {
			if (��ϵ�[i].equals(����[2])) {
				�������[i].setText(����[3]);
				��������[(�������� + 1) % 2][1]
						.setText(Integer.parseInt(��������[(�������� + 1) % 2][1].getText()) + Integer.parseInt(����[3]) + "");
				break;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton ������ư = (JButton) e.getSource();

		if (������ư == ���ư���) {
			����Frame.outprint("����������");
		} else { // ���� ���� ��,
			int ���� = Integer.parseInt(������ư.getText());

			switch (������ư.getName()) {
			case "aces": case "deuces": case "threes": case"fours": case"fives": case"sixes":
				this.����[scores.subtotal.ordinal()] = this.����[scores.subtotal.ordinal()] + ����;
				break;

			}
			
			������ư.setEnabled(false);
			����Frame.outprint("��������/" + ������ư.getName() + "/" + ����);
			������ư.setName("����");
			����Frame.get����ȭ��().�� = 0;
			��������[��������][1].setText(Integer.parseInt(��������[��������][1].getText()) + ���� + "");
//			����Frame.get����ȭ��().set������(����);
		}

		����Frame.get����ȭ��().����������();

	}

	public void �Ͻ���(boolean ���ð�) {
		���ù�ư��(���ð�);
		get���ư���().setVisible(���ð�);
	}

	public int get������() {
		return Integer.parseInt(��������[��������][1].getText());
	}

	public int get�������() {
		return Integer.parseInt(��������[(�������� + 1) % 2][1].getText());
	}

}
