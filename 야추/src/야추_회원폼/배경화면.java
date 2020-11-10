package 야추_회원폼;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class 배경화면 extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// 노트북에서
		//Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\git\\yacht\\야추\\src\\배경화면.jpg");
		
		// 자취방에서
		Image img= Toolkit.getDefaultToolkit().getImage("C:\\Users\\chlsk\\git\\yacht\\야추\\src\\yacht배경.jpg");
		//System.out.println("C:\\Users\\chlsk\\git\\yacht\\야추\\src\\yacht배경.jpg");

		g.drawImage(img, 0, 0, 700, 700, this);
	}
}