package ȭ��;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import ����_����.����;

public class ����ȭ�� extends JPanel implements MouseListener {
	private ������ ������;
	private ������ ������;
	private �ֻ��� �ֻ�����[] = new �ֻ���[5];

	public static int �� = 0;

	static CardLayout ���;
//	static ���� ������;

	private ����[] �������; // ����
	
	private String ������; // �� �̸�

	public ����ȭ��() {
		// �� �����ϴ°Űŵ�.
		// �� �Ӽ� ����
		��� = new CardLayout();
		setLayout(get���());

		// ��ü �ʱ�ȭ

		������� = new ����[2];
		

//		��������(new Random().nextBoolean());

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

		get���().show(this, "���������ϱ�");
	}

	public void ����������() {
		������.���õ�();
		������.�ֻ�������();
		get���().show(this, "�ֻ���������");
	}



	void �ֻ�������() {
		// ��� �� �����ϰ� ���� �׷��ֱ� ����.
//		if(!����) {
//			// ���� = false�� �� ���� �ƴ�.
//			return;
//		}
		// ������ ���� ���ð����ϵ��� �����ϸ� �Ϻ�.
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

	private void ��������(boolean b) {
//		System.out.println("b : " + b);
//		get����A().set����(b);
//		get����B().set����(!b);

//		if (get����A().is����()) {
//			������ = get����A();
//		} else {
//			������ = get����B();
//		}
	}

//	public void ������(���� _user) {
//		����B = _user;
//	}

	public void �泪����(���� _user) {
//		if (_user == get����A()) {
//			// ����A�� ������,
//			set����A(����B);
//			set����B(null);
//		} else {
//			// ����B�� ������,
//			set����B(null);
//		}
//
//		if (get����A() == null && get����B() == null) {
//			����ȭ��.�����(this);
//			return;
//		}

	}

	public void Broadcast(byte[] data) {
		// �� �������� �����͸� �����ϴ� �޼��� ȣ��~
		// ex) user.SendData(data);
//		try {
//			����A.getSocket().getOutputStream().write(data);
//			// �̷������� ����Ʈ�迭�� ������. //
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}


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

	// get set --------------------------------------------------------------


//	public void set����A(���� _user) {
//		this.����A = _user; // Ư�� ����ڸ� �������� �����Ѵ�.
//	}
//
//	public void set����B(���� _user) {
//		this.����B = _user; // Ư�� ����ڸ� �������� �����Ѵ�.
//	}

//	public void �����߰�(���� �ű�����) {
//		����
//	}
	
	public void set������(String _name) {
		this.������ = _name;
	}

	public String get������() {
		return ������;
	}

//	public ���� get����A() {
//		return ����A;
//	}
//
//	public ���� get����B() {
//		return ����B;
//	}

	public ������ get������() {
		return ������;
	}

	public void set������(������ ������) {
		this.������ = ������;
	}

	public ������ get������() {
		return ������;
	}

	public void set������(������ ������) {
		this.������ = ������;
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