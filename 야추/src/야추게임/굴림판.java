package 야추게임;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class 굴림판 extends JPanel {
	주사위판 주사위판;
	저장판 저장판;
	주사위 주사위들[];
	JButton 굴림버튼;
	JButton 점수화면전환;
	굴림판(주사위[] 주사위들){
		System.out.println("굴림판으로 옴.");
		setLayout(null);
		setBackground(Color.pink);
		저장판 = new 저장판();
		주사위판 = new 주사위판(주사위들);
		this.주사위들 = 주사위들;
		주사위판.setBorder(new LineBorder(Color.red));
		
		점수화면전환 = new JButton("<html>점수<br>화면</html>");
		점수화면전환.setName("점수화면으로");
		
		굴림버튼 = new JButton("굴리기");
		굴림버튼.setBounds(300, 500, 100, 50);
		굴림버튼.setBackground(Color.white);
		굴림버튼.setOpaque(false);
		굴림버튼.addActionListener(new 이벤트_액션());
		
		점수화면전환.addMouseListener(new 이벤트_마우스());
		점수화면전환.setBounds(20, 250, 50, 100);
		
		add(주사위판);
		add(저장판);
		add(굴림버튼);
		add(점수화면전환);
	}

	void 굴리기() {
		for (int i = 0; i < 주사위들.length; i++) {
			if (!주사위들[i].저장중) { // 저장중이 아니면,
				주사위들[i].굴리기();
			}

		}
		주사위판.repaint();
		게임화면.턴++;
	}

	public void 선택됨() {
		// TODO Auto-generated method stub
		주사위판.setBounds(100, 350, 500, 70);
		add(주사위판);
		add(저장판);
		add(굴림버튼);
		add(점수화면전환);
	}

}
