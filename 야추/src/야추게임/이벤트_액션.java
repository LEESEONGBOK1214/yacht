package 야추게임;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class 이벤트_액션 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("e.getActionCommand : " + e.getActionCommand());

		String 명령 = e.getActionCommand();
		switch (명령) {
		case "굴리기":
			if(!야추Frame.get게임화면().차례) {
				// 차례 = false면 내 차례 아님.
				return;
			}
			게임화면.굴림판.굴리기();
			break;

		}
	}

}
