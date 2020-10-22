package 야추게임;

import javax.swing.JPanel;

public class 주사위판 extends JPanel {
	주사위 주사위들[];

	주사위판() {
		setLayout(null);

		주사위들 = new 주사위[5];

		for (int i = 0; i < 주사위들.length; i++) {
			주사위들[i] = new 주사위();
			주사위들[i].randomset();
			주사위들[i].set이미지(주사위들[i].눈금);
			주사위들[i].setBounds((i * 50) + 10, 0, 50, 50);
			// n번쨰는 n 이런식으로 i+1값으로 초기화
		}

	}

	void setRoll() {

	}
}
