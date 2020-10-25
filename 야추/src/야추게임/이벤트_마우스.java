package 야추게임;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class 이벤트_마우스 implements MouseListener {


	public void mouseClicked(MouseEvent e) {
		JButton 버튼 = (JButton) e.getSource();
		
		switch (버튼.getName()) {
		case "점수화면으로":
			야추Frame.get게임화면().점수판으로();
			System.out.println("점수화면으로 선택.");
			break;
		case "굴림판으로":
			야추Frame.get게임화면().굴림판으로();
			System.out.println("되돌아감 선택");
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
