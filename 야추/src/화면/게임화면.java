package ȭ��;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import ����_����.����;
import ���߸���.����Frame;

@SuppressWarnings("serial")
public class ����ȭ�� extends JPanel implements MouseListener {
	private static ������ ������;
	private static ������ ������;
	private �ֻ��� �ֻ�����[] = new �ֻ���[5];

	public static int �� = 0;
	int ��Ʈ��ȣ��[] = new int[2];
	static CardLayout ���;
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

		get���().show(this, "�ֻ���������");
	}

	public void ����������() {
//		������.��������();
		������.���õ�();
		���.show(this, "���������ϱ�");
	}

	public void ����������() {
		������.���õ�();
		������.�ֻ�������();
		���.show(this, "�ֻ���������");
	}



	public void �ֻ�������() {
		for (int i = 0; i < 5; i++) { // �ֻ����� ������ 5��.
			������.������.repaint();
			������.�ֻ�����.repaint();

			if (�ֻ�����[i].������) {
				�ֻ�����[i].setBounds(82 * i, 0, 70, 70);
				������.������.add(�ֻ�����[i]);
			} else {
				�ֻ�����[i].setBounds((i * 102) + 10, 0, 70, 70);
				������.�ֻ�����.add(�ֻ�����[i]);
			}
		}
	}
	
	public void �ֻ���������(String �����ֻ���) {
		// ������ ������ �ֻ����� �����;���.
		for(�ֻ��� �ֻ��� : �ֻ�����) {
			if(�ֻ���.getName().equals(�����ֻ���)) {// ���� �ֻ������,
				�ֻ���.������ = !�ֻ���.������;
			}
		}
		�ֻ�������();
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("");System.out.println("");System.out.println("");System.out.println("");
		System.out.print("����ȭ�� > MouseClicked > ");
		System.out.println(e.getSource().getClass());
		if (e.getSource().getClass() == �ֻ���.class) {
			// �����Ѱ� �ֻ��� �̸�!!!
			�ֻ��� �����ֻ��� = (�ֻ���) e.getSource();
			�����ֻ���.������ = !�����ֻ���.������;
			����Frame.outprint("�ֻ�������/" + �����ֻ���.getName());
			System.out.println("���� �ֻ��� : " + �����ֻ���.getName());
			�ֻ�������();
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