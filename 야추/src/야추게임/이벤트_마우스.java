package ���߰���;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class �̺�Ʈ_���콺 implements MouseListener {


	public void mouseClicked(MouseEvent e) {
		JButton ��ư = (JButton) e.getSource();
		
		switch (��ư.getName()) {
		case "����ȭ������":
			����Frame.get����ȭ��().����������();
			System.out.println("����ȭ������ ����.");
			break;
		case "����������":
			����Frame.get����ȭ��().����������();
			System.out.println("�ǵ��ư� ����");
			break;
		}

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
