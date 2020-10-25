package 야추게임;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class 주사위 extends JLabel implements MouseListener {
	int x, y;
	int size;
	int 눈금;
	boolean 저장중 = false;
	String 이미지파일[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };

	주사위(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	void randomset() {
		Random r = new Random();

		눈금 = r.nextInt(6) + 1;
//		System.out.println(눈금);
	}

	void set이미지() {
//		ImageIcon icon = new ImageIcon("./images/" + 이미지파일[눈금 - 1]);

		int 회수 = new Random().nextInt(5) + 5; // 5~10회 흔들기.
		if (게임화면.턴 > 1) {
			Thread 흔들기 = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						for (int i = 0, j = 10; i < 회수; i++) {
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
					ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + 이미지파일[눈금 - 1]));
					setIcon(icon);
				}

			});
			흔들기.start();


		} else {
			setBounds(x, y, 70, 70);

			ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + 이미지파일[눈금 - 1]));
			setIcon(icon);
		}


//		System.out.println("getIcon() : " + getIcon());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		저장중 = !저장중;
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

	public void 굴리기() {
		// TODO Auto-generated method stub
		randomset();
		set이미지();
	}

	// 이미지 출처 https://www.flaticon.com/kr/authors/smashicons"
}
