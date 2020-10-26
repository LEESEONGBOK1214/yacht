package 야추게임;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class 굴림판 extends JPanel {
	주사위판 주사위판;
	저장판 저장판;
	주사위 주사위들[];
	JLabel 차례표시;
	JButton 굴림버튼;
	JButton 점수화면전환;

	굴림판(주사위[] 주사위들) {
		System.out.println("굴림판으로 옴.");
		setLayout(null);
		setBackground(Color.pink);

		this.주사위들 = 주사위들;
		굴림버튼세팅();
		주사위판 = new 주사위판(주사위들);
		주사위세팅();

		저장판 = new 저장판();

		차례표시세팅();

		주사위판.setBorder(new LineBorder(Color.red));

		점수화면전환 = new JButton("<html>점수<br>화면</html>");
		점수화면전환.setName("점수화면으로");

		점수화면전환.addMouseListener(new 이벤트_마우스());
		점수화면전환.setBounds(20, 250, 50, 100);

		add(주사위판);
		add(저장판);
		add(굴림버튼);
		add(점수화면전환);
		add(차례표시);
	}

	private void 차례표시세팅() {
		// TODO Auto-generated method stub

		차례표시 = new JLabel(게임화면.턴유저.get이름());
		차례표시.setBounds(325, 580, 100, 50);
	}

	private void 굴림버튼세팅() {
		// TODO Auto-generated method stub
		굴림버튼 = new JButton("굴리기");
		굴림버튼.setBounds(300, 500, 100, 50);
		굴림버튼.setBackground(Color.white);
		굴림버튼.setOpaque(false);
		굴림버튼.addActionListener(new 이벤트_액션());
	}

	void 주사위세팅() {
		for (int i = 0; i < 5; i++) {
			if (주사위들[i].저장중) {
				주사위들[i].setBounds(82 * i, 0, 70, 70);
				저장판.add(주사위들[i]);

			} else {
				주사위들[i].setBounds(주사위들[i].x, 0, 주사위들[i].size, 주사위들[i].size);
				// n번쨰는 n 이런식으로 i+1값으로 초기화
				주사위판.add(주사위들[i]);
			}
		}
	}

	void 굴리기() {
		게임화면.턴++;
		for (int i = 0; i < 주사위들.length; i++) {
			if (!주사위들[i].저장중) { // 저장중이 아니면,

				주사위들[i].굴리기();
			}

		}
		System.out.println("굴림 에서의 턴 : " + 게임화면.턴);
		주사위판.repaint();

		if (게임화면.턴 == 3) {
			턴종료();
		}
	}

	public void 턴종료() {
		게임화면 턴종료화면 = 야추Frame.get게임화면();
//		턴종료화면.차례 = false;
		턴종료화면.턴 = 0;
		턴종료화면.점수판.돌아가기.setVisible(false);
		턴종료화면.점수판으로();
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
