package ����_ȸ����;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ���ȭ�� extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// ��Ʈ�Ͽ���
		//Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\git\\yacht\\����\\src\\���ȭ��.jpg");
		
		// ����濡��
		Image img= Toolkit.getDefaultToolkit().getImage("C:\\Users\\chlsk\\git\\yacht\\����\\src\\yacht���.jpg");
		//System.out.println("C:\\Users\\chlsk\\git\\yacht\\����\\src\\yacht���.jpg");

		g.drawImage(img, 0, 0, 700, 700, this);
	}
}