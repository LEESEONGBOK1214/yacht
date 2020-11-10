package 화면;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import 야추메인.야추Frame;

@SuppressWarnings("serial")
public class 굴림판 extends JPanel implements ActionListener {
	주사위판 주사위판;
	저장판 저장판;
	주사위 주사위들[];
	private static JLabel 차례표시;
	private static JButton 굴림버튼;
	private static JButton 점수화면전환;

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
		add(get점수화면전환());
//		차례표시세팅();
//		add(차례표시);
	}

	private void 주사위판세팅(주사위[] 주사위들) {
		주사위판 = new 주사위판(주사위들);
		주사위판.setBorder(new LineBorder(Color.red));
	}

	private void 점수화면버튼세팅() {
		set점수화면전환(new JButton("<html>점수<br>화면</html>"));
		get점수화면전환().setName("점수화면으로");
		get점수화면전환().setVisible(false);
		get점수화면전환().addActionListener(this);
		get점수화면전환().setBounds(20, 250, 50, 100);
	}

	private void 차례표시세팅() {
		set차례표시(new JLabel());
		get차례표시().setBounds(250, 20, 200, 100);
		get차례표시().setBorder(new LineBorder(Color.green));
	}

	private void 굴림버튼세팅() {
		// TODO Auto-generated method stub
		굴림버튼 = new JButton("굴리기");
		굴림버튼.setVisible(false);
		굴림버튼.setBounds(300, 500, 100, 50);
		굴림버튼.setBackground(Color.white);
		굴림버튼.setOpaque(false);
		굴림버튼.addActionListener(this);
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
		야추Frame.get게임화면().set턴(야추Frame.get게임화면().get턴() + 1);
		;
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
		System.out.println("굴림 에서의 턴 : " + 야추Frame.get게임화면().턴);
		주사위판.repaint();

		System.out.println("턴 : " + 야추Frame.get게임화면().턴);
		if (야추Frame.get게임화면().get턴() == 4) {
			마지막굴림();
			야추Frame.outprint("마지막굴림");
		}
	}

	public void 마지막굴림() {
		Thread 잠시대기 = new Thread(new Runnable() {

			public void run() {
				try {
					굴림버튼.setEnabled(false);
					Thread.sleep(1500);
					야추Frame.get게임화면().점수판으로();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		잠시대기.start();
	}

	public void 굴리기(String 응답) {
		// 상대방 결과를 그려주는 메소드.
		// 그저 그려주기만 하면 됨.
		for (int i = 0; i < 주사위들.length; i++) {
			if (!주사위들[i].저장중) { // 저장중이 아니면,
				주사위들[i].눈금 = (응답.charAt(i) - 48); // 문자이므로 -48 해줘야함.
				주사위들[i].굴림();
			}
		}
		주사위판.repaint();
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
		add(get점수화면전환());
	}

	public JButton get굴림버튼() {
		return 굴림버튼;
	}

	public void set굴림버튼(JButton _굴림버튼) {
		굴림버튼 = _굴림버튼;
	}

	public JButton get점수화면전환() {
		return 점수화면전환;
	}

	public void set점수화면전환(JButton _점수화면전환) {
		점수화면전환 = _점수화면전환;
	}

	public JLabel get차례표시() {
		return 차례표시;
	}

	public void set차례표시(JLabel _차례표시) {
		차례표시 = _차례표시;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 굴리기버튼 만 있음.

		JButton 눌린버튼 = (JButton) e.getSource();
		if (눌린버튼 == 굴림버튼) {
			야추Frame.get게임화면().get굴림판().굴리기();
		} else if (눌린버튼 == 점수화면전환) {

			야추Frame.get게임화면().점수판으로();
			System.out.println("점수화면으로 선택.");
			야추Frame.outprint("점수판으로");
		}
	}

	public void 턴시작(boolean 세팅값) {
		점수화면전환.setVisible(세팅값);
		굴림버튼.setVisible(세팅값);

		for (int i = 0; i < 5; i++) {
			this.주사위들[i].눈금 = 0;
			this.주사위들[i].저장중 = false;
			ImageIcon icon = new ImageIcon(getClass().getResource("/images/No.png"));
			주사위들[i].setIcon(icon);
		}
	}
}
