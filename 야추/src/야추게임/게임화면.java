package 야추게임;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class 게임화면 extends JPanel implements ActionListener{
	주사위판 주사위판;
	킾판 킾판;
	점수판 점수판;
	JPanel 버튼판;
	JButton 굴리기;
	boolean 차례; // true면 내 차례.
	public 게임화면() {

		// 판 속성 세팅
		setBackground(Color.pink);
		setLayout(null);
		
		// 객체 초기화
		주사위판 = new 주사위판();
		버튼판 = new JPanel();

		// 객체 세팅
		주사위판.setBounds(50, 50, 400, 50); // 50, 50부터 400 50칸만큼.
		주사위판.setBorder(new LineBorder(Color.red));

		// 세팅
		add(주사위판);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
