package ���߰���;

import javax.swing.JPanel;

public class �ֻ����� extends JPanel {
	�ֻ��� �ֻ�����[];

	�ֻ�����() {
		setLayout(null);

		�ֻ����� = new �ֻ���[5];

		for (int i = 0; i < �ֻ�����.length; i++) {
			�ֻ�����[i] = new �ֻ���();
			�ֻ�����[i].randomset();
			�ֻ�����[i].set�̹���(�ֻ�����[i].����);
			�ֻ�����[i].setBounds((i * 50) + 10, 0, 50, 50);
			// n������ n �̷������� i+1������ �ʱ�ȭ
		}

	}

	void setRoll() {

	}
}
