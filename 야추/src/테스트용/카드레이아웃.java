package �׽�Ʈ��;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ī�巹�̾ƿ� {
	public static void main(String[] args) {
		System.out.println("�Ƕ�");
		new cardEx01();
	}
}

class cardEx01 extends JFrame implements ActionListener {
	CardLayout ���;
	int f;
	cardEx01() {
		��� = new CardLayout();
		setLayout(���);

		JPanel �ϳ� = new JPanel(null);
		JPanel �� = new JPanel(null);

		�ϳ�.setBackground(Color.orange);
		��.setBackground(Color.pink);

		add(�ϳ�, "�ϳ�����");
		add(��, "�Ѷ���");

		setSize(300, 300);
		setDefaultCloseOperation(3);
		setVisible(true);
//		���.show(this.getContentPane(), "�ϳ����")
		JButton ��ư = new JButton("��ȯ");

		��ư.setBounds(100, 100, 50, 50);
		�ϳ�.add(��ư);
		��.add(��ư);

		��ư.addActionListener(this);
		f = 0;

		while (true) {
			Scanner sc = new Scanner(System.in);

			int i = sc.nextInt();

			if (i == 0) {
				���.show(this.getContentPane(), "�ϳ�����");
			} else {
				���.show(this.getContentPane(), "�Ѷ���");
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (f == 0) {
			���.show(this.getContentPane(), "�ϳ�����");
			f = 1;
		} else {
			���.show(this.getContentPane(), "�Ѷ���");
			f = 0;
		}
	}
}
