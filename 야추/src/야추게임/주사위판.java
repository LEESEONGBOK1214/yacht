package 야추게임;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class 주사위판 extends JPanel implements MouseListener {
	
	
	주사위판(주사위[] 주사위들) {
		setLayout(null);
		setBounds(100, 350, 500, 70);
		주사위들 = new 주사위[5];

		for (int i = 0; i < 주사위들.length; i++) {
			주사위들[i] = new 주사위();
			주사위들[i].randomset();
			주사위들[i].setName(i+"번째");
			주사위들[i].set이미지(주사위들[i].눈금);
			주사위들[i].setBounds((i * 102) + 10, 0, 70, 70);
			주사위들[i].addMouseListener(this);
			// n번쨰는 n 이런식으로 i+1값으로 초기화
			add(주사위들[i]);
		}
		
	}

	void setRoll() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource() + "눌림");
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
