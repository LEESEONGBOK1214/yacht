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
		
		����();
		
		add(�ֻ�����);
		add(������);
		add(������ư);
		add(����ȭ����ȯ);
	}
	
	void ����() {
		for(int i=0;i<5;i++) {
			if(�ֻ�����[i].������) {
				�ֻ�����[i].setBounds(82 * i, 0, 70, 70);
				������.add(�ֻ�����[i]);
				
			}else {
				�ֻ�����[i].setBounds(�ֻ�����[i].x, 0, �ֻ�����[i].size, �ֻ�����[i].size);
				// n������ n �̷������� i+1������ �ʱ�ȭ
				�ֻ�����.add(�ֻ�����[i]);
			}
		}
	}

	void ������() {
		for (int i = 0; i < �ֻ�����.length; i++) {
			if (!�ֻ�����[i].������) { // �������� �ƴϸ�,
				
				�ֻ�����[i].������();
			}

		}
		�ֻ�����.repaint();
		����ȭ��.��++;
		if(����ȭ��.��==3) {
			����Frame.get����ȭ��().���� = false;
		}
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
