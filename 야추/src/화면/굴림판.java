package 화면;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import 야추메인.야추Frame;
import 인게임이벤트.e_마우스;
import 인게임이벤트.e_액션;
import 화면.주사위.흔들기;

@SuppressWarnings("serial")
public class 굴림판 extends JPanel {
	주사위판 주사위판;
	저장판 저장판;
	주사위 주사위들[];
	public static JLabel 차례표시;
	public static JButton 굴림버튼;
	public static JButton 점수화면전환;

	굴림판(주사위[] 주사위들) {
//		System.out.println("굴림판으로 옴.");
		setLayout(null);
		setBackground(Color.pink);

		this.주사위들 = 주사위들;
		굴림버튼세팅();
		주사위판세팅(주사위들);
		주사위세팅();
		
		저장판 = new 저장판();
		차례표시세팅();
		점수화면버튼세팅();
		
		add(주사위판);
		add(저장판);
		add(굴림버튼);
		add(점수화면전환);
//		차례표시세팅();
//		add(차례표시);
	}

	private void 주사위판세팅(주사위[] 주사위들) {
		주사위판 = new 주사위판(주사위들);
		주사위판.setBorder(new LineBorder(Color.red));
	}

	private void 점수화면버튼세팅() {
		점수화면전환 = new JButton("<html>점수<br>화면</html>");
		점수화면전환.setName("점수화면으로");
		점수화면전환.setVisible(false);
		점수화면전환.addMouseListener(new e_마우스());
		점수화면전환.setBounds(20, 250, 50, 100);
	}

	private void 차례표시세팅() {
		차례표시 = new JLabel();
		차례표시.setBounds(250, 20, 200, 100);
		차례표시.setBorder(new LineBorder(Color.green));
	}

	private void 굴림버튼세팅() {
		// TODO Auto-generated method stub
		굴림버튼 = new JButton("굴리기");
		굴림버튼.setVisible(false);
		굴림버튼.setBounds(300, 500, 100, 50);
		굴림버튼.setBackground(Color.white);
		굴림버튼.setOpaque(false);
		굴림버튼.addActionListener(new e_액션());
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

	public void 굴리기() {
		// 굴리기를 눌렀을 때, 숫자들을 다 정하고 이미지 흔들기를 따로 뽑아내.
		// 그리고 서버에 전송! 눈금 수를 받아서 그만큼 흔들기!
		게임화면.턴++;
		String 주사위눈금 = "";
		for (int i = 0; i < 주사위들.length; i++) {
			if (!주사위들[i].저장중) { // 저장중이 아니면,
				주사위들[i].굴리기();
			}
			주사위눈금 += 주사위들[i].눈금;
		}
		야추Frame.outprint("굴리기/" + 주사위눈금);
		// 굴린 값 출력.
		주사위눈금 = "";
		System.out.println("굴림 에서의 턴 : " + 게임화면.턴);
		주사위판.repaint();

		if (게임화면.턴 == 3) {
//			턴종료();
		}
	}
	
	public void 굴리기(String 응답) {
		// 굴리기를 눌렀을 때, 숫자들을 다 정하고 이미지 흔들기를 따로 뽑아내.
		// 그리고 서버에 전송! 눈금 수를 받아서 그만큼 흔들기!
		게임화면.턴++;
		for (int i = 0; i < 주사위들.length; i++) {
			if (!주사위들[i].저장중) { // 저장중이 아니면,
				주사위들[i].눈금=(응답.charAt(i)-48); // 문자이므로 -48 해줘야함.
				주사위들[i].굴림();
			}
		}
		System.out.println("굴림 에서의 턴 : " + 게임화면.턴);
		주사위판.repaint();

		if (게임화면.턴 == 3) {
			//3이면 턴종료 outprint로 알림. 상대턴임을 알려줌.
		}
	}

//	public void 턴종료() {
//		게임화면 턴종료화면 = 야추Frame.get게임화면();
////		턴종료화면.차례 = false;
//		턴종료화면.턴 = 0;
//		턴종료화면.get점수판().돌아가기.setVisible(false);
//		턴종료화면.점수판으로();
//	}

	public void 선택됨() {
		주사위판.setBounds(100, 350, 500, 70);
		add(주사위판);
		add(저장판);
		add(굴림버튼);
		add(점수화면전환);
	}
	
	public JButton get굴림버튼() {
		return 굴림버튼;
	}

	public void set굴림버튼(JButton 굴림버튼) {
		굴림판.굴림버튼 = 굴림버튼;
	}
}
