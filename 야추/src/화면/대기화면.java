package 화면;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class 대기화면 extends JPanel {
	private static 대기화면 instance = new 대기화면();
	private JPanel 버튼패널;
	private JButton 돌아가기;
	private JButton 시작하기;
	private 대기화면() {
		setLayout(null);
		setBackground(Color.pink);
		JLabel 대기중입니다 = new JLabel("대기중입니다.");
		Font 대기폰트 = new Font(null,1,50);
		대기중입니다.setFont(대기폰트);
		대기중입니다.setBounds(200, 170, 600, 300);
		
		
		Font 버튼폰트 = new Font(null, Font.BOLD, 30);
		돌아가기 = new JButton("방목록으로");
		돌아가기.setBounds(0, 0, 200, 100);
		돌아가기.setFont(버튼폰트);
		시작하기 = new JButton("게임시작");
		시작하기.setBounds(150, 0, 200, 100);
		시작하기.setFont(버튼폰트);
		
		버튼패널 = new JPanel(new GridLayout(0,1));
		버튼패널.setBounds(200, 450, 300, 100);
		버튼패널.add(돌아가기);
		버튼패널.add(시작하기);
		
		add(대기중입니다);
		add(버튼패널);
	}

	public static 대기화면 getInstance() {
		return instance;
	}
	
	public JButton get돌아가기() {
		return 돌아가기;
	}
	
	public JButton get시작하기() {
		return 시작하기;
	}
}
