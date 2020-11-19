package 야추.메뉴;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class 로그인 extends 배경화면 {
	static JTextField idTextField;
	static JPasswordField passwdTextField;
	JButton startButton,backButton;
	JLabel id,passwd;
	
	public static JTextField getIdTextField() {
		return idTextField;
	}

	public static JTextField getPasswdTextField() {
		return passwdTextField;
	}

	public JButton getStartButton() {
		return startButton;
	}


	public JButton getBackButton() {
		return backButton;
	}


	public 로그인() {
		setLayout(new BorderLayout());
		Font font = new Font("Serif",1,30);
		//nameInput.setLayout(new GridLayout(3,1,0,20));	
		setLayout(null);
		setBackground(Color.white);
		idTextField = new JTextField(5);
		idTextField.setFont(font);
		passwdTextField = new JPasswordField();
		passwdTextField.setFont(font);
		id = new JLabel("아 이 디");
		id.setFont(font);
		passwd = new JLabel("비밀번호");
		passwd.setFont(font);

		add(id);
		id.setBounds(170, 170, 200, 100);
		id.setForeground(Color.green);
		add(idTextField);
		idTextField.setBounds(300, 190, 300, 70);
		add(passwd);
		passwd.setBounds(160, 250, 200, 150);
		passwd.setForeground(Color.green);
		add(passwdTextField);
		passwdTextField.setBounds(300, 300, 300, 70);
		passwdTextField.setEchoChar('*');
		startButton = new JButton("시작");
		backButton = new JButton("뒤로");
		startButton.setFont(font);
		backButton.setFont(font);
		startButton.setBackground(new Color(200, 100, 0));
		startButton.setBorderPainted(false);
		startButton.setBounds(250, 400, 150, 80);
		backButton.setBackground(new Color(200, 100, 0));
		backButton.setBorderPainted(false);
		backButton.setBounds(430, 400, 150, 80);
		add(startButton);
		add(backButton);
	}
}
