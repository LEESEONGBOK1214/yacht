package ����.ȭ��;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ����.����Frame;

@SuppressWarnings("serial")
public class �ֻ��� extends JLabel implements MouseListener {
	int x;
	int y;
	int size;
	int ����;
	boolean ������;
	
	static Thread ����;
	String �̹�������[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };

	public static Thread get����() {
		return ����;
	}

	�ֻ���(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		���� = 0;
		������ = false;
	}

	void randomset() {
		Random r = new Random();
		���� = r.nextInt(6) + 1;
	}
	public void set�̹���() {
		setBounds(x, y, 70, 70);
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + �̹�������[���� - 1]));
		setIcon(icon);
	}
	
	public class ���� extends Thread {

		public void run() {
			// ������ �߿� ���ø��ϰ�.
			����Frame.get����ȭ��().get������().get������ư().setEnabled(false);
			int ȸ�� = new Random().nextInt(5) + 5; // 5~10ȸ ����.
			try {
				for (int i = 0, j = 10; i < ȸ��; i++) {
					setBounds(x - j, y, 70, 70);
					j = j * -1;
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				System.out.println("�ֻ��� ���⿡�� ���ͷ�Ʈ�ɸ�.");
			}

			try {
				setBounds(x, y, 70, 70);
				Thread.sleep(200);
				set�̹���();
			} catch (InterruptedException e) {
				System.out.println("�ֻ��� ���� ���� ���㿡�� ���ͷ�Ʈ�ɸ�.");
			}
			����Frame.get����ȭ��().get������().get������ư().setEnabled(true);
			System.out.println(getName());
		}
//		
	}
	public void mouseClicked(MouseEvent e) { ������ = !������; }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }

	
	public void ������() {
		randomset();
		����();
	}

	public void ����() {
		new ����().start();
	}
	// �̹��� ��ó https://www.flaticon.com/kr/authors/smashicons"
}
