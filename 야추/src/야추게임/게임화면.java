package ���߰���;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

import ����_����.����;

public class ����ȭ�� extends JPanel implements ActionListener, MouseListener {
	static ������ ������;
	static ������ ������;
	static �ֻ��� �ֻ�����[] = new �ֻ���[5];

	public static int �� = 0;

	static CardLayout ���;
	���� ����A, ����B;
	static ���� ������;
	public ����ȭ��(���� ����A, ���� ����B) {
		// �� �Ӽ� ����
		��� = new CardLayout();
		setLayout(���);

		// ��ü �ʱ�ȭ
		this.����A = ����A;
		this.����B = ����B;
		
		��������(new Random().nextBoolean());
		
		

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
	}

	private void ��������(boolean b) {
		// TODO Auto-generated method stub
		this.����A.set����(b);
		this.����B.set����(!b);

		if (����A.is����()) {
			������ = ����A;
		} else {
			������ = ����B;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	void �ֻ�������() {
		// ��� �� �����ϰ� ���� �׷��ֱ� ����.
//		if(!����) {
//			// ���� = false�� �� ���� �ƴ�.
//			return;
//		}
		System.out.print("�ֻ������� >");
		// ������=true�� �ֻ��� ����ŭ �׸���

		System.out.print("for in >");
		for (int i = 0; i < 5; i++) { // �ֻ����� ������ 5��.
			������.������.repaint();
			������.�ֻ�����.repaint();

			if (�ֻ�����[i].������) {
				System.out.println("\n�ֻ���" + i + "��°�� ������!");

				�ֻ�����[i].setBounds(82 * i, 0, 70, 70);
				������.������.add(�ֻ�����[i]);
			} else {
				System.out.println("\n�ֻ���" + i + "��°�� �������!");

				�ֻ�����[i].setBounds((i * 102) + 10, 0, 70, 70);
				������.�ֻ�����.add(�ֻ�����[i]);
			}
		}
		System.out.print("for out >");
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// ���콺 Ŭ�� ��
//		if(!����) {
//			// ���� = false�� �� ���� �ƴ�.
//			return;
//		}
		System.out.print("��ȭMouseClicked > ");
		System.out.println(e.getSource().getClass());
		if (e.getSource().getClass() == �ֻ���.class) {
			// �����Ѱ� �ֻ��� �̸�!!!
			�ֻ��� �����ֻ��� = (�ֻ���) e.getSource();
			�����ֻ���.������ = !�����ֻ���.������;
			System.out.print("if�� >");
			�ֻ�������();
		}

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



}