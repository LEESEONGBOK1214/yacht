package 화면;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class 저장판 extends JPanel {
	저장판(){
		setLayout(null);
		setBounds(150, 100, 400, 70);
		setBorder(new LineBorder(Color.blue));
	}
}
