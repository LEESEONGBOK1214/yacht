package ���߰���;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class �ֻ��� extends JLabel implements MouseListener {
	int x, y;
	int size;
	int ����;
	boolean ������ = false;
	String �̹�������[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };

	�ֻ���(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	void randomset() {
		Random r = new Random();

		���� = r.nextInt(6) + 1;
//		System.out.println(����);
	}

	void set�̹���() {
//		ImageIcon icon = new ImageIcon("./images/" + �̹�������[���� - 1]);

		int ȸ�� = new Random().nextInt(5) + 5; // 5~10ȸ ����.
		if (����ȭ��.�� > 1) {
			Thread ���� = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						for (int i = 0, j = 10; i < ȸ��; i++) {
							setBounds(x - j, y, 70, 70);
							j = j * -1;
							Thread.sleep(100);
						}
//						System.out.println("??!");

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setBounds(x, y, 70, 70);
					ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + �̹�������[���� - 1]));
					setIcon(icon);
				}

			});
			����.start();


		} else {
			setBounds(x, y, 70, 70);

			ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + �̹�������[���� - 1]));
			setIcon(icon);
		}


//		System.out.println("getIcon() : " + getIcon());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		������ = !������;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void ������() {
		// TODO Auto-generated method stub
		randomset();
		set�̹���();
	}

	// �̹��� ��ó https://www.flaticon.com/kr/authors/smashicons"
}
