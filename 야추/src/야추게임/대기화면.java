package 야추게임;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

class 대기배경 extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img= Toolkit.getDefaultToolkit().getImage("");
		g.drawImage(img, 0,0, 820,930,this);
	}
}

public class 대기화면 extends 대기배경{

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setFont(new Font(null,1,80));
		g.setColor(Color.orange);
		g.drawString("상대방 대기중....", 120, 470);
	}
	
}
