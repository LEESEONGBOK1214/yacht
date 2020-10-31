package ���߸���;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ����_����.���Ӽ���;
import ����_����.����;
import ����_Ŭ��.�α���;
import ����_Ŭ��.�޴�;
import ����_Ŭ��.ȸ������;
import ȭ��.����ȭ��;
import ȭ��.���ȭ��;
import ȭ��.����ȭ��;

public class ����Frame extends JFrame implements ActionListener {
	private static ����ȭ�� ����ȭ��;

	private �޴� �޴�;
	private static ȸ������ ȸ������;
	private static �α��� �α���;
	private static CardLayout ���; // ī�� ���̾ƿ��� add�� ���̾ƿ����� �ϳ��� show ����.
	private static JPanel ����ȭ��;
	private static ����ȭ�� ����ȭ��;

	Socket socket;
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// =================================================================================================
		��� = new CardLayout();
		����ȭ�� = new JPanel(���);
		�޴� = new �޴�();

		�޴�.get�α���().addActionListener(this);
		�޴�.getȸ������().addActionListener(this);
		�޴�.get�ٷν���().addActionListener(this);
		�޴�.get���Ϻ���().addActionListener(this);
		����ȭ��.add(�޴�, "�޴�");

		ȸ������ = new ȸ������();
		ȸ������.get����().addActionListener(this);
		ȸ������.get���().addActionListener(this);
		����ȭ��.add(ȸ������, "ȸ������");

		�α��� = new �α���();
		�α���.getStartButton().addActionListener(this);
		�α���.getBackButton().addActionListener(this);
		����ȭ��.add(�α���, "�α���");

		����ȭ�� = new ����ȭ��();
		����ȭ��.add(����ȭ��, "����ȭ��");

		����ȭ�� = ȭ��.����ȭ��.getInstance();
		����ȭ��.get���ΰ�ħ().addActionListener(this);
		����ȭ��.get����().addActionListener(this);
		����ȭ��.get�α׾ƿ�().addActionListener(this);
		����ȭ��.get�游���().addActionListener(this);
		����ȭ��.add(����ȭ��, "����ȭ��");

		���ȭ�� ���ȭ�� = ȭ��.���ȭ��.getInstance();
		���ȭ��.get���ư���().addActionListener(this);
		���ȭ��.get�����ϱ�().addActionListener(this);
		����ȭ��.add(���ȭ��, "���ȭ��");

		���.show(get����ȭ��(), "�޴�");

		add(get����ȭ��());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "�α���":
			���.show(get����ȭ��(), "�α���");
			break;
		case "ȸ������":
			���.show(get����ȭ��(), "ȸ������");
			break;
		case "����":
			ȸ������();
			break;
		case "���":
		case "�ڷ�":
			���.show(get����ȭ��(), "�޴�");
			break;
		case "����":
			�α���();
			break;
		case "�游���":
//			�����();
			�游���();
			break;
		case "����":
			break;
		case "���ΰ�ħ":
			���ΰ�ħ();
			break;
		case "�α׾ƿ�":
			�α׾ƿ�();
			break;
		case "��������":
			�泪����();
			break;
		// �׽��ÿ��
//		case "���ӽ���":
		case "�ٷν���":
			���.show(get����ȭ��(), "����ȭ��");
			break;
		case "���Ϻ���":
			���.show(get����ȭ��(), "����ȭ��");
			break;
		}
	}

	private void �泪����() {
		System.out.println("����Frame > �泪����() > ");
		Thread �泪���� = new Thread(new Runnable() {

			public void run() {
				BufferedReader in;
				// 2.������ �̿��Ͽ� ������ �����͸� ������.
				OutputStream os;

				try {
					os = socket.getOutputStream();
					OutputStreamWriter ost = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(ost, true);
					pw.println(socket.getLocalPort() + "/�泪����");
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while ((���� = in.readLine()) == null) {

					}

					if (����.equals(socket.getLocalPort() + "�泪����")) {

						���.show(����ȭ��, "����ȭ��");

					} else {
						System.out.println("�泪���� ����");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		�泪����.start();
		
	}

	private void ���ΰ�ħ() {
		����ȭ�� ���ȭ�� = ȭ��.����ȭ��.getInstance();
		���ȭ��.��ϻ��ΰ�ħ();
	}

	private void �α���() {
		if (!�α���.getIdTextField().getText().equals("")) {
			if (!�α���.getPasswdTextField().getText().equals("")) {
				System.out.println("����Frame > �α���() > ");
				Thread loginthread = new Thread(new Runnable() {

					public void run() {
						// TODO Auto-generated method stub
						BufferedReader in;
						// 2.������ �̿��Ͽ� ������ �����͸� ������.
						OutputStream os;

						try {
							os = socket.getOutputStream();
							OutputStreamWriter ost = new OutputStreamWriter(os);
							PrintWriter pw = new PrintWriter(ost, true);
							String uid = �α���.getIdTextField().getText();
							String upw = �α���.getPasswdTextField().getText();
							pw.println(socket.getLocalPort() + "/�α���/" + uid + "/" + upw);
							System.out.println("������ �α��� ������ ����.");
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							while ((���� = in.readLine()) == null) {

							}
							if (����.equals(socket.getLocalPort() + "�α��μ���")) {
								���.show(����ȭ��, "����ȭ��");
								System.out.println("�α��μ���");

							} else {
								System.out.println("�α��ν���");
								JOptionPane.showMessageDialog(����Frame.this, "���̵�/��й�ȣ�� Ȯ���ϼ���.");
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				loginthread.start();
			} else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���.");
		}
	}// end of �α��� Method

	public void ȸ������() {

		����_Ŭ��.ȸ������ ȸ������ = ����Frame.getȸ������();
		if (!ȸ������.get���̵�ޱ�().getText().equals("")) {
			if (!ȸ������.get��й�ȣ�ޱ�().getText().equals("")) {
				if (!ȸ������.get�̸��ޱ�().getText().equals("")) {
					try {
						OutputStream os = this.socket.getOutputStream();
						OutputStreamWriter ost = new OutputStreamWriter(os);
						PrintWriter pw = new PrintWriter(ost, true);
						String ���̵� = ȸ������.get���̵�ޱ�().getText();
						String ��й�ȣ = ȸ������.get��й�ȣ�ޱ�().getText();
						String �̸� = ȸ������.get�̸��ޱ�().getText();
//						System.out.println(pw);
						pw.println(socket.getLocalPort() + "/ȸ������/" + ���̵� + "/" + ��й�ȣ + "/" + �̸�);
						BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
						Thread thread = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {

									while ((���� = in.readLine()) == null) {

									}
//									System.out.println("���� : " + ����);
									System.out.println("socket.getLocalPort() : " + socket.getLocalPort());
									if (����.equals(socket.getLocalPort() + "ȸ�������� �Ϸ�Ǿ����ϴ�.")) {
										JOptionPane.showMessageDialog(null, "ȸ�� ���� ����!");
										���.show(����ȭ��, "�޴�");
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

				} else {
					JOptionPane.showMessageDialog(null, "�̸��� Ȯ���ϼ���");
				}
			} else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ���ϼ���");
			}
		} else {
			JOptionPane.showMessageDialog(null, "���̵� Ȯ���ϼ���");
		}
	}// end of ȸ������ Method

	public void �游���() {
		System.out.print("�游��� > ");
		try {

			Socket socket = this.socket;
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ost = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(ost, true);

			System.out.print("������ ��û : ");
			pw.println(socket.getLocalPort() + "/�游���");
			System.out.println(socket.getLocalPort() + "/�游���");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					String ���� = "";
					try {

						while ((���� = in.readLine()) == null) {

						}
						System.out.println("���� : " + ����);

						if (����.equals(socket.getLocalPort() + "���������")) {
//							System.out.println("����� �ؾ��ϴµ�..?");
							���ȭ������();
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

	// �濡 ���� �� �߰��ؾ���..!!!!
//	����ȭ�� = new ����ȭ��();
//	����ī��.add(����ȭ��, "������");
	public void �α׾ƿ�() {
		// �α��� �� �ְ� �������� �α׾ƿ� �޼��� ������ �޴�â���� ����.
//		System.out.print("�α׾ƿ� > ");
		try {
			Socket socket = this.socket;
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ost = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(ost, true);

//			System.out.print("������ ��û : ");
			pw.println(socket.getLocalPort() + "/�α׾ƿ�");
			System.out.println("�α׾ƿ�/" + socket.getLocalPort());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String ���� = "";
					try {

						while ((���� = in.readLine()) == null) {

						}
						if (����.equals(socket.getLocalPort() + "�α׾ƿ�����")) {
							�޴���();
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

	public JPanel get����ȭ��() {
		return ����ȭ��;
	}
}
