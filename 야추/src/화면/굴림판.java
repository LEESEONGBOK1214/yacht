package ȭ��;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ���߸���.����Frame;
import �̺�Ʈ.e_���콺;
import �̺�Ʈ.e_�׼�;

public class ������ extends JPanel {
	�ֻ����� �ֻ�����;
	������ ������;
	�ֻ��� �ֻ�����[];
	JLabel ����ǥ��;
	JButton ������ư;
	JButton ����ȭ����ȯ;

	������(�ֻ���[] �ֻ�����) {
//		System.out.println("���������� ��.");
		setLayout(null);
		setBackground(Color.pink);

		this.�ֻ����� = �ֻ�����;
		������ư����();
		�ֻ����� = new �ֻ�����(�ֻ�����);
		�ֻ�������();

		������ = new ������();



		�ֻ�����.setBorder(new LineBorder(Color.red));

		����ȭ����ȯ = new JButton("<html>����<br>ȭ��</html>");
		����ȭ����ȯ.setName("����ȭ������");

		����ȭ����ȯ.addMouseListener(new e_���콺());
		����ȭ����ȯ.setBounds(20, 250, 50, 100);

		add(�ֻ�����);
		add(������);
		add(������ư);
		add(����ȭ����ȯ);
//		����ǥ�ü���();
//		add(����ǥ��);
	}

	private void ����ǥ�ü���() {
		// TODO Auto-generated method stub

//		����ǥ�� = new JLabel(����ȭ��.������.get�̸�());
//		����ǥ��.setBounds(325, 580, 100, 50);
	}

	private void ������ư����() {
		// TODO Auto-generated method stub
		������ư = new JButton("������");
		������ư.setBounds(300, 500, 100, 50);
		������ư.setBackground(Color.white);
		������ư.setOpaque(false);
		������ư.addActionListener(new e_�׼�());
	}

	void �ֻ�������() {
		for (int i = 0; i < 5; i++) {
			if (�ֻ�����[i].������) {
				�ֻ�����[i].setBounds(82 * i, 0, 70, 70);
				������.add(�ֻ�����[i]);

			} else {
				�ֻ�����[i].setBounds(�ֻ�����[i].x, 0, �ֻ�����[i].size, �ֻ�����[i].size);
				// n������ n �̷������� i+1������ �ʱ�ȭ
				�ֻ�����.add(�ֻ�����[i]);
			}
		}
	}

	public void ������() {
		����ȭ��.��++;
		for (int i = 0; i < �ֻ�����.length; i++) {
			if (!�ֻ�����[i].������) { // �������� �ƴϸ�,

				�ֻ�����[i].������();
			}

		}
		System.out.println("���� ������ �� : " + ����ȭ��.��);
		�ֻ�����.repaint();

		if (����ȭ��.�� == 3) {
//			������();
		}
	}

//	public void ������() {
//		����ȭ�� ������ȭ�� = ����Frame.get����ȭ��();
////		������ȭ��.���� = false;
//		������ȭ��.�� = 0;
//		������ȭ��.get������().���ư���.setVisible(false);
//		������ȭ��.����������();
//	}

	public void ���õ�() {
		// TODO Auto-generated method stub
		�ֻ�����.setBounds(100, 350, 500, 70);
		add(�ֻ�����);
		add(������);
		add(������ư);
		add(����ȭ����ȯ);
	}

}
