package 야추게임;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

import 야추_서버.유저;

public class 게임화면 extends JPanel implements ActionListener, MouseListener {
	static 점수판 점수판;
	static 굴림판 굴림판;
	static 주사위 주사위들[] = new 주사위[5];

	public static int 턴 = 0;

	static CardLayout 장면;
	유저 유저A, 유저B;
	static 유저 턴유저;
	public 게임화면(유저 유저A, 유저 유저B) {
		// 판 속성 세팅
		장면 = new CardLayout();
		setLayout(장면);

		// 객체 초기화
		this.유저A = 유저A;
		this.유저B = 유저B;
		
		유저세팅(new Random().nextBoolean());
		
		

		for (int i = 0; i < 주사위들.length; i++) {
			주사위들[i] = new 주사위((i * 102) + 10, 0, 70);
			주사위들[i].addMouseListener(this);
		}
		굴림판 = new 굴림판(주사위들);
		점수판 = new 점수판(주사위들);

		// 객체 세팅

		// 세팅

		add(굴림판, "주사위굴리기");
		add(점수판, "점수선택하기");

		장면.show(this, "주사위굴리기");
	}

	private void 유저세팅(boolean b) {
		// TODO Auto-generated method stub
		this.유저A.set차례(b);
		this.유저B.set차례(!b);

		if (유저A.is차례()) {
			턴유저 = 유저A;
		} else {
			턴유저 = 유저B;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	void 주사위선택() {
		// 요소 다 삭제하고 새로 그려주기 위함.
//		if(!차례) {
//			// 차례 = false면 내 차례 아님.
//			return;
//		}
		System.out.print("주사위선택 >");
		// 저장중=true인 주사위 수만큼 그리기

		System.out.print("for in >");
		for (int i = 0; i < 5; i++) { // 주사위는 무조건 5개.
			굴림판.저장판.repaint();
			굴림판.주사위판.repaint();

			if (주사위들[i].저장중) {
				System.out.println("\n주사위" + i + "번째가 저장중!");

				주사위들[i].setBounds(82 * i, 0, 70, 70);
				굴림판.저장판.add(주사위들[i]);
			} else {
				System.out.println("\n주사위" + i + "번째가 굴림대기!");

				주사위들[i].setBounds((i * 102) + 10, 0, 70, 70);
				굴림판.주사위판.add(주사위들[i]);
			}
		}
		System.out.print("for out >");
	}

	public void 점수판으로() {
//		점수판.점수설정();
		점수판.선택됨();
		
		장면.show(this, "점수선택하기");
	}

	public void 굴림판으로() {
		굴림판.선택됨();
		굴림판.주사위세팅();
		장면.show(this, "주사위굴리기");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 마우스 클릭 시
//		if(!차례) {
//			// 차례 = false면 내 차례 아님.
//			return;
//		}
		System.out.print("겜화MouseClicked > ");
		System.out.println(e.getSource().getClass());
		if (e.getSource().getClass() == 주사위.class) {
			// 선택한게 주사위 이면!!!
			주사위 선택주사위 = (주사위) e.getSource();
			선택주사위.저장중 = !선택주사위.저장중;
			System.out.print("if문 >");
			주사위선택();
		}

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