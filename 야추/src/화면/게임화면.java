package ȭ��;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import ���߸���.����Frame;

@SuppressWarnings("serial")
public class ����ȭ�� extends JPanel implements MouseListener {
	private static ������ ������;
	private static ������ ������;
	private �ֻ��� �ֻ�����[] = new �ֻ���[5];

	public static int �� = 0;
	int ��Ʈ��ȣ��[] = new int[2];
	static CardLayout ���;
	String �������;
//	static ���� ������;

	public ����ȭ��() {
		// �� �����ϴ°Űŵ�.
		// �� �Ӽ� ����
		��� = new CardLayout();
		setLayout(get���());

		// ��ü �ʱ�ȭ
		for (int i = 0; i < �ֻ�����.length; i++) {
			�ֻ�����[i] = new �ֻ���((i * 102) + 10, 0, 70);
			�ֻ�����[i].addMouseListener(this);
		}
		������ = new ������(�ֻ�����);
		������ = new ������(�ֻ�����);
		// ��ü ����
		// ����
		add(������, "�ֻ���������");
		add(������, "���������ϱ�");

		���.show(this, "�ֻ���������");
		�������="������";
	}

	public void ����������() {
//		������.��������();
		������.���õ�();
		���.show(this, "���������ϱ�");
		�������="������";
	}

	public void ����������() {
		������.���õ�();
		������.�ֻ�������();
		���.show(this, "�ֻ���������");
		�������="������";
	}

	public void �ֻ�������() {
		int count = 0;
		for (int i = 0; i < 5; i++) { // �ֻ����� ������ 5��.
			������.������.repaint();
			������.�ֻ�����.repaint();

			if (�ֻ�����[i].������) {
				�ֻ�����[i].setBounds(82 * i, 0, 70, 70);
				������.������.add(�ֻ�����[i]);
				count++;
			} else {
				�ֻ�����[i].setBounds((i * 102) + 10, 0, 70, 70);
				������.�ֻ�����.add(�ֻ�����[i]);
			}
		}
		System.out.println("�ֻ��� �����Ҷ� �� �ȵ�? " + count);
		if (count == 5) {
			ȭ��.������.get������ư().setVisible(false);
		} else {
			ȭ��.������.get������ư().setVisible(true);
		}
	}

	public void �ֻ���������(String �����ֻ���) {
		// ������ ������ �ֻ����� �����;���.

		for (�ֻ��� �ֻ��� : �ֻ�����) {
			if (�ֻ���.getName().equals(�����ֻ���)) {// ���� �ֻ������,
				�ֻ���.������ = !�ֻ���.������;
			}
			System.out.println("�ֻ���.������ : " + �ֻ���.������);
		}
		�ֻ�������();

	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.print("����ȭ�� > MouseClicked > ");
		System.out.println(e.getSource().getClass());
		
		if(�������.equals("������"))return;
		
		if (e.getSource().getClass() == �ֻ���.class) {
			// �����Ѱ� �ֻ��� �̸�!!!
			�ֻ��� �����ֻ��� = (�ֻ���) e.getSource();
			if (�����ֻ���.���� != 0) {
				�����ֻ���.������ = !�����ֻ���.������;
				����Frame.outprint("�ֻ�������/" + �����ֻ���.getName());
				System.out.println("���� �ֻ��� : " + �����ֻ���.getName());
				�ֻ�������();
			}
		}

	}

	// get set --------------------------------------------------------------

	public ������ get������() {
		return ������;
	}

	public void set������(������ ��������) {
		������ = ��������;
	}

	public ������ get������() {
		return ������;
	}

	public void set������(������ �±�����) {
		������ = �±�����;
	}

	public �ֻ���[] get�ֻ�����() {
		return �ֻ�����;
	}

	public void set�ֻ�����(�ֻ���[] �ֻ�����) {
		this.�ֻ����� = �ֻ�����;
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public static CardLayout get���() {
		return ���;
	}

}