package ���߰���;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class �ֻ��� extends JLabel {
	int ����;
	boolean ������ = false;
	String �̹�������[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };
	
	void randomset() {
		Random r = new Random();
		
		���� = r.nextInt(6) + 1;
		System.out.println(����);
	}

	void set�̹���(int ����) {
//		ImageIcon icon = new ImageIcon("./images/" + �̹�������[���� - 1]);
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + �̹�������[����-1]));
		setIcon(icon);
		System.out.println("getIcon() : " + getIcon());
	}

	// �̹��� ��ó https://www.flaticon.com/kr/authors/smashicons"
}
