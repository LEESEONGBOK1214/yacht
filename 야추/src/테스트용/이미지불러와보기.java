package 테스트용;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class 이미지불러와보기{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new 프레임();
		
	}

}


class 프레임 extends JFrame{
	프레임(){
		ImageIcon 이미지 = new ImageIcon("C:\\Users\\chlsk\\git\\yacht\\야추\\src\\yacht배경.jpg");
		JLabel 이미지담는라벨 = new JLabel(이미지);
		
		System.out.println(이미지);
		setSize(700, 700);
		
		JPanel 패널 = new JPanel();
		
		패널.add(이미지담는라벨);
		
		add(패널);
		
		setVisible(true);
		setDefaultCloseOperation(3);
	}
}