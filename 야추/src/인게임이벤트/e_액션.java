package �ΰ����̺�Ʈ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ���߸���.����Frame;

public class e_�׼� implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("e.getActionCommand : " + e.getActionCommand());

		String ��� = e.getActionCommand();

		if (e.getSource().getClass().equals(JButton.class)) {
			System.out.println("JButton Ŭ��.");
			switch (���) {
			case "������":
				����Frame.get����ȭ��().get������().������();
				return;
			}

			JButton ������ư = (JButton) e.getSource();
			if (������ư.getName().equals("�������ù�ư")) {
//				int ���� = Integer.parseInt(������ư.getText()) + ����Frame.get����ȭ��().get������();
//				����Frame.get����ȭ��().set������(����);
				������ư.setVisible(false);
				����Frame.get����ȭ��().����������();
				����Frame.outprint("��������/" + ������ư.getText());
			}
		}
	}
}
