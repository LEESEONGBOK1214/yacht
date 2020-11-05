package 화면;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import 야추_서버.유저;
import 야추메인.야추Frame;

@SuppressWarnings("serial")
public class 게임화면 extends JPanel implements MouseListener {
	private static 점수판 점수판;
	private static 굴림판 굴림판;
	private 주사위 주사위들[] = new 주사위[5];

	public static int 턴 = 0;
	int 포트번호들[] = new int[2];
	static CardLayout 장면;
//	static 유저 턴유저;

	public 게임화면() {
		// 방 생성하는거거든.
		// 판 속성 세팅
		장면 = new CardLayout();
		setLayout(get장면());

		// 객체 초기화
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

		get장면().show(this, "주사위굴리기");
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



	public void 주사위선택() {
		for (int i = 0; i < 5; i++) { // 주사위는 무조건 5개.
			굴림판.저장판.repaint();
			굴림판.주사위판.repaint();

			if (주사위들[i].저장중) {
				주사위들[i].setBounds(82 * i, 0, 70, 70);
				굴림판.저장판.add(주사위들[i]);
			} else {
				주사위들[i].setBounds((i * 102) + 10, 0, 70, 70);
				굴림판.주사위판.add(주사위들[i]);
			}
		}
	}
	
	public void 주사위저장함(String 선택주사위) {
		// 상대방이 저장한 주사위값 가져와야함.
		for(주사위 주사위 : 주사위들) {
			if(주사위.getName().equals(선택주사위)) {// 누른 주사위라면,
				주사위.저장중 = !주사위.저장중;
			}
		}
		주사위선택();
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("");System.out.println("");System.out.println("");System.out.println("");
		System.out.print("게임화면 > MouseClicked > ");
		System.out.println(e.getSource().getClass());
		if (e.getSource().getClass() == 주사위.class) {
			// 선택한게 주사위 이면!!!
			주사위 선택주사위 = (주사위) e.getSource();
			선택주사위.저장중 = !선택주사위.저장중;
			야추Frame.outprint("주사위저장/" + 선택주사위.getName());
			System.out.println("선택 주사위 : " + 선택주사위.getName());
			주사위선택();
		}

	}

	// get set --------------------------------------------------------------



	public 점수판 get점수판() {
		return 점수판;
	}

	public void set점수판(점수판 셋점수판) {
		점수판 = 셋점수판;
	}

	public 굴림판 get굴림판() {
		return 굴림판;
	}

	public void set굴림판(굴림판 셋굴림판) {
		굴림판 = 셋굴림판;
	}

	public 주사위[] get주사위들() {
		return 주사위들;
	}

	public void set주사위들(주사위[] 주사위들) {
		this.주사위들 = 주사위들;
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public static CardLayout get장면() {
		return 장면;
	}

	
}