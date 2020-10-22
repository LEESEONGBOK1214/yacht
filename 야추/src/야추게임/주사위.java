package 야추게임;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class 주사위 extends JLabel {
	int 눈금;
	String 이미지파일[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };

	void randomset() {
		Random r = new Random();
		
		눈금 = r.nextInt(6) + 1;
		System.out.println(눈금);
	}

	void set이미지(int 눈금) {
		ImageIcon icon = new ImageIcon("./images/" + 이미지파일[눈금 - 1]);
	}

	// 이미지 출처 https://www.flaticon.com/kr/authors/smashicons"
}
