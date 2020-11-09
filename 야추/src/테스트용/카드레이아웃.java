package 테스트용;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class 카드레이아웃 {
	public static void main(String[] args) {
		System.out.println("되라");
		new cardEx01();
	}
}

class cardEx01 extends JFrame implements ActionListener {
	CardLayout 장면;
	int f;
	cardEx01() {
		장면 = new CardLayout();
		setLayout(장면);

		JPanel 하나 = new JPanel(null);
		JPanel 둘 = new JPanel(null);

		하나.setBackground(Color.orange);
		둘.setBackground(Color.pink);

		add(하나, "하나띄우기");
		add(둘, "둘띄우기");

		setSize(300, 300);
		setDefaultCloseOperation(3);
		setVisible(true);
//		장면.show(this.getContentPane(), "하나띄ㅜ")
		JButton 버튼 = new JButton("전환");

		버튼.setBounds(100, 100, 50, 50);
		하나.add(버튼);
		둘.add(버튼);

		버튼.addActionListener(this);
		f = 0;

		while (true) {
			Scanner sc = new Scanner(System.in);

			int i = sc.nextInt();

			if (i == 0) {
				장면.show(this.getContentPane(), "하나띄우기");
			} else {
				장면.show(this.getContentPane(), "둘띄우기");
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (f == 0) {
			장면.show(this.getContentPane(), "하나띄우기");
			f = 1;
		} else {
			장면.show(this.getContentPane(), "둘띄우기");
			f = 0;
		}
	}
}
