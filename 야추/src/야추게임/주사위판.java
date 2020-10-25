package 야추게임;

import javax.swing.JPanel;

public class 주사위판 extends JPanel {
	
	주사위[] 주사위들;
	주사위판(주사위[] 주사위들) {
		setLayout(null);
		setBounds(100, 350, 500, 70);

		this.주사위들 = 주사위들;

		for (int i = 0; i < this.주사위들.length; i++) {
			주사위들[i].굴리기();
			주사위들[i].setName(i+"번째");
			주사위들[i].setBounds(주사위들[i].x, 0, 주사위들[i].size, 주사위들[i].size);
			// n번쨰는 n 이런식으로 i+1값으로 초기화
			add(주사위들[i]);
		}
		
	}

	void 보여줘() {
		removeAll();
		for (int i = 0; i < this.주사위들.length; i++) {
			주사위들[i].setBounds(주사위들[i].x, 0, 주사위들[i].size, 주사위들[i].size);
			add(주사위들[i]);
		}
	}
}
