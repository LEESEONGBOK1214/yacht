package ���߸���;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ����_����.����;
import ����_Ŭ��.�α���;
import ����_Ŭ��.�޴�;
import ����_Ŭ��.ȸ������;
import ȭ��.����ȭ��;
import ȭ��.���ȭ��;
import ȭ��.����ȭ��;

@SuppressWarnings("serial")
public class ����Frame extends JFrame {
	private static ����ȭ�� ����ȭ��;

	private �޴� �޴�;
	private static ȸ������ ȸ������;
	private static �α��� �α���;
	private static CardLayout ���; // ī�� ���̾ƿ��� add�� ���̾ƿ����� �ϳ��� show ����.
	private static JPanel ����ȭ��;
	private static ����ȭ�� ����â;

	static Socket socket;
	Connection conn;
	PreparedStatement ptst;

	String ���� = null;

	����Frame() {
		super("yacht!");
		// �⺻ ����.
		setSize(720, 700);
		setLocation(600, 200); // ȭ�� ��� ����
		setDefaultCloseOperation(3);// �ݱ� ������ ����.
		// =================================================================================================
		try {
			socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8888);
		} catch (UnknownHostException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		// =================================================================================================
		��� = new CardLayout();
		����ȭ�� = new JPanel(���);
		�޴� = new �޴�();

		�޴�.get�α���().addActionListener(new ��ư�̺�Ʈ());
		�޴�.getȸ������().addActionListener(new ��ư�̺�Ʈ());
		�޴�.get�ٷν���().addActionListener(new ��ư�̺�Ʈ());
		�޴�.get���Ϻ���().addActionListener(new ��ư�̺�Ʈ());
		����ȭ��.add(�޴�, "�޴�");

		ȸ������ = new ȸ������();
		ȸ������.get����().addActionListener(new ��ư�̺�Ʈ());
		ȸ������.get���().addActionListener(new ��ư�̺�Ʈ());
		����ȭ��.add(ȸ������, "ȸ������");

		�α��� = new �α���();
		�α���.getStartButton().addActionListener(new ��ư�̺�Ʈ());
		�α���.getBackButton().addActionListener(new ��ư�̺�Ʈ());
		����ȭ��.add(�α���, "�α���");

		����ȭ�� = new ����ȭ��();
		����ȭ��.add(����ȭ��, "����ȭ��");

		����â = ����ȭ��.getInstance();
		����â.get���ΰ�ħ().addActionListener(new ��ư�̺�Ʈ());
		����â.get����().addActionListener(new ��ư�̺�Ʈ());
		����â.get�α׾ƿ�().addActionListener(new ��ư�̺�Ʈ());
		����â.get�游���().addActionListener(new ��ư�̺�Ʈ());
		����ȭ��.add(����â, "����ȭ��");

		���ȭ�� ���ȭ�� = ȭ��.���ȭ��.getInstance();
		���ȭ��.get���ư���().addActionListener(new ��ư�̺�Ʈ());
		���ȭ��.get�����ϱ�().addActionListener(new ��ư�̺�Ʈ());
		����ȭ��.add(���ȭ��, "���ȭ��");

		���.show(get����ȭ��(), "�޴�");

		�������ޱ�();
		add(get����ȭ��());
		setVisible(true);
	}

	private void �������ޱ�() {
		Thread �泪���� = new Thread(new Runnable() {
			String �������� = "";

			public void run() {
				BufferedReader in;
				System.out.println(socket.getLocalPort());
				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					while (true) {
						if((�������� = in.readLine()) == null) {
							continue;
						}
						String ����[] = ��������.split("/");
						if (����[0].equals("" + socket.getLocalPort())) {
							System.out.println("�ش� yatch���Ͽ��� ��û����.");
							// ���⼭ switch ������ ���䰪 �й��Ű��.
							switch (����[1]) {
							case "�泪����":
							case "�α��μ���":
								���.show(����ȭ��, "����ȭ��");
								break;
							case "�α��ν���":
								JOptionPane.showMessageDialog(����Frame.this, "���̵�/��й�ȣ�� Ȯ���ϼ���.");
								break;
							case "ȸ�������� �Ϸ�Ǿ����ϴ�.":
								JOptionPane.showMessageDialog(null, "ȸ�� ���� ����!");
							case "�α׾ƿ�����" :
								���.show(����ȭ��, "�޴�");
								break;
							case "���������":
								���.show(����ȭ��, "���ȭ��");
								break;
							case "���ΰ�ħ":
								���ΰ�ħ();
								break;
							}
						}
						Thread.sleep(100);
					}
					// while true ��.
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		�泪����.start();
	}

	private void ���ΰ�ħ() {

		Thread reflash = new Thread(new Runnable() {

			public void run() {

				BufferedReader in;
				// 2.������ �̿��Ͽ� ������ �����͸� ������.

				try {
					outprint("���ΰ�ħ");
					System.out.println("������ �α��� ������ ����.");
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					while ((���� = in.readLine()) == null) {
//
//					}
					while (!(���� = in.readLine()).equals("���ΰ�ħ��")) {
						if (����.split("/").length > 1)
							���� = ����.split("/")[1]; // ��Ʈ��ȣ ������ �� ������.
						System.out.println("���ΰ�ħ > ���� : " + ����);

					}
//					if (����.contains("���ΰ�ħ")) {
					// ���ΰ�ħ
					// while���� ���� �����̸� �۽���.
					// �����̸� �p ����.

					// ���ΰ�ħ��
//					}
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});
		reflash.start();
	}

	// �濡 ���� �� �߰��ؾ���..!!!!
//	����ȭ�� = new ����ȭ��();
//	����ī��.add(����ȭ��, "������");
	
	protected void outprint(String str) {
		try {
			PrintWriter pw = new PrintWriter(getSocket().getOutputStream(), true);
			pw.println(socket.getLocalPort() + "/" + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���.show
	public void �޴���() {
		���.show(����ȭ��, "�޴�");
	}

	public void ��������() {
		// ����ȭ�� ����.
		���.show(����ȭ��, "����ȭ��");
	}

	public void ����ȭ������() {
		���.show(����ȭ��, "����ȭ��");
	}

	public void ���ȭ������() {
		���.show(����ȭ��, "���ȭ��");
	}
	// get set

	public static ����ȭ�� get����ȭ��() {
		return ����ȭ��;
	}

	public static ȸ������ getȸ������() {
		return ȸ������;
	}

	public Socket getSocket() {
		return this.socket;
	}

	public static JPanel get����ȭ��() {
		return ����ȭ��;
	}

	public static CardLayout get���() {
		return ���;
	}
}
