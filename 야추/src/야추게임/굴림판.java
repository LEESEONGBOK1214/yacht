package 야추게임;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class 굴림판 extends JPanel  implements ActionListener{
	주사위판 주사위판;
	저장판 저장판;
	주사위 주사위들[];
	JButton 굴림버튼;
	JButton 점수화면전환;
	굴림판(주사위[] 주사위들){
		setLayout(null);
		setBackground(Color.pink);
		저장판 = new 저장판();
		주사위판 = new 주사위판(주사위들);
		this.주사위들 = 주사위들;
		주사위판.setBorder(new LineBorder(Color.red));
		
		점수화면전환 = new JButton("<html>점수<br>화면</html>");
		
		굴림버튼 = new JButton("굴리기");
		굴림버튼.setBounds(300, 500, 100, 50);
		굴림버튼.setBackground(Color.white);
		굴림버튼.setOpaque(false);
		굴림버튼.addActionListener(this);
		
		점수화면전환.setBounds(20, 250, 50,100);
		
		add(주사위판);
		add(저장판);
		add(굴림버튼);
		add(점수화면전환);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("굴림버튼 눌림?");
		
		for(int i =0;i<주사위들.length;i++) {
			if(!주사위들[i].저장중) { // 저장중이 아니면,
				주사위들[i].굴리기();
			}
			
			주사위판.repaint();
		}
	}
}
