package 야추.화면;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import 야추.야추Frame;

enum scores{
	aces, deuces, threes, fours, fives, sixes, subtotal, choice, fourOfKind, fullHouse, sStraight, lStraight, yatch,
	bonus
}


@SuppressWarnings("serial")
public class 점수판 extends JPanel implements ActionListener {
	int size = 14;
	JLabel 목록[] = new JLabel[size];
	private JButton 선택버튼[] = new JButton[size];
	JLabel 상대점수[] = new JLabel[size];
	String 목록들[] = { "Aces", "Deuces", "Threes", "Fours", "Fives", "Sixes", "subtotal", "Choice", "4 of Kind",
			"Full House", "S.Straight", "L.Straight", "Yatch", "bonus" };
	int 점수[] = new int[size];
	주사위[] 주사위들;
	private JButton 돌아가기;
	private JLabel 유저점수[][] = new JLabel[2][2];
	private int 유저순서;

	점수판(주사위[] 주사위들) {
		setLayout(null);
		setBackground(Color.decode("#CDD1FF"));
		this.주사위들 = 주사위들;

		// 1~6 합 6개
		// 초이스, 4 of kind, Full house(2, 3),
		// S.straight, L.straight, yatch

		// 유저 두명의 점수 보이기
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				get유저점수()[i][j] = new JLabel("0"); // [유저][이름, 점수]
				get유저점수()[i][j].setBounds(480 + 160 * j, 200 + 130 * i, 150 - 100 * j, 50);
				get유저점수()[i][j].setBorder(new LineBorder(Color.yellow));
				add(get유저점수()[i][j]);
			}
		}


		for (int i = 0; i < 2; i++) {
			int 좌우간격 = 200;
			int 상하간격 = 65;
			int len = (size % 2 == 0) ? size : size + 1;

			for (int j = len / 2 * i; j < len / 2 * (i + 1); j++) {
				목록[j] = new JLabel(목록들[j]);
				목록[j].setBounds(20 + 좌우간격 * i, 상하간격 * (j % (len / 2)) + 30, 100, 50);

				선택버튼[j] = new JButton();
				선택버튼[j].setName(목록들[j]);
				선택버튼[j].setBackground(Color.white);
				선택버튼[j].setFocusPainted(false);
				선택버튼[j].setBounds(70 + (좌우간격 + 20) * i, 상하간격 * (j % (len / 2)) + 30, 50, 50);
				선택버튼[j].addActionListener(this);
				선택버튼[j].setEnabled(false);
				
				상대점수[j] = new JLabel("0");
				상대점수[j].setName(목록들[j]);
				상대점수[j].setHorizontalAlignment(JLabel.CENTER);

				JPanel tmp = new JPanel(new BorderLayout());
				tmp.setBounds(130 + (좌우간격 + 20) * i, 상하간격 * (j % (len / 2)) + 30, 50, 50);
				tmp.setBackground(Color.gray);
				tmp.setBorder(new LineBorder(Color.black));
				tmp.add(상대점수[j], BorderLayout.CENTER);

				add(목록[j]);
				add(선택버튼[j]);
				add(tmp);
			}
		}
//		for(int i=0;i<12;i++) {
//			선택[i] = new JButton(목록들[i]);
//		}
		돌아가기설정();
	}

	private void 돌아가기설정() {
		돌아가기 = new JButton("돌아가기");
		돌아가기.setName("굴림판으로");
		돌아가기.setVisible(false);
		돌아가기.setBounds(300, 600, 100, 50);
		돌아가기.addActionListener(this);
		add(돌아가기);
	}

	void 점수설정() {
		System.out.println("점수설정 >");

		// 점수 0으로 바꾸기
		for (int i = 0; i < size; i++) {
			점수[i] = 0;

		}
		// 1~6합.
		System.out.println("주사위 눈금 ==================================");
		for (주사위 주사위 : 주사위들) {
			System.out.println(주사위.눈금);
			점수[주사위.눈금 - 1] += 주사위.눈금;
			// 초이스
			점수[scores.choice.ordinal()] += 주사위.눈금;
		}
		System.out.println("주사위 눈금 ==================================");
		
//		// 보너스 -> 선택 할 때 추가.
//		for (int i = 0; i < 6; i++) {
//			점수[scores.bonus.ordinal()] += 점수[i];
//		}

		 
		// 포오카
		for (int i = 0; i < 6; i++) {
			if (점수[i] >= (i + 1) * 4) {
				int 포오카 = 0;
				for (int j = 0; j < 5; j++) {
					포오카 += 주사위들[j].눈금;
				}
				if (포오카 > 점수[scores.fourOfKind.ordinal()]) {
					점수[scores.fourOfKind.ordinal()] = 포오카;
				}
			}
		}
		// 풀하
		int a = 0, b = 0;
		for (int i = 0; i < 6; i++) {
			if (점수[i] >= (i + 1) * 2) {
				if (점수[i] == (i + 1) * 3) {
					if (점수[i] > a)
						a = 점수[i];
				} else if (점수[i] == (i + 1) * 2) {
					if (점수[i] > b)
						b = 점수[i];
				}
			}
		}
		if (a > 0 && b > 0)
			점수[scores.fullHouse.ordinal()] = a + b;

		// 스몰스트
		// 라지스트
		if (점수[scores.threes.ordinal()] > 0 && 점수[scores.fours.ordinal()] > 0) { // 3, 4는 무조건 있어야함.
			System.out.print("3, 4");
			if (점수[scores.deuces.ordinal()] > 0 && 점수[scores.fives.ordinal()] > 0) {// 2 3 4 5
				System.out.print(", 2, 5");
				if (점수[scores.aces.ordinal()] > 0 || 점수[scores.sixes.ordinal()] > 0) { // 1 2 3 4 5 || 2 3 4 5 6
					System.out.print(", 1 or 6");
					점수[10] = 30;
				}
				점수[9] = 15;
			} else if (점수[scores.aces.ordinal()] > 0 && 점수[scores.deuces.ordinal()] > 0) {// 1 2 3 4
				System.out.print(", 1, 2");
				// 1 2 3 4 5는 위에서 처리.
				점수[scores.sStraight.ordinal()] = 15;
			} else if (점수[scores.fives.ordinal()] > 0 && 점수[scores.sixes.ordinal()] > 0) { // 3 4 5 6
				System.out.print(", 5, 6");
				점수[scores.sStraight.ordinal()] = 15;
			}
			System.out.println();
		}

		// 야추
		for (int i = 0; i < 6; i++) {
			if (점수[i] == (i + 1) * 5) {
				점수[scores.yatch.ordinal()] = 50;
				break;
			}
		}

		for (int i = 0; i < size; i++) {
			remove(선택버튼[i]);
			선택버튼[i].setText("" + 점수[i]);
			System.out.println(목록들[i] + " : " + 선택버튼[i].getText());
			add(선택버튼[i]);
//			System.out.println(목록들[i] + " : " + 점수[i]);
		}
	}

	void 선택됨() {
		// 점수선택판 하단에 주사위 표시
		주사위판 주사위목록 = 야추Frame.get게임화면().get굴림판().주사위판;
		주사위목록.setBounds(100, 500, 500, 70);
		주사위목록.보여줘();

		점수설정();
		add(주사위목록);
		repaint();
	}

	public void set상대이름(String 상대이름) {
		get유저점수()[1][0].setText(상대이름);
		get유저점수()[1][1].setText("0");
	}

	public JLabel[][] get유저점수() {
		return 유저점수;
	}

	public void set유저점수(JLabel 유저점수[][]) {
		this.유저점수 = 유저점수;
	}

	public JButton get돌아가기() {
		return 돌아가기;
	}

	public void set돌아가기(JButton 돌아가기) {
		this.돌아가기 = 돌아가기;
	}

	public int get유저순서() {
		return 유저순서;
	}

	public void set유저순서(int 유저순서) {
		this.유저순서 = 유저순서;
	}

	public void 선택버튼셋(boolean b) {
		System.out.println("점수판 > 선택버튼셋 >");
		for (int i = 0; i < 선택버튼.length; i++) {
			System.out.println(i);
			if (!선택버튼[i].getName().equals("눌림")) {
				선택버튼[i].setEnabled(b);
			}
		}
		선택버튼[scores.subtotal.ordinal()].setEnabled(false);
		선택버튼[scores.bonus.ordinal()].setEnabled(false);
	}

	public void 점수세팅(String[] 응답) {
		for (int i = 0; i < 목록들.length; i++) {
			if (목록들[i].equals(응답[2])) {
				상대점수[i].setText(응답[3]);
				유저점수[(유저순서 + 1) % 2][1]
						.setText(Integer.parseInt(유저점수[(유저순서 + 1) % 2][1].getText()) + Integer.parseInt(응답[3]) + "");
				break;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton 눌린버튼 = (JButton) e.getSource();

		if (눌린버튼 == 돌아가기) {
			야추Frame.outprint("굴림판으로");
		} else { // 점수 선택 시,
			int 점수 = Integer.parseInt(눌린버튼.getText());

			switch (눌린버튼.getName()) {
			case "aces": case "deuces": case "threes": case"fours": case"fives": case"sixes":
				this.점수[scores.subtotal.ordinal()] = this.점수[scores.subtotal.ordinal()] + 점수;
				break;

			}
			
			눌린버튼.setEnabled(false);
			야추Frame.outprint("점수선택/" + 눌린버튼.getName() + "/" + 점수);
			눌린버튼.setName("눌림");
			야추Frame.get게임화면().턴 = 0;
			유저점수[유저순서][1].setText(Integer.parseInt(유저점수[유저순서][1].getText()) + 점수 + "");
//			야추Frame.get게임화면().set내점수(점수);
		}

		야추Frame.get게임화면().굴림판으로();

	}

	public void 턴시작(boolean 세팅값) {
		선택버튼셋(세팅값);
		get돌아가기().setVisible(세팅값);
	}

	public int get내점수() {
		return Integer.parseInt(유저점수[유저순서][1].getText());
	}

	public int get상대점수() {
		return Integer.parseInt(유저점수[(유저순서 + 1) % 2][1].getText());
	}

}
