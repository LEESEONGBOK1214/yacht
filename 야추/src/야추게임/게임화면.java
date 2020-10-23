package 야추게임;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class 게임화면 extends JPanel implements ActionListener{
	주사위판 주사위판;
	주사위 주사위들[];
	점수판 점수판;
	저장판 저장판;
	JPanel 버튼판;
	JButton 굴리기;
	int 턴;
	String 상태 = "";
	boolean 차례; // true면 내 차례.
	public 게임화면() {

		// 판 속성 세팅
		setBackground(Color.pink);
		setLayout(null);
		
		// 객체 초기화
		주사위판 = new 주사위판(주사위들);
		버튼판 = new JPanel();
		저장판 = new 저장판();
		
		
		굴리기 = new JButton("굴리기");
		
		// 객체 세팅
//		주사위판. // 50, 50부터 400 50칸만큼.
		주사위판.setBorder(new LineBorder(Color.red));
		
		굴리기.setBounds(300, 500, 100, 50);
		굴리기.setBackground(Color.white);
		굴리기.setOpaque(false);
		굴리기.addActionListener(this);
//		굴리기.setBorderPainted(false);
//		굴리기.setFocusPainted(false);
		
		// 세팅
		add(주사위판);
		add(굴리기);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void 주사위저장(주사위 주사위){
		// 요소 다 삭제하고 새로 그려주기 위함.
		removeAll();
		
		// 저장중=true인 주사위 수만큼 그리기
		for(int i=0;i<5;i++) { // 주사위는 무조건 5개.
			if()
		}
	}
}
