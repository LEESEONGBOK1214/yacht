package 야추_클라;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class 메뉴 extends 배경화면 {
private JButton 게임시작,회원가입;
private JButton 바로시작;
	
	public JButton get게임시작() {
		return 게임시작;
	}
	public JButton get회원가입() {
		return 회원가입;
	}
	
	public JButton get바로시작() {
		return 바로시작;
	}
	public 메뉴(){
		setLayout(null);
		Font 메뉴폰트 = new Font(null,0,40);
		
		
		게임시작 = new JButton("게임시작");
		게임시작.setBackground(Color.white);
		게임시작.setOpaque(false);
		게임시작.setBorderPainted(false);
		게임시작.setFocusPainted(false);
		게임시작.setFont(메뉴폰트);
		게임시작.setBounds(100, 250, 250, 100);
		add(게임시작);
		
		바로시작 = new JButton("바로시작");
		바로시작.setBounds(300, 350, 100, 100);
		add(바로시작);

		회원가입 = new JButton("회원가입");
		회원가입.setBackground(Color.white);
		회원가입.setOpaque(false);
		회원가입.setBorderPainted(false);
		회원가입.setFocusPainted(false);
		회원가입.setFont(메뉴폰트);
		회원가입.setBounds(360, 250, 250, 100);
		add(회원가입);
	}
}
