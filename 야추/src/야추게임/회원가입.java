package 야추게임;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class 회원가입 extends JPanel// extends 가입배경
{
	private Font 가입폰트;
	private JLabel 아이디,비밀번호,이름;
	private JTextField 아이디받기, 이름받기;
	private JPasswordField 비밀번호받기;
	private JButton 가입,취소;
	
	public JTextField get아이디받기() {
		return 아이디받기;
	}

	public JTextField get비밀번호받기() {
		return 비밀번호받기;
	}

	public JTextField get이름받기() {
		return 이름받기;
	}

	public JButton get가입() {
		return 가입;
	}

	public JButton get취소() {
		return 취소;
	}

	public 회원가입(){
		setLayout(null);
		가입폰트 = new Font(null,1,40);
		아이디 = new JLabel(" 아 이 디 ");
		아이디.setFont(가입폰트);
		아이디받기 = new JTextField();
		아이디받기.setFont(가입폰트);
		비밀번호 = new JLabel("비밀번호 ");
		비밀번호.setFont(가입폰트);
		비밀번호받기 = new JPasswordField();
		비밀번호받기.setFont(가입폰트);
		비밀번호받기.selectAll(); // 전체 선택 후,
		비밀번호받기.setEchoChar('*');
		이름 = new JLabel("  이  름 ");
		이름.setFont(가입폰트);
		이름받기 = new JTextField();
		이름받기.setFont(가입폰트);
		
		가입 = new JButton("가입");
		가입.setBackground(Color.blue);
		가입.setFont(가입폰트);
		취소 = new JButton("취소");
		취소.setBackground(Color.red);
		취소.setFont(가입폰트);
		
		아이디.setBounds(100, 150, 200, 80);
		아이디받기.setBounds(270, 150, 330, 80);
		비밀번호.setBounds(100, 300, 200, 80);
		비밀번호받기.setBounds(270, 300, 330, 80);
		이름.setBounds(100, 450, 200, 80);
		이름받기.setBounds(270, 450, 330, 80);
		
		가입.setBounds(200, 550,150,50);
		취소.setBounds(400, 550,150,50);
		
		add(아이디);
		add(아이디받기);
		add(비밀번호);
		add(비밀번호받기);
		add(이름);
		add(이름받기);
		add(가입);
		add(취소);
	}
}
