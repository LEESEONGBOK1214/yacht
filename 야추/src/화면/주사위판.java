package ȭ��;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class �ֻ����� extends JPanel {

	static �ֻ���[] �ֻ�����;

	�ֻ�����(�ֻ���[] _�ֻ�����) {
		setLayout(null);
		setBounds(100, 350, 500, 70);

		�ֻ����� = _�ֻ�����;

		for (int i = 0; i < �ֻ�����.length; i++) {
			ImageIcon icon = new ImageIcon(getClass().getResource("/images/No.png"));
			�ֻ�����[i].setIcon(icon);
			�ֻ�����[i].setName((i + 1) + "��°");
		}

	}

	void ������() {
		removeAll();
		for (int i = 0; i < �ֻ�����.length; i++) {
			�ֻ�����[i].setBounds(�ֻ�����[i].x, 0, �ֻ�����[i].size, �ֻ�����[i].size);
			add(�ֻ�����[i]);
		}
	}

	public static �ֻ���[] get�ֻ�����() {
		return �ֻ�����;
	}
}
