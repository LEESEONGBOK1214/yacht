package �׽�Ʈ��;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class �̹����ҷ��ͺ���{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ������();
		
	}

}


class ������ extends JFrame{
	������(){
		ImageIcon �̹��� = new ImageIcon("C:\\Users\\chlsk\\git\\yacht\\����\\src\\yacht���.jpg");
		JLabel �̹�����¶� = new JLabel(�̹���);
		
		System.out.println(�̹���);
		setSize(700, 700);
		
		JPanel �г� = new JPanel();
		
		�г�.add(�̹�����¶�);
		
		add(�г�);
		
		setVisible(true);
		setDefaultCloseOperation(3);
	}
}