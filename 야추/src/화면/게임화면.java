package 화면;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import 야추_서버.유저;

public class 게임화면 extends JPanel implements MouseListener {
	private 점수판 점수판;
	private 굴림판 굴림판;
	private 주사위 주사위들[] = new 주사위[5];

	public static int 턴 = 0;

	static CardLayout 장면;
//	static 유저 턴유저;

	private 유저[] 유저목록; // 방장
	
	private String 방제목; // 방 이름

	public 게임화면() {
		// 방 생성하는거거든.
		// 판 속성 세팅
		장면 = new CardLayout();
		setLayout(get장면());

		// 객체 초기화

		유저목록 = new 유저[2];
		

//		유저세팅(new Random().nextBoolean());

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

		get장면().show(this, "점수선택하기");
	}

	public void 굴림판으로() {
		굴림판.선택됨();
		굴림판.주사위세팅();
		get장면().show(this, "주사위굴리기");
	}



	void 주사위선택() {
		// 요소 다 삭제하고 새로 그려주기 위함.
//		if(!차례) {
//			// 차례 = false면 내 차례 아님.
//			return;
//		}
		// 차례일 때만 선택가능하도록 수정하면 완벽.
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

	private void 유저세팅(boolean b) {
//		System.out.println("b : " + b);
//		get유저A().set차례(b);
//		get유저B().set차례(!b);

//		if (get유저A().is차례()) {
//			턴유저 = get유저A();
//		} else {
//			턴유저 = get유저B();
//		}
	}

//	public void 방입장(유저 _user) {
//		유저B = _user;
//	}

	public void 방나가기(유저 _user) {
//		if (_user == get유저A()) {
//			// 유저A가 나가면,
//			set유저A(유저B);
//			set유저B(null);
//		} else {
//			// 유저B가 나가면,
//			set유저B(null);
//		}
//
//		if (get유저A() == null && get유저B() == null) {
//			방목록화면.방삭제(this);
//			return;
//		}

	}

	public void Broadcast(byte[] data) {
		// 각 유저에게 데이터를 전송하는 메서드 호출~
		// ex) user.SendData(data);
//		try {
//			유저A.getSocket().getOutputStream().write(data);
//			// 이런식으로 바이트배열을 보낸다. //
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}


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

	// get set --------------------------------------------------------------


//	public void set유저A(유저 _user) {
//		this.유저A = _user; // 특정 사용자를 방장으로 변경한다.
//	}
//
//	public void set유저B(유저 _user) {
//		this.유저B = _user; // 특정 사용자를 방장으로 변경한다.
//	}

//	public void 유저추가(유저 신규유저) {
//		유저
//	}
	
	public void set방제목(String _name) {
		this.방제목 = _name;
	}

	public String get방제목() {
		return 방제목;
	}

//	public 유저 get유저A() {
//		return 유저A;
//	}
//
//	public 유저 get유저B() {
//		return 유저B;
//	}

	public 점수판 get점수판() {
		return 점수판;
	}

	public void set점수판(점수판 점수판) {
		this.점수판 = 점수판;
	}

	public 굴림판 get굴림판() {
		return 굴림판;
	}

	public void set굴림판(굴림판 굴림판) {
		this.굴림판 = 굴림판;
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