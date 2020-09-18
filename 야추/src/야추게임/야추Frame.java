package 야추게임;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class 야추Frame extends JFrame {
	private 주사위Panel 주사위Panel;
	private 점수선택Panel 선택Panel;
	private CardLayout 카드; // 카드 레이아웃은 add한 레이아웃들을 하나씩 show 가능.
	private JPanel 메인카드;
	야추Frame() {
		super("yacht!");
		// 기본 설정.
		setSize(700, 700);
		setLocation(1920 / 2 - this.getWidth() / 2, 1080 / 2 - this.getHeight() / 2); // 화면 가운데 조정
		setVisible(true);
		setDefaultCloseOperation(3);// 닫기 누르면 종료.

		// =================================================================================================

		카드 = new CardLayout();
		메인카드 = new JPanel(카드);

	}
}
