package �׽�Ʈ��;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class �˸�â_JOptionPane {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// System.out.printf("%d %f %c %s", 1, 1.0, 1.0, 'c', "asdf");
		System.out.printf("%d\n", 1);
		System.out.printf("%f\n", 1.0);
		new Ex01();
	}
}

class Ex01 extends JFrame {
	Ex01() {
		super("�˸�â");
		JOptionPane �˸�â = new JOptionPane();
		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(3);
		
		
		// showMessageDialog
		// - Component parentComponent
		// �޽���â ���̾�αװ� � Frame���� ��Ÿ���� �� ��������
		// �����ִ� ����. null ���̰ų� �������� ���� Frame�� ���ٸ�
		// �⺻�� Frame(default Frame)�� �����˴ϴ�.
		// ���� null �� �ֵ� �� �� ���׿�.
		
		
		//�⺻ �˸�â�� null, �޼����� �Է�.
		// �ٸ� ���(Warning), ����(Error), ����(Question)
		�˸�â.showMessageDialog(null, "�˸�â�Դϴ�.");

	}

}