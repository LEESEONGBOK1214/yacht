package ���߰���;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ����Frame extends JFrame {
	private �ֻ���Panel �ֻ���Panel;
	private ��������Panel ����Panel;
	private CardLayout ī��; // ī�� ���̾ƿ��� add�� ���̾ƿ����� �ϳ��� show ����.
	private JPanel ����ī��;
	����Frame() {
		super("yacht!");
		// �⺻ ����.
		setSize(700, 700);
		setLocation(1920 / 2 - this.getWidth() / 2, 1080 / 2 - this.getHeight() / 2); // ȭ�� ��� ����
		setVisible(true);
		setDefaultCloseOperation(3);// �ݱ� ������ ����.

		// =================================================================================================

		ī�� = new CardLayout();
		����ī�� = new JPanel(ī��);

	}
}
