package ���߰���;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class �̺�Ʈ_�׼� implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("e.getActionCommand : " + e.getActionCommand());

		String ��� = e.getActionCommand();
		switch (���) {
		case "������":
			if(!����Frame.get����ȭ��().����) {
				// ���� = false�� �� ���� �ƴ�.
				return;
			}
			����ȭ��.������.������();
			break;

		}
	}

}
