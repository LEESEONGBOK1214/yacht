package ���߰���;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class �ֻ��� extends JLabel {
	int ����;
	String �̹�������[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };

	void randomset() {
		Random r = new Random();
		
		���� = r.nextInt(6) + 1;
		System.out.println(����);
	}

	void set�̹���(int ����) {
		ImageIcon icon = new ImageIcon("./images/" + �̹�������[���� - 1]);
	}

	// �̹��� ��ó https://www.flaticon.com/kr/authors/smashicons"
}
