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

@SuppressWarnings("serial")
public class ������ extends JPanel implements ActionListener {
	JLabel ���[] = new JLabel[12];
	private JButton ���ù�ư[] = new JButton[12];
	JLabel �������[] = new JLabel[12];
	String ��ϵ�[] = { "Aces", "Deuces", "Threes", "Fours", "Fives", "Sixes", "Choice", "4 of Kind", "Full House",
			"S.Straight", "L.Straight", "Yatch" };
	int ����[] = new int[12];
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

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				get��������()[i][j] = new JLabel("0"); // [����][�̸�, ����]
				get��������()[i][j].setBounds(480 + 160 * j, 200 + 130 * i, 150 - 100 * j, 50);
				get��������()[i][j].setBorder(new LineBorder(Color.yellow));
				add(get��������()[i][j]);
			}
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 6 * i; j < 6 * (i + 1); j++) {
				���[j] = new JLabel(��ϵ�[j]);
				���[j].setBounds(20 + 200 * i, 70 * (j % 6) + 50, 100, 50);

				���ù�ư[j] = new JButton();
				���ù�ư[j].setName(��ϵ�[j]);
				���ù�ư[j].setBackground(Color.white);
				���ù�ư[j].setFocusPainted(false);
				���ù�ư[j].setBounds(70 + 220 * i, 70 * (j % 6) + 50, 50, 50);
				���ù�ư[j].addActionListener(this);
				���ù�ư[j].setEnabled(false);
				
				JPanel tmp = new JPanel(new BorderLayout());
				�������[j] = new JLabel("0");
				�������[j].setName(��ϵ�[j]);
				�������[j].setHorizontalAlignment(JLabel.CENTER);
				tmp.setBounds(130 + 220 * i, 70 * (j % 6) + 50, 50, 50);
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
		for (int i = 0; i < 12; i++) {
			����[i] = 0;

		}
		// 1~6��.
		for (�ֻ��� �ֻ��� : �ֻ�����) {
			if (�ֻ���.���� == 1) {
				����[0] += 1;
			} else if (�ֻ���.���� == 2) {
				����[1] += 2;
			} else if (�ֻ���.���� == 3) {
				����[2] += 3;
			} else if (�ֻ���.���� == 4) {
				����[3] += 4;
			} else if (�ֻ���.���� == 5) {
				����[4] += 5;
			} else if (�ֻ���.���� == 6) {
				����[5] += 6;
			}
			// ���̽�
			����[6] += �ֻ���.����;
		}
		// ����ī
		for (int i = 0; i < 6; i++) {
			if (����[i] >= (i + 1) * 4) {
				int ����ī = 0;
				for (int j = 0; j < 5; j++) {
					����ī += �ֻ�����[j].����;
				}
				if (����ī > ����[7]) {
					����[7] = ����ī;
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
			����[8] = a + b;

		// ������Ʈ
		// ������Ʈ
		if (����[2] > 0 && ����[3] > 0) { // 3, 4�� ������ �־����.
			System.out.print("3, 4");
			if (����[1] > 0 && ����[4] > 0) {// 2 3 4 5
				System.out.print(", 2, 5");
				if (����[0] > 0 || ����[5] > 0) { // 1 2 3 4 5 || 2 3 4 5 6
					System.out.print(", 1 or 6");
					����[10] = 30;
				}
				����[9] = 15;
			} else if (����[0] > 0 && ����[1] > 0) {// 1 2 3 4
				System.out.print(", 1, 2");
				// 1 2 3 4 5�� ������ ó��.
				����[9] = 15;
			} else if (����[4] > 0 && ����[5] > 0) { // 3 4 5 6
				System.out.print(", 5, 6");
				����[9] = 15;
			}
			System.out.println();
		}

		// ����
		for (int i = 0; i < 6; i++) {
			if (����[i] == (i + 1) * 5) {
				����[11] = 50;
				break;
			}
		}

		for (int i = 0; i < 12; i++) {
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
		for (int i = 0; i < ���ù�ư.length; i++) {
			if (!���ù�ư[i].getName().equals("����")) {
				���ù�ư[i].setEnabled(b);
			}
		}
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
