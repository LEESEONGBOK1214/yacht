package ����.ȭ��;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ����.YatchFrame;

@SuppressWarnings("serial")
public class �ֻ��� extends JLabel implements MouseListener {
	int x;
	int y;
	int size;
	int ����;
	boolean ������;
	
	boolean ����;
	String �̹�������[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };

	�ֻ���(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		���� = 0;
		������ = false;
		new ����().start();
	}

	void randomset() {
		Random r = new Random();
		���� = r.nextInt(6) + 1;
	}
	public void set�̹���() {
		randomset();
		setBounds(x, y, 70, 70);
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + �̹�������[���� - 1]));
		setIcon(icon);
	}
	
	public class ���� extends Thread {

		public void run() {
			// ������ �߿� ���ø��ϰ�.
			while(true) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(����) {
					YatchFrame.get����ȭ��().get������().get������ư().setEnabled(false);
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
						���� = false;
					} catch (InterruptedException e) {
						System.out.println("�ֻ��� ���� ���� ���㿡�� ���ͷ�Ʈ�ɸ�.");
					}
				}
			}
		}
//		
	}
	public void mouseClicked(MouseEvent e) { ������ = !������; }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }

	
	public void ������() {
		���� = true;
	}
	// �̹��� ��ó https://www.flaticon.com/kr/authors/smashicons"
}
