package ���߰���;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class �ֻ��� extends JLabel implements MouseListener{
	int ����;
	boolean ������ = false;
	String �̹�������[] = { "one.png", "two.png", "three.png", "four.png", "five.png", "six.png" };
	
	�ֻ���(){
		
	}
	void randomset() {
		Random r = new Random();
		
		���� = r.nextInt(6) + 1;
		System.out.println(����);
	}

	void set�̹���() {
//		ImageIcon icon = new ImageIcon("./images/" + �̹�������[���� - 1]);
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + �̹�������[����-1]));
		setIcon(icon);
		System.out.println("getIcon() : " + getIcon());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 ������ = !������;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void ������() {
		// TODO Auto-generated method stub
		randomset();
		set�̹���();
	}
	

	// �̹��� ��ó https://www.flaticon.com/kr/authors/smashicons"
}
