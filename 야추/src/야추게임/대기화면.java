package ���߰���;

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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ����_����.���Ӽ���;
import ����_����.�����;
import ����_����.����;

public class ���ȭ�� extends JPanel implements ActionListener {
	// 1. GameRoom List �����ͼ� �ѷ��ֱ�.
	// 2. �� ���ý�, ����Ȯ�� �޼��� �����ֱ�.
	// 3. Ȯ�� ���ý�, ���ӹ� ����.
	����Frame ����ȭ��;
	static JPanel �����г�;
	static JPanel ��ư���;
	static ����� ����� = new �����();

	���ȭ��(����Frame ����ȭ��) {
		this.����ȭ�� = ����ȭ��;
		��ư��� = new JPanel();

		setLayout(null);

		JButton �游��� = new JButton("�游���");
		JButton ���ΰ�ħ = new JButton("���ΰ�ħ");
		JButton ���� = new JButton("����");
		JButton �α׾ƿ� = new JButton("�α׾ƿ�");
//		JLabel �˻� = new JLabel()

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

		showRoomList();

		�����г�.setBounds(10, 20, 685, 500);
		��ư���.setBounds(0, 600, 700, 200);

		add(�����г�);
		add(��ư���);
	}

	void showRoomList() {
		�����г�.removeAll(); // ��� ���� �����ذ� ���� ����.
	}

	void �游���(String ���̵�) {
		���� ������ = null;
		showRoomList();
		try {
			for (���� ���� : ���Ӽ���.�������) {
				if(����.get���̵�() == ) {
					
				}
			}
			Socket socket = ������.getM_socket();
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ost = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(ost, true);

			pw.println("�游���/" + ������.get���̵�());
			System.out.println("ȸ������ pw.println ��.");
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
						if (����.equals("�游��� ����.")) {
							����ȭ�� ���� = new ����ȭ��(������);
							�����.getroomList().add(����);
							����ȭ��.����ȭ������(������);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();

//							if(rs != null && rs.next()) {
//								JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���̵��Դϴ�.");
//							}else {
//								String sql = "insert into ������ (id,passwd,name) values ('"+makeInfo.get���̵�ޱ�().getText()+"','"
//										+makeInfo.get��й�ȣ�ޱ�().getText()+"','"+makeInfo.get�̸��ޱ�().getText()+"')";
//								try {
//									stmt.executeUpdate(sql);
//									JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
//									card.show(mainCard, "�޴�");
//								} catch (SQLException e1) {
//									e1.printStackTrace();
//								}
//								System.out.println("����");
//							}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//						catch (NullPointerException e) {
//							System.out.println("����Frame() -> ȸ������() -> �̸��޴� ������ null �߻�.");
//						}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		String �Է¹�ư = e.getActionCommand();
		switch (�Է¹�ư) {
		case "�游���":

			�游���();
			break;
		case "����":
			break;
		case "���ΰ�ħ":
			break;
		case "�α׾ƿ�":
			break;
		}
	}

}
