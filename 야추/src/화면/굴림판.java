package ȭ��;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ���߸���.����Frame;

@SuppressWarnings("serial")
public class ������ extends JPanel implements ActionListener {
	�ֻ����� �ֻ�����;
	������ ������;
	�ֻ��� �ֻ�����[];
	private static JLabel ����ǥ��;
	private static JButton ������ư;
	private static JButton ����ȭ����ȯ;

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
		add(get����ȭ����ȯ());
//		����ǥ�ü���();
//		add(����ǥ��);
	}

	private void �ֻ����Ǽ���(�ֻ���[] �ֻ�����) {
		�ֻ����� = new �ֻ�����(�ֻ�����);
		�ֻ�����.setBorder(new LineBorder(Color.red));
	}

	private void ����ȭ���ư����() {
		set����ȭ����ȯ(new JButton("<html>����<br>ȭ��</html>"));
		get����ȭ����ȯ().setName("����ȭ������");
		get����ȭ����ȯ().setVisible(false);
		get����ȭ����ȯ().addActionListener(this);
		get����ȭ����ȯ().setBounds(20, 250, 50, 100);
	}

	private void ����ǥ�ü���() {
		set����ǥ��(new JLabel());
		get����ǥ��().setBounds(250, 20, 200, 100);
		get����ǥ��().setBorder(new LineBorder(Color.green));
	}

	private void ������ư����() {
		// TODO Auto-generated method stub
		������ư = new JButton("������");
		������ư.setVisible(false);
		������ư.setBounds(300, 500, 100, 50);
		������ư.setBackground(Color.white);
		������ư.setOpaque(false);
		������ư.addActionListener(this);
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
		����Frame.get����ȭ��().set��(����Frame.get����ȭ��().get��() + 1);
		;
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
		System.out.println("���� ������ �� : " + ����Frame.get����ȭ��().��);
		�ֻ�����.repaint();

		System.out.println("�� : " + ����Frame.get����ȭ��().��);
		if (����Frame.get����ȭ��().get��() == 4) {
			����������();
			����Frame.outprint("����������");
		}
	}

	public void ����������() {
		Thread ��ô�� = new Thread(new Runnable() {

			public void run() {
				try {
					������ư.setEnabled(false);
					Thread.sleep(1500);
					����Frame.get����ȭ��().����������();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		��ô��.start();
	}

	public void ������(String ����) {
		// ���� ����� �׷��ִ� �޼ҵ�.
		// ���� �׷��ֱ⸸ �ϸ� ��.
		for (int i = 0; i < �ֻ�����.length; i++) {
			if (!�ֻ�����[i].������) { // �������� �ƴϸ�,
				�ֻ�����[i].���� = (����.charAt(i) - 48); // �����̹Ƿ� -48 �������.
				�ֻ�����[i].����();
			}
		}
		�ֻ�����.repaint();
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
		add(get����ȭ����ȯ());
	}

	public JButton get������ư() {
		return ������ư;
	}

	public void set������ư(JButton _������ư) {
		������ư = _������ư;
	}

	public JButton get����ȭ����ȯ() {
		return ����ȭ����ȯ;
	}

	public void set����ȭ����ȯ(JButton _����ȭ����ȯ) {
		����ȭ����ȯ = _����ȭ����ȯ;
	}

	public JLabel get����ǥ��() {
		return ����ǥ��;
	}

	public void set����ǥ��(JLabel _����ǥ��) {
		����ǥ�� = _����ǥ��;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �������ư �� ����.

		JButton ������ư = (JButton) e.getSource();
		if (������ư == ������ư) {
			����Frame.get����ȭ��().get������().������();
		} else if (������ư == ����ȭ����ȯ) {

			����Frame.get����ȭ��().����������();
			System.out.println("����ȭ������ ����.");
			����Frame.outprint("����������");
		}
	}

	public void �Ͻ���(boolean ���ð�) {
		����ȭ����ȯ.setVisible(���ð�);
		������ư.setVisible(���ð�);

		for (int i = 0; i < 5; i++) {
			this.�ֻ�����[i].���� = 0;
			this.�ֻ�����[i].������ = false;
			ImageIcon icon = new ImageIcon(getClass().getResource("/images/No.png"));
			�ֻ�����[i].setIcon(icon);
		}
	}
}
