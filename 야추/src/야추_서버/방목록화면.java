package ����_����;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ���߰���.����ȭ��;
import ���߰���.����Frame;

public class ����ȭ�� extends JPanel implements ActionListener {
	// 1. GameRoom List �����ͼ� �ѷ��ֱ�.
	// 2. �� ���ý�, ����Ȯ�� �޼��� �����ֱ�.
	// 3. Ȯ�� ���ý�, ���ӹ� ����.

	static JPanel �����г�;
	static JPanel ��ư���;
	private static ArrayList<����ȭ��> ���� = new ArrayList<����ȭ��>(); // ���� ����Ʈ����

	public ����ȭ��() {
		��ư��� = new JPanel();

		setLayout(null);

		JButton �游��� = new JButton("�游���");
		JButton ���ΰ�ħ = new JButton("���ΰ�ħ");
		JButton ���� = new JButton("����");
		JButton �α׾ƿ� = new JButton("�α׾ƿ�");

		�����г� = new JPanel();
		�����г�.setName("�����г�");
		�����г�.setBackground(Color.orange);

		��ư���.setLayout(null);
		��ư���.add(�游���);
		��ư���.add(����);
		��ư���.add(���ΰ�ħ);
		��ư���.add(�α׾ƿ�);
		�游���.setBounds(20, 0, 150, 50);
		����.setBounds(192, 0, 150, 50);
		���ΰ�ħ.setBounds(363, 0, 150, 50);
		�α׾ƿ�.setBounds(535, 0, 150, 50);

		�游���.addActionListener(this);
		����.addActionListener(this);
		���ΰ�ħ.addActionListener(this);
		�α׾ƿ�.addActionListener(this);

		��Ϻ����ֱ�();

		�����г�.setBounds(10, 20, 685, 500);
		��ư���.setBounds(0, 600, 700, 200);

		add(�����г�);
		add(��ư���);
	}

	static void ��Ϻ����ֱ�() {
		�����г�.removeAll(); // ��� ���� �����ذ� ���� ����.
	}

	static void �游���() {
		System.out.print("�游��� > ");
		��Ϻ����ֱ�();
		try {
//			for (���� ���� : ���Ӽ���.�������) {
//				if(����.get���̵�() == ) {
//					
//				}
//			}
			Socket socket = ����Frame.getSocket();
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ost = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(ost, true);

			System.out.print("������ ��û : ");
			pw.println("�游���/" + socket.getLocalPort());
			System.out.println("�游���/" + socket.getLocalPort());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String ���� = "";
					try {

						while ((���� = in.readLine()) == null) {

						}
						JOptionPane.showMessageDialog(null, ����);
						System.out.println("���� : " + ����);
						/*
						 * ���� ������ ���� ������ְ�, �� ���� �ǵ��� �ؾ���.
						 * 
						 */

						if (����.equals("�游��� ����.")) {
							����ȭ�� ���� = new ����ȭ��(������);
							����.add(����);
//							����Frame.����ȭ������(������);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		String �Է¹�ư = e.getActionCommand();
		switch (�Է¹�ư) {
		case "�游���":
//			�����();
			�游���();
			break;
		case "����":
			break;
		case "���ΰ�ħ":
			break;
		case "�α׾ƿ�":
			����Frame.�޴���();
			break;
		}
	}



	public static ����ȭ�� �����(���� _owner) {
		// ������ ���� ������ �� ���(������ �������� ��)
		����ȭ�� room = new ����ȭ��(_owner);
		����.add(room);
		System.out.println("Room Created!");
		return room;
	}

	public static void �����(����ȭ�� ����ȭ��) {
		����.remove(����ȭ��); // ���޹��� ���� �����Ѵ�.
		System.out.println("Room Deleted!");
	}

	public static ArrayList<����ȭ��> getroomList() {
		return ����;
	}

	public static int roomCount() {
		return ����.size();
	} // ���� ũ�⸦ ������
}
