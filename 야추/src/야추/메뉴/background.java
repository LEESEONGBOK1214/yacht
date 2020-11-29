package 야추.메뉴;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class background extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		AlphaComposite ac;
		// 노트북에서
		// Image img = Tool
		// kit.getDefaultToolkit().getImage("C:\\Users\\user\\git\\yacht\\야추\\src\\배경화면.jpg");
		
		// 자취방에서
//		Image img= Toolkit.getDefaultToolkit().getImage("C:\\Users\\chlsk\\git\\yacht\\야추\\src\\yacht배경.jpg");
		//System.out.println("C:\\Users\\chlsk\\git\\yacht\\야추\\src\\yacht배경.jpg");

		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bgi2.png"));
		g.drawImage(img, 0, 0, 700, 700, this);
	}
}