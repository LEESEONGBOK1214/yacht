package ���߰���;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class �ֻ����� extends JPanel implements MouseListener {
	
	
	�ֻ�����(�ֻ���[] �ֻ�����) {
		setLayout(null);
		setBounds(100, 350, 500, 70);
		�ֻ����� = new �ֻ���[5];

		for (int i = 0; i < �ֻ�����.length; i++) {
			�ֻ�����[i] = new �ֻ���();
			�ֻ�����[i].randomset();
			�ֻ�����[i].setName(i+"��°");
			�ֻ�����[i].set�̹���(�ֻ�����[i].����);
			�ֻ�����[i].setBounds((i * 102) + 10, 0, 70, 70);
			�ֻ�����[i].addMouseListener(this);
			// n������ n �̷������� i+1������ �ʱ�ȭ
			add(�ֻ�����[i]);
		}
		
	}

	void setRoll() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource() + "����");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
