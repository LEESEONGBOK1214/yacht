package ���߰���;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class �ֻ����� extends JPanel {
	
	�ֻ���[] �ֻ�����;

	�ֻ�����(�ֻ���[] �ֻ�����) {
		setLayout(null);
		setBounds(100, 350, 500, 70);

		this.�ֻ����� = �ֻ�����;

		for (int i = 0; i < this.�ֻ�����.length; i++) {
//			�ֻ�����[i].������();
			ImageIcon icon = new ImageIcon(getClass().getResource("/images/No.png"));
			�ֻ�����[i].setIcon(icon);
			�ֻ�����[i].setName(i+"��°");
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
