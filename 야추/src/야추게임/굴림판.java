package ���߰���;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ������ extends JPanel {
	�ֻ����� �ֻ�����;
	������ ������;
	�ֻ��� �ֻ�����[];
	JButton ������ư;
	JButton ����ȭ����ȯ;
	������(�ֻ���[] �ֻ�����){
		System.out.println("���������� ��.");
		setLayout(null);
		setBackground(Color.pink);
		������ = new ������();
		�ֻ����� = new �ֻ�����(�ֻ�����);
		this.�ֻ����� = �ֻ�����;
		�ֻ�����.setBorder(new LineBorder(Color.red));
		
		����ȭ����ȯ = new JButton("<html>����<br>ȭ��</html>");
		����ȭ����ȯ.setName("����ȭ������");
		
		������ư = new JButton("������");
		������ư.setBounds(300, 500, 100, 50);
		������ư.setBackground(Color.white);
		������ư.setOpaque(false);
		������ư.addActionListener(new �̺�Ʈ_�׼�());
		
		����ȭ����ȯ.addMouseListener(new �̺�Ʈ_���콺());
		����ȭ����ȯ.setBounds(20, 250, 50, 100);
		
		add(�ֻ�����);
		add(������);
		add(������ư);
		add(����ȭ����ȯ);
	}

	void ������() {
		for (int i = 0; i < �ֻ�����.length; i++) {
			if (!�ֻ�����[i].������) { // �������� �ƴϸ�,
				�ֻ�����[i].������();
			}

		}
		�ֻ�����.repaint();
		����ȭ��.��++;
	}

	public void ���õ�() {
		// TODO Auto-generated method stub
		�ֻ�����.setBounds(100, 350, 500, 70);
		add(�ֻ�����);
		add(������);
		add(������ư);
		add(����ȭ����ȯ);
	}

}
