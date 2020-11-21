package 야추.메뉴;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class 메뉴 extends 배경화면 {
private JButton 로그인,회원가입;

	
	public JButton get로그인() {
		return 로그인;
	}
	public JButton get회원가입() {
		return 회원가입;
	}
	
	public 메뉴(){
		setLayout(null);
		Font 메뉴폰트 = new Font(null,0,40);
		
		Color 글자색 = Color.green;
		로그인 = new JButton("로그인");
		로그인.setBackground(Color.green);
		로그인.setOpaque(false);
		로그인.setBorderPainted(false);
		로그인.setFocusPainted(false);
		로그인.setFont(메뉴폰트);
		로그인.setForeground(글자색);
		로그인.setBounds(100, 250, 250, 100);
		add(로그인);

		회원가입 = new JButton("회원가입");
		회원가입.setBackground(Color.green);
		회원가입.setOpaque(false);
		회원가입.setBorderPainted(false);
		회원가입.setFocusPainted(false);
		회원가입.setFont(메뉴폰트);
		회원가입.setForeground(글자색);
		회원가입.setBounds(360, 250, 250, 100);
		add(회원가입);

		// 테스팅용들
	}
}
