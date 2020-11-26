package ����.ȭ��;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ����.����Frame;

enum scores {
	Aces, Deuces, Threes, Fours, Fives, Sixes, Subtotal, Choice, FourOfKind, FullHouse, sStraight, lStraight, Yatch,
	Bonus
}

@SuppressWarnings("serial")
public class ������ extends JPanel implements ActionListener {
	int size = 14;
	JLabel ���[] = new JLabel[size];
	private JButton ���ù�ư[] = new JButton[size];
	JLabel �������[] = new JLabel[size];

	String ��ϵ�[] = { "Aces", "Deuces", "Threes", "Fours", "Fives", "Sixes", "subtotal", "Choice", "FourOfKind",
			"FullHouse", "sStraight", "lStraight", "Yatch", "Bonus" };
	int ����[] = new int[size];
	�ֻ���[] �ֻ�����;
	private JButton ���ư���;
	private JLabel ��������[][] = new JLabel[2][2];
	private int �������;

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
			for (int j = size / 2 * i; j < size / 2 * (i + 1); j++) {

				int �¿찣�� = 200;
				int ���ϰ��� = 65;
				int len = (size % 2 == 0) ? size : size + 1;

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

		�����ʱ�ȭ();
//		for(int i=0;i<12;i++) {
//			����[i] = new JButton(��ϵ�[i]);
//		}
		���ư��⼳��();
	}

	public void �����ʱ�ȭ() {
		// ���� 0���� �ٲٱ�
		for (int i = 0; i < size; i++) {
			����[i] = 0;
		}
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
		�����ʱ�ȭ();

		// 1~6��.
		System.out.println("�ֻ��� ���� ==================================");
		for (�ֻ��� �ֻ��� : �ֻ�����) {
//			System.out.println(�ֻ���.����);
			����[�ֻ���.���� - 1] += �ֻ���.����;
			// ���̽�
			����[scores.Choice.ordinal()] += �ֻ���.����;
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
				if (����ī > ����[scores.FourOfKind.ordinal()]) {
					����[scores.FourOfKind.ordinal()] = ����ī;
				} else {
					����[scores.FourOfKind.ordinal()] = 0;
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
		if (a > 0 && b > 0) {
			����[scores.FullHouse.ordinal()] = a + b;
		} else {
			����[scores.FullHouse.ordinal()] = 0;
		}

		// ������Ʈ
		// ������Ʈ
		if (����[scores.Threes.ordinal()] > 0 && ����[scores.Fours.ordinal()] > 0) { // 3, 4�� ������ �־����.
			System.out.print("3, 4");
			if (����[scores.Deuces.ordinal()] > 0 && ����[scores.Fives.ordinal()] > 0) {// 2 3 4 5
				System.out.print(", 2, 5");
				if (����[scores.Aces.ordinal()] > 0 || ����[scores.Sixes.ordinal()] > 0) { // 1 2 3 4 5 || 2 3 4 5 6
					System.out.print(", 1 or 6");
					����[scores.lStraight.ordinal()] = 30;
				} else {
					����[scores.lStraight.ordinal()] = 0;
				}
				����[scores.sStraight.ordinal()] = 15;
			} else if (����[scores.Aces.ordinal()] > 0 && ����[scores.Deuces.ordinal()] > 0) {// 1 2 3 4
				System.out.print(", 1, 2");
				// 1 2 3 4 5�� ������ ó��.
				����[scores.sStraight.ordinal()] = 15;
			} else if (����[scores.Fives.ordinal()] > 0 && ����[scores.Sixes.ordinal()] > 0) { // 3 4 5 6
				System.out.print(", 5, 6");
				����[scores.sStraight.ordinal()] = 15;
			} else {
				����[scores.lStraight.ordinal()] = 0;
				����[scores.sStraight.ordinal()] = 0;
			}
			System.out.println();
		} else {
			����[scores.lStraight.ordinal()] = 0;
			����[scores.sStraight.ordinal()] = 0;
		}

		// ����
		for (int i = 0; i < 6; i++) {
			if (����[i] == (i + 1) * 5) {
				����[scores.Yatch.ordinal()] = 50;
				break;
			} else {
				����[scores.Yatch.ordinal()] = 0;
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
		return �������;
	}

	public void set��������(int ��������) {
		this.������� = ��������;
	}

	public void ���ù�ư��(boolean b) {
//		System.out.println("������ > ���ù�ư�� >");
		for (int i = 0; i < ���ù�ư.length; i++) {
//			System.out.println(i);

			if (!���ù�ư[i].getName().equals("����")) {
				���ù�ư[i].setEnabled(b);
			}
		}
		���ù�ư[scores.Subtotal.ordinal()].setEnabled(false);
		���ù�ư[scores.Bonus.ordinal()].setEnabled(false);
	}

	public void �����������(String[] ����) {
		for (int i = 0; i < ��ϵ�.length; i++) {
			if (��ϵ�[i].equals(����[2])) {
				if (i < 6) // Aces ~ Sixes ������
				{
					�������[scores.Subtotal.ordinal()].setText(
							Integer.parseInt(�������[scores.Subtotal.ordinal()].getText()) + Integer.parseInt(����[3]) + "");
					if (Integer.parseInt(�������[scores.Subtotal.ordinal()].getText()) >= 63) {
						��������[(������� + 1) % 2][1].setText(Integer.parseInt(��������[(������� + 1) % 2][1].getText()) + 35 + "");
					}
				}
				�������[i].setText(����[3]);
				��������[(������� + 1) % 2][1]
						.setText(Integer.parseInt(��������[(������� + 1) % 2][1].getText()) + Integer.parseInt(����[3]) + "");
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
			System.out.println("������ư . getName() : " + ������ư.getName());
			switch (������ư.getName()) {
			case "Aces":
			case "Deuces":
			case "Threes":
			case "Fours":
			case "Fives":
			case "Sixes":
				System.out.println("������ > ������ư > 1~6");
				this.����[scores.Subtotal.ordinal()] = this.����[scores.Subtotal.ordinal()] + 63;

				if (this.����[scores.Subtotal.ordinal()] >= 63) {
					JOptionPane.showMessageDialog(����Frame.get����ȭ��(), "���ʽ� ����!!!");
					this.����[scores.Bonus.ordinal()] = 35;
					����Frame.outprint("��������/" + ������ư.getName() + "/" + ����);
				}
				break;

			}

			������ư.setEnabled(false);
			����Frame.outprint("��������/" + ������ư.getName() + "/" + ����);
			������ư.setName("����");
			����Frame.get����ȭ��().�� = 0;
			��������[�������][1].setText(Integer.parseInt(��������[�������][1].getText()) + ���� + "");
//			����Frame.get����ȭ��().set������(����);
		}

		����Frame.get����ȭ��().����������();

	}

	public void �Ͻ���(boolean ���ð�) {
		���ù�ư��(���ð�);
		get���ư���().setVisible(���ð�);
	}

	public int get������() {
		return Integer.parseInt(��������[�������][1].getText());
	}

	public int get�������() {
		return Integer.parseInt(��������[(������� + 1) % 2][1].getText());
	}

}
