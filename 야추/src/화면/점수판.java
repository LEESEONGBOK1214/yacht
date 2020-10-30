package 화면;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import 야추게임.야추Frame;
import 이벤트.e_마우스;
import 이벤트.e_액션;

public class 점수판 extends JPanel {
	JLabel 목록[] = new JLabel[12];
	JButton 선택[] = new JButton[12];
	String 목록들[] = { "Aces", "Deuces", "Threes", "Fours", "Fives", "Sixes", "Choice", "4 of Kind", "Full House",
			"S.Straight", "L.Straight", "Yatch" };
	int 점수[] = new int[12];
	주사위[] 주사위들;
	JButton 돌아가기;
	점수판(주사위[] 주사위들) {
		setLayout(null);
		setBackground(Color.decode("#CDD1FF"));
		this.주사위들 = 주사위들;
		
		
		// 1~6 합 6개
		// 초이스, 4 of kind, Full house(2, 3),
		// S.straight, L.straight, yatch

		for (int i = 0; i < 2; i++) {
			for (int j = 6 * i; j < 6 * (i + 1); j++) {
				목록[j] = new JLabel(목록들[j]);
				목록[j].setBounds(20 + 300 * i, 70 * (j % 6) + 50, 100, 50);
				
				선택[j] = new JButton();
				선택[j].setName("점수선택버튼");
				선택[j].setBackground(Color.white);
				선택[j].setFocusPainted(false);
				선택[j].setBounds(120 + 300 * i, 70 * (j % 6) + 50, 100, 50);
				선택[j].addActionListener(new e_액션());
				add(목록[j]);
				add(선택[j]);
			}
		}
		
//		for(int i=0;i<12;i++) {
//			선택[i] = new JButton(목록들[i]);
//		}
		돌아가기 = new JButton("돌아가기");
		돌아가기.setName("굴림판으로");
		돌아가기.setBounds(300, 600, 100, 50);
		돌아가기.addMouseListener(new e_마우스());
		add(돌아가기);
	}
	
	
	
	
	
	void 점수설정() {
		System.out.println("점수설정 >");

		// 점수 0으로 바꾸기
		for(int i=0;i<12;i++) {
			점수[i] = 0;

		}
		// 1~6합.		
		for(주사위 주사위 : 주사위들) {
			if(주사위.눈금 == 1) {
				점수[0] += 1;
			}else if(주사위.눈금 == 2) {
				점수[1] += 2;
			}else if(주사위.눈금 == 3) {
				점수[2] += 3;
			}else if(주사위.눈금 == 4) {
				점수[3] += 4;
			}else if(주사위.눈금 == 5) {
				점수[4] += 5;
			}else if(주사위.눈금 == 6) {
				점수[5] += 6;
			}
			//초이스
			점수[6] += 주사위.눈금;
		}
		// 포오카
		for(int i=0;i<6;i++) {
			if(점수[i] >= (i+1)*4) {
				int 포오카 = 0;
				for(int j=0;j<5;j++) {
					포오카 += 주사위들[j].눈금;
				}
				if(포오카 > 점수[7]) {
					점수[7] = 포오카;
				}
			}
		}
		// 풀하
		int a=0, b=0;
		for(int i=0;i<6;i++) {
			if(점수[i] >= (i+1)*2) {
				if(점수[i] == (i+1)*3) {
					if(점수[i] > a)a=점수[i];
				}else if(점수[i] == (i+1)*2) {
					if(점수[i] > b)b=점수[i];
				}
			}
		}
		if(a>0 && b>0)점수[8] = a+b;
		
		// 스몰스트
		// 라지스트
		if (점수[2] > 0 && 점수[3] > 0) { // 3, 4는 무조건 있어야함.
			System.out.print("3, 4");
			if(점수[1] >0 && 점수[4] >0) {// 2 3 4 5
				System.out.print(", 2, 5");
				if (점수[0] > 0 || 점수[5] > 0) { // 1 2 3 4 5 || 2 3 4 5 6
					System.out.print(", 1 or 6");
					점수[10] = 30;
				}
				점수[9] = 15;
			}
			else if(점수[0] >0 &&  점수[1] >0) {// 1 2 3 4
				System.out.print(", 1, 2");
				// 1 2 3 4 5는 위에서 처리.
				점수[9] = 15;
			}
			else if(점수[4] >0 && 점수[5] >0) { // 3 4 5 6
				System.out.print(", 5, 6");
				점수[9] = 15;
			}
			System.out.println();
		}
		
		
		// 야추
		for(int i =0;i<6;i++) {
			if(점수[i]== (i+1)*5) {
				점수[11] = 50;
				break;
			}
		}
		
		for(int i=0;i<12;i++) {
			remove(선택[i]);
			선택[i].setText("" + 점수[i]);
			System.out.println(목록들[i] + " : " + 선택[i].getText());
			add(선택[i]);
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
}
