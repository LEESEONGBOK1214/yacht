package 야추.화면;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import 야추.야추Frame;

@SuppressWarnings("serial")
public class 주사위 extends JLabel implements MouseListener {
	int x;
	int y;
	int size;
	int 눈금;
	boolean 저장중;
	
	static Thread 흔들기;
	String 이미지파일[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };

	public static Thread get흔들기() {
		return 흔들기;
	}

	주사위(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		눈금 = 0;
		저장중 = false;
	}

	void randomset() {
		Random r = new Random();
		눈금 = r.nextInt(6) + 1;
	}
	public void set이미지() {
		setBounds(x, y, 70, 70);
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + 이미지파일[눈금 - 1]));
		setIcon(icon);
	}
	
	public class 흔들기 extends Thread {

		public void run() {
			// 굴리는 중엔 선택못하게.
			야추Frame.get게임화면().get굴림판().get굴림버튼().setEnabled(false);
			int 회수 = new Random().nextInt(5) + 5; // 5~10회 흔들기.
			try {
				for (int i = 0, j = 10; i < 회수; i++) {
					setBounds(x - j, y, 70, 70);
					j = j * -1;
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				System.out.println("주사위 흔들기에서 인터럽트걸림.");
			}

			try {
				setBounds(x, y, 70, 70);
				Thread.sleep(200);
				set이미지();
			} catch (InterruptedException e) {
				System.out.println("주사위 흔들기 사이 멈춤에서 인터럽트걸림.");
			}
			야추Frame.get게임화면().get굴림판().get굴림버튼().setEnabled(true);
			System.out.println(getName());
		}
//		
	}
	public void mouseClicked(MouseEvent e) { 저장중 = !저장중; }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }

	
	public void 굴리기() {
		randomset();
		굴림();
	}

	public void 굴림() {
		new 흔들기().start();
	}
	// 이미지 출처 https://www.flaticon.com/kr/authors/smashicons"
}
