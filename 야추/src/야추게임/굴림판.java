package ���߰���;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ������ extends JPanel  implements ActionListener{
	�ֻ����� �ֻ�����;
	������ ������;
	�ֻ��� �ֻ�����[];
	JButton ������ư;
	JButton ����ȭ����ȯ;
	������(�ֻ���[] �ֻ�����){
		setLayout(null);
		setBackground(Color.pink);
		������ = new ������();
		�ֻ����� = new �ֻ�����(�ֻ�����);
		this.�ֻ����� = �ֻ�����;
		�ֻ�����.setBorder(new LineBorder(Color.red));
		
		����ȭ����ȯ = new JButton("<html>����<br>ȭ��</html>");
		
		������ư = new JButton("������");
		������ư.setBounds(300, 500, 100, 50);
		������ư.setBackground(Color.white);
		������ư.setOpaque(false);
		������ư.addActionListener(this);
		
		����ȭ����ȯ.setBounds(20, 250, 50,100);
		
		add(�ֻ�����);
		add(������);
		add(������ư);
		add(����ȭ����ȯ);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("������ư ����?");
		
		for(int i =0;i<�ֻ�����.length;i++) {
			if(!�ֻ�����[i].������) { // �������� �ƴϸ�,
				�ֻ�����[i].������();
			}
			
			�ֻ�����.repaint();
		}
	}
}
