package 야추게임;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class 점수판 extends JPanel {
	JLabel 목록[] = new JLabel[12];
	String 목록들[] = { "Aces", "Deuces", "Threes", "Fours", "Fives", "Sixes", "Choice", "4 of Kind", "Full House",
			"S.Straight", "L.Straight", "Yatch" };

	JButton 돌아가기;
	점수판() {
		setLayout(null);
		setBackground(Color.decode("#CDD1FF"));
		// 1~6 합 6개
		// 초이스, 4 of kind, Full house(2, 3),
		// S.straight, L.straight, yatch

		for (int i = 0; i < 2; i++) {
			for (int j = 6 * i; j < 6 * (i + 1); j++) {
				목록[j] = new JLabel(목록들[j]);
				목록[j].setBounds(20 + 300 * i, 70 * (j % 6) + 50, 100, 50);
				add(목록[j]);
			}
		}
		
		돌아가기 = new JButton("돌아가기");
		돌아가기.setName("굴림판으로");
		돌아가기.setBounds(300, 600, 100, 50);
		돌아가기.addMouseListener(new 이벤트_마우스());
		add(돌아가기);
	}

	void 선택됨() {
		주사위판 주사위목록 = 게임화면.굴림판.주사위판;
		주사위목록.setBounds(100, 500, 500, 70);
		주사위목록.보여줘();

		add(주사위목록);
	}
}
