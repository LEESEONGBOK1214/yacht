package ȭ��;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ���߸���.����Frame;
import �ΰ����̺�Ʈ.e_���콺;
import �ΰ����̺�Ʈ.e_�׼�;
import ȭ��.�ֻ���.����;

@SuppressWarnings("serial")
public class ������ extends JPanel {
	�ֻ����� �ֻ�����;
	������ ������;
	�ֻ��� �ֻ�����[];
	public static JLabel ����ǥ��;
	public static JButton ������ư;
	public static JButton ����ȭ����ȯ;

	������(�ֻ���[] �ֻ�����) {
//		System.out.println("���������� ��.");
		setLayout(null);
		setBackground(Color.pink);

		this.�ֻ����� = �ֻ�����;
		������ư����();
		�ֻ����Ǽ���(�ֻ�����);
		�ֻ�������();
		
		������ = new ������();
		����ǥ�ü���();
		����ȭ���ư����();
		
		add(�ֻ�����);
		add(������);
		add(������ư);
		add(����ȭ����ȯ);
//		����ǥ�ü���();
//		add(����ǥ��);
	}

	private void �ֻ����Ǽ���(�ֻ���[] �ֻ�����) {
		�ֻ����� = new �ֻ�����(�ֻ�����);
		�ֻ�����.setBorder(new LineBorder(Color.red));
	}

	private void ����ȭ���ư����() {
		����ȭ����ȯ = new JButton("<html>����<br>ȭ��</html>");
		����ȭ����ȯ.setName("����ȭ������");
		����ȭ����ȯ.setVisible(false);
		����ȭ����ȯ.addMouseListener(new e_���콺());
		����ȭ����ȯ.setBounds(20, 250, 50, 100);
	}

	private void ����ǥ�ü���() {
		����ǥ�� = new JLabel();
		����ǥ��.setBounds(250, 20, 200, 100);
		����ǥ��.setBorder(new LineBorder(Color.green));
	}

	private void ������ư����() {
		// TODO Auto-generated method stub
		������ư = new JButton("������");
		������ư.setVisible(false);
		������ư.setBounds(300, 500, 100, 50);
		������ư.setBackground(Color.white);
		������ư.setOpaque(false);
		������ư.addActionListener(new e_�׼�());
	}

	void �ֻ�������() {
		for (int i = 0; i < 5; i++) {
			if (�ֻ�����[i].������) {
				�ֻ�����[i].setBounds(82 * i, 0, 70, 70);
				������.add(�ֻ�����[i]);

			} else {
				�ֻ�����[i].setBounds(�ֻ�����[i].x, 0, �ֻ�����[i].size, �ֻ�����[i].size);
				// n������ n �̷������� i+1������ �ʱ�ȭ
				�ֻ�����.add(�ֻ�����[i]);
			}
		}
	}

	public void ������() {
		// �����⸦ ������ ��, ���ڵ��� �� ���ϰ� �̹��� ���⸦ ���� �̾Ƴ�.
		// �׸��� ������ ����! ���� ���� �޾Ƽ� �׸�ŭ ����!
		����ȭ��.��++;
		String �ֻ������� = "";
		for (int i = 0; i < �ֻ�����.length; i++) {
			if (!�ֻ�����[i].������) { // �������� �ƴϸ�,
				�ֻ�����[i].������();
			}
			�ֻ������� += �ֻ�����[i].����;
		}
		����Frame.outprint("������/" + �ֻ�������);
		// ���� �� ���.
		�ֻ������� = "";
		System.out.println("���� ������ �� : " + ����ȭ��.��);
		�ֻ�����.repaint();

		if (����ȭ��.�� == 3) {
//			������();
		}
	}
	
	public void ������(String ����) {
		// �����⸦ ������ ��, ���ڵ��� �� ���ϰ� �̹��� ���⸦ ���� �̾Ƴ�.
		// �׸��� ������ ����! ���� ���� �޾Ƽ� �׸�ŭ ����!
		����ȭ��.��++;
		for (int i = 0; i < �ֻ�����.length; i++) {
			if (!�ֻ�����[i].������) { // �������� �ƴϸ�,
				�ֻ�����[i].����=(����.charAt(i)-48); // �����̹Ƿ� -48 �������.
				�ֻ�����[i].����();
			}
		}
		System.out.println("���� ������ �� : " + ����ȭ��.��);
		�ֻ�����.repaint();

		if (����ȭ��.�� == 3) {
			//3�̸� ������ outprint�� �˸�. ��������� �˷���.
		}
	}

//	public void ������() {
//		����ȭ�� ������ȭ�� = ����Frame.get����ȭ��();
////		������ȭ��.���� = false;
//		������ȭ��.�� = 0;
//		������ȭ��.get������().���ư���.setVisible(false);
//		������ȭ��.����������();
//	}

	public void ���õ�() {
		�ֻ�����.setBounds(100, 350, 500, 70);
		add(�ֻ�����);
		add(������);
		add(������ư);
		add(����ȭ����ȯ);
	}
	
	public JButton get������ư() {
		return ������ư;
	}

	public void set������ư(JButton ������ư) {
		������.������ư = ������ư;
	}
}
