package ���߰���;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

class ����� extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img= Toolkit.getDefaultToolkit().getImage("");
		g.drawImage(img, 0,0, 820,930,this);
	}
}

public class ���ȭ�� extends �����{

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setFont(new Font(null,1,80));
		g.setColor(Color.orange);
		g.drawString("���� �����....", 120, 470);
	}
	
}
