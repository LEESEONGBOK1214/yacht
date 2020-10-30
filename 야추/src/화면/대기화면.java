package 화면;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class 대기화면 extends JPanel {
	private static 대기화면 instance = new 대기화면();

	private 대기화면() {
		setLayout(null);
		setBackground(Color.pink);
		JLabel 대기중입니다 = new JLabel("대기중입니다.");
		Font 폰트 = new Font(null,1,40);
		대기중입니다.setFont(폰트);
		대기중입니다.setBounds(200, 200, 300, 300);
		add(대기중입니다);
	}

	public static 대기화면 getInstance() {
		return instance;
	}
}
