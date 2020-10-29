package 화면;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import 야추게임.야추Frame;

public class 이벤트_액션 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("e.getActionCommand : " + e.getActionCommand());

		String 명령 = e.getActionCommand();

		if (e.getSource().getClass().equals(JButton.class)) {
			System.out.println("JButton 클릭.");
			switch (명령) {
			case "굴리기":
//				if (!야추Frame.get게임화면().차례) {
//					// 차례 = false면 내 차례 아님.
//					System.out.println("내 차례 아님.");
//					return;
//				}
				야추Frame.get게임화면().get굴림판().굴리기();
				return;
			}

			JButton 눌린버튼 = (JButton) e.getSource();
			if (눌린버튼.getName().equals("점수선택버튼")) {
//				int 점수 = Integer.parseInt(눌린버튼.getText()) + 야추Frame.get게임화면().get내점수();
//				야추Frame.get게임화면().set내점수(점수);
				눌린버튼.setVisible(false);
				야추Frame.get게임화면().굴림판으로();
			}
		}


	}

}
