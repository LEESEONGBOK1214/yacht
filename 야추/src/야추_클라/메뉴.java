package 야추_클라;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class 메뉴 extends 배경화면 {
private JButton 로그인,회원가입;
private JButton 바로시작;
private JButton 방목록보기;
	
	public JButton get로그인() {
		return 로그인;
	}
	public JButton get회원가입() {
		return 회원가입;
	}
	
	public JButton get바로시작() {
		return 바로시작;
	}

	public JButton get방목록보기() {
		return 방목록보기;
	}
	public 메뉴(){
		setLayout(null);
		Font 메뉴폰트 = new Font(null,0,40);
		
		
		로그인 = new JButton("로그인");
		로그인.setBackground(Color.white);
		로그인.setOpaque(false);
		로그인.setBorderPainted(false);
		로그인.setFocusPainted(false);
		로그인.setFont(메뉴폰트);
		로그인.setBounds(100, 250, 250, 100);
		add(로그인);

		회원가입 = new JButton("회원가입");
		회원가입.setBackground(Color.white);
		회원가입.setOpaque(false);
		회원가입.setBorderPainted(false);
		회원가입.setFocusPainted(false);
		회원가입.setFont(메뉴폰트);
		회원가입.setBounds(360, 250, 250, 100);
		add(회원가입);

		// 테스팅용들

		바로시작 = new JButton("바로시작");
		바로시작.setBounds(300, 350, 100, 100);
		add(바로시작);

		방목록보기 = new JButton("방목록보기");
		방목록보기.setBounds(500, 350, 100, 100);
		add(방목록보기);
	}
}
