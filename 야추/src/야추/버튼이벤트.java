package ����;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ����.�޴�.�α���;
import ����.�޴�.ȸ������;

public class ��ư�̺�Ʈ implements ActionListener {
	Socket socket = YatchFrame.socket;

	public void actionPerformed(ActionEvent e) {

//		System.out.println(e.getActionCommand());
		// ��ư�� ������
		// outprint�� ������ �����ϰ�
		// �� ���� switch�� ������
		// �ش� �� ó���ϴ� ������ �����սô�.

		CardLayout ��� = YatchFrame.get���();
		JPanel ����ȭ�� = YatchFrame.get����ȭ��();

		switch (e.getActionCommand()) {
		case "�α���":
			���.show(����ȭ��, "�α���");
			break;
		case "ȸ������": // ȸ������ â����
			���.show(����ȭ��, "ȸ������");
			break;
		case "����":
			����();
			break;
		case "���":
		case "�ڷ�":
			���.show(����ȭ��, "�޴�");
			break;
		case "����":
			����();
			break;
		case "�游���":
			�游���();
			break;
		case "���ΰ�ħ":
			outprint("���ΰ�ħ");
			break;
		case "�α׾ƿ�":
			outprint("�α׾ƿ�");
			break;
		case "��������":
			outprint("�泪����");
			break;
		case "���ӽ���":
			outprint("���ӽ���");
			break;
		}
	}

	private void �游���() {
		String title = JOptionPane.showInputDialog("�� ������ �Է����ּ���.");
		if (title != null) {
			outprint("�游���/" + title);
		}
	}

	private void ����() {
		String uid = �α���.getIdTextField().getText();
		String upw = �α���.getPasswdTextField().getText();
		if (!�α���.getIdTextField().getText().equals("")) {
			if (!�α���.getPasswdTextField().getText().equals("")) {
				outprint("�α���/" + uid + "/" + upw);
				YatchFrame.���̵� = uid;
			} else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���.");
		}
	}

	private void ����() {
		if (!ȸ������.get���̵�ޱ�().getText().equals("")) {
			if (!ȸ������.get��й�ȣ�ޱ�().getText().equals("")) {
				if (!ȸ������.get�̸��ޱ�().getText().equals("")) {
					String ���̵� = ȸ������.get���̵�ޱ�().getText();
					String ��й�ȣ = ȸ������.get��й�ȣ�ޱ�().getText();
					String �̸� = ȸ������.get�̸��ޱ�().getText();
					outprint("ȸ������/" + ���̵� + "/" + ��й�ȣ + "/" + �̸�);
				} else {
					JOptionPane.showMessageDialog(null, "�̸��� Ȯ���ϼ���");
				}
			} else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ���ϼ���");
			}
		} else {
			JOptionPane.showMessageDialog(null, "���̵� Ȯ���ϼ���");
		}
	}

	protected void outprint(String str) {
		try {
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			pw.println(socket.getLocalPort() + "/" + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
