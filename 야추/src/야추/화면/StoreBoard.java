package 야추.화면;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class StoreBoard extends JPanel {
	StoreBoard(){
		setLayout(null);
		setBounds(150, 100, 400, 70);
		setBorder(new LineBorder(Color.blue));
	}
}
