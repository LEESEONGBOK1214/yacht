package 야추게임;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class 배경화면 extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// 노트북에서
		Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\git\\yacht\\야추\\src\\배경화면.jpg");
		
		// 자취방에서
		//Image img = Toolkit.getDefaultToolkit().getImage("D:\\JAVA\\2학년2학기\\src\\윷놀이\\윷놀이.jpg");

		g.drawImage(img, 0, 0, 700, 700, this);
	}
}