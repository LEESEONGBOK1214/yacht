package ���߰���;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ����ȭ�� extends JPanel implements ActionListener{
	�ֻ����� �ֻ�����;
	�h�� �h��;
	������ ������;
	JPanel ��ư��;
	JButton ������;
	boolean ����; // true�� �� ����.
	public ����ȭ��() {

		// �� �Ӽ� ����
		setBackground(Color.pink);
		setLayout(null);
		
		// ��ü �ʱ�ȭ
		�ֻ����� = new �ֻ�����();
		��ư�� = new JPanel();

		// ��ü ����
		�ֻ�����.setBounds(50, 50, 400, 50); // 50, 50���� 400 50ĭ��ŭ.
		�ֻ�����.setBorder(new LineBorder(Color.red));

		// ����
		add(�ֻ�����);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
