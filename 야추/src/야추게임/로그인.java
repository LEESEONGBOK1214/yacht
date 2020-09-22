package 야추게임;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class 로그인 extends 배경화면{
	JTextField idTextField,passwdTextField;
	JButton startButton,backButton;
	JLabel id,passwd;
	
	public JTextField getIdTextField() {
		return idTextField;
	}

	public JTextField getPasswdTextField() {
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
		passwdTextField = new JTextField(5);
		passwdTextField.setFont(font);
		id = new JLabel("아 이 디");
		id.setFont(font);
		id.setBackground(Color.pink);
		passwd = new JLabel("비밀번호");
		passwd.setFont(font);
		add(id);
		id.setBounds(170, 150, 200, 100);
		add(idTextField);
		idTextField.setBounds(300, 170, 300, 70);
		add(passwd);
		passwd.setBounds(160, 230, 200, 150);
		add(passwdTextField);
		passwdTextField.setBounds(300, 280, 300, 70);
		startButton = new JButton("시작");
		backButton = new JButton("뒤로");
		startButton.setFont(font);
		backButton.setFont(font);
		startButton.setBackground(Color.white);
		startButton.setBorderPainted(false);
		startButton.setBounds(250, 380, 150, 80);
		backButton.setBackground(Color.white);
		backButton.setBorderPainted(false);
		backButton.setBounds(430, 380, 150, 80);
		add(startButton);
		add(backButton);
	}
}
