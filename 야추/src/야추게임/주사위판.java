package ���߰���;

import javax.swing.JPanel;

public class �ֻ����� extends JPanel {
	
	�ֻ���[] �ֻ�����;
	�ֻ�����(�ֻ���[] �ֻ�����) {
		setLayout(null);
		setBounds(100, 350, 500, 70);

		this.�ֻ����� = �ֻ�����;

		for (int i = 0; i < this.�ֻ�����.length; i++) {
			�ֻ�����[i].������();
			�ֻ�����[i].setName(i+"��°");
			�ֻ�����[i].setBounds(�ֻ�����[i].x, 0, �ֻ�����[i].size, �ֻ�����[i].size);
			// n������ n �̷������� i+1������ �ʱ�ȭ
			add(�ֻ�����[i]);
		}
		
	}

	void ������() {
		removeAll();
		for (int i = 0; i < this.�ֻ�����.length; i++) {
			�ֻ�����[i].setBounds(�ֻ�����[i].x, 0, �ֻ�����[i].size, �ֻ�����[i].size);
			add(�ֻ�����[i]);
		}
	}
}
