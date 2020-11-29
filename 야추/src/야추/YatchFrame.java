package ����;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ����.�޴�.login;
import ����.�޴�.menu;
import ����.�޴�.signUp;
import ����.ȭ��.InGame;
import ����.ȭ��.WaitPanel;
import ����.ȭ��.RoomsPanel;

@SuppressWarnings("serial")
public class YatchFrame extends JFrame implements ActionListener, WindowListener {
	private static InGame ����ȭ��;

	private menu �޴�;
	private static signUp ȸ������;
	private static login �α���;
	private static CardLayout ���; // ī�� ���̾ƿ��� add�� ���̾ƿ����� �ϳ��� show ����.
	private static JPanel ����ȭ��;
	private RoomsPanel �����г�;

	static Socket socket;
	Connection conn;
	PreparedStatement ptst;

	String ���� = null;
	int ��ü�� = -1;
	static String ���̵�;

	YatchFrame() {
		super("yacht!");
		// �⺻ ����.
		setSize(720, 700);
//		setLocation(600, 200); // ȭ�� ��� ���� ��ž
		setLocation(470, 100); // ��Ʈ��
		setDefaultCloseOperation(3);// �ݱ� ������ ����.

		// =================================================================================================
		try {
//			172.26.2.227
//			socket = new Socket("39.127.9.97", 8888);
//			System.out.println(InetAddress.getLocalHost().getHostAddress());
			socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8888);
//			System.out.println(socket.getLocalAddress());
		} catch (UnknownHostException e1) {
			System.out.println("���� ���� ����");
			return;
		} catch (IOException e1) {
			System.out.println("���� ���� ����");
			return;
		}
		// =================================================================================================
		��� = new CardLayout();
		����ȭ�� = new JPanel(���);
		�޴� = new menu();

		�޴�.get�α���().addActionListener(new EventListener());
		�޴�.getȸ������().addActionListener(new EventListener());
		����ȭ��.add(�޴�, "�޴�");

		ȸ������ = new signUp();
		ȸ������.get����().addActionListener(new EventListener());
		ȸ������.get���().addActionListener(new EventListener());
		����ȭ��.add(ȸ������, "ȸ������");

		�α��� = new login();
		�α���.getStartButton().addActionListener(new EventListener());
		�α���.getBackButton().addActionListener(new EventListener());
		����ȭ��.add(�α���, "�α���");

		����ȭ�� = new InGame();
		����ȭ��.add(����ȭ��, "����ȭ��");

		�����г� = RoomsPanel.getInstance();
		�����г�.get���ΰ�ħ().addActionListener(new EventListener());
		�����г�.get�α׾ƿ�().addActionListener(new EventListener());
		�����г�.get�游���().addActionListener(new EventListener());
		����ȭ��.add(�����г�, "����ȭ��");

		WaitPanel ���ȭ�� = ����.ȭ��.WaitPanel.getInstance();
		���ȭ��.get���ư���().addActionListener(new EventListener());
		���ȭ��.get�����ϱ�().addActionListener(new EventListener());
		����ȭ��.add(���ȭ��, "���ȭ��");

		���.show(����ȭ��, "�޴�");
		setResizable(false);
		�������ޱ�();
		add(����ȭ��);
		setVisible(true);
		addWindowListener(this);
	}

	private void �������ޱ�() {
		Thread �������ޱ� = new Thread(new Runnable() {
			String �������� = "";

			public void run() {
				BufferedReader in;
				System.out.println(socket.getLocalPort());
				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					while (true) {
						while ((�������� = in.readLine()) == null) {
							System.out.println("in while �������� : " + ��������);
							continue;
						}
						System.out.println("�������� : " + ��������);
						String ����[] = ��������.split("/");
						if (����.length < 2) {
							System.out.println("���� ������ �߸��Ȱ� ����.");
							return;
						}
						if (����[0].equals("broadCast")) {
							// ��� �������� �����ϴ� ��ε�ĳ��Ʈ >
							// �� ���� ���� �ÿ� ������Ʈ ���Ѿ���.
							if (����[1] == "")
								return;
							switch (����[1]) {
							case "�������Ʈ":
								���ϻ��ΰ�ħ();
								break;
							}
						} else if (����[0].equals("" + socket.getLocalPort())) {
							System.out.println("�ش� yatch���Ͽ��� ��û����.");
							// ���⼭ switch ������ ���䰪 �й��Ű��.
							switch (����[1]) {
							case "����������":
								����ȭ��.����������();
								break;
							case "����������":
								����ȭ��.����������();
								break;
							case "�ֻ���������":
								����ȭ��.�ֻ���������(����[2]); // ������ ������.
								break;
							case "����������":
								����ȭ��.get������().������(����[2]);
								break;
							case "���ӽ�����":
								��ü�� = 0;
								���ӽ���(����[2], ����[3], ����[4], ����[5]); // ���� �� ���� ����.
								break;
							case "��������":
								��������(����[2]);
								break;
							case "��������":
								��������("");
								break;
							case "�泪����":
								����.ȭ��.WaitPanel.getInstance().get�����ϱ�().setEnabled(false);
								����.ȭ��.WaitPanel.getInstance().�����̸�����("");
								��������();
								break;
							case "�α��μ���":
								setTitle("yatch - ������ : " + ����[2]);
								// ����ȭ�鿡 �̸�, �·�, ��ŷ ��������
								�����г�.������������(����);
								����ȭ��.get������().set���̸�(����[2]);
								��������();
								break;
							case "�ߺ�����":
								JOptionPane.showMessageDialog(YatchFrame.this, "�̹� �α��εǾ��ֽ��ϴ�.");
								break;
							case "�α��ν���":
								JOptionPane.showMessageDialog(YatchFrame.this, "���̵�/��й�ȣ�� Ȯ���ϼ���.");
								break;
							case "ȸ�������� �Ϸ�Ǿ����ϴ�.":
								JOptionPane.showMessageDialog(null, "ȸ�� ���� ����!");
							case "�α׾ƿ�����":
								���.show(����ȭ��, "�޴�");
								break;
							case "���������":
								���.show(����ȭ��, "���ȭ��");
								break;
							case "���ΰ�ħ":
								���ϻ��ΰ�ħ();
								break;
							case "������":
								������(����);
								repaint();
								break;
							case "����":
								����ȭ��.get������().�����������(����);
								����ȭ��.set��(1);
								�ϼ���(true);
								��ü��++;
								System.out.println("��ü�� : " + ��ü��);
								break;
							case "������":
								��ü��++;
								System.out.println("��ü�� : " + ��ü��);
								if (��ü�� == 24) {
									String ���º� = "false";
									String ���� = "0/";
									String ���� = "1/";
									int �������� = Integer.parseInt(����ȭ��.get������().get��������()[0][1].getText());
									int �������� = Integer.parseInt(����ȭ��.get������().get��������()[1][1].getText());
									if (�������� < ��������) {
										���� = "1/";
										���� = "0/";
									}
									if (�������� == ��������) {
										���º� = "true";
									}
									
									outprint("��������/" + ���� + ���� + ���º�);
								}
								�ϼ���(false);
								break;
							case "����������":
								����ȭ��.get������().����������();
								break;
							case "��������":
								��������();
								��ü�� = -1;
								break;
							case "������":
								if (��ü�� == -1)
									break;
								JOptionPane.showMessageDialog(YatchFrame.this, "��밡 �������ϴ�! �¸�!");
								����.ȭ��.WaitPanel.getInstance().get�����ϱ�().setEnabled(false);
								����.ȭ��.WaitPanel.getInstance().�����̸�����("");
								��������();
								��ü�� = -1;
								break;
							}

						}
						Thread.sleep(100);
					}
					// while true ��.
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}



			private void ������(String[] ����) {
				����.ȭ��.WaitPanel.getInstance().�����̸�����(����[2]);
				���.show(����ȭ��, "���ȭ��");
			}

			private void ��������(String ����̸�) {
				����.ȭ��.WaitPanel.getInstance().�����̸�����(����̸�);
				if (����̸�.equals("")) {
					����.ȭ��.WaitPanel.getInstance().get�����ϱ�().setEnabled(false);
				} else {
					����.ȭ��.WaitPanel.getInstance().get�����ϱ�().setEnabled(true);
				}
			}

		});
		�������ޱ�.start();
	}

	protected void ��������() {
		String ��� = "";
		int ������ = ����ȭ��.get������().get������();
		int ������� = ����ȭ��.get������().get�������();

		if (������ > �������) {
			��� = "�¸�!";
		} else if (������ < �������) {
			��� = "�й�!";
		} else {
			��� = "���º�..";
		}
		JOptionPane.showMessageDialog(YatchFrame.this, "��������!! ��� : " + ���);
		outprint("�泪����");
	}

	protected void ���ӽ���(String �������ϱ�, String ������1, String ������2, String ����) {
		if (�������ϱ�.equals("0")) { // 0�̸� �� ������ ����
			�ϼ���(true);
			����ȭ��.�� = 1;

		} else {
			�ϼ���(false);
		}
		����ȭ��.get������().�����ʱ�ȭ();
		����ȭ��.get������().get��������()[0][0].setText(������1);
		����ȭ��.get������().get��������()[1][0].setText(������2);
		����ȭ��.get������().set��������(Integer.parseInt(����));
		���.show(����ȭ��, "����ȭ��");
	}

	JButton �������ư;

	private void ���ϻ��ΰ�ħ() {
		try {
			ArrayList<String> DB����;
			DB���� = new DB.OracleDB().���ϰ�������();
			Iterator<String> ���� = DB����.iterator();
			int i = 0;
			�����г�.get�����г�().removeAll();

			if (!����.hasNext()) {
				JLabel ����� = new JLabel("���� �����ϴ�.");
				�����.setFont(new Font("Serif", Font.BOLD, 40));
				�����.setBounds(200, 230, 300, 50);
//				�����.setBorder(new LineBorder(Color.pink));
				�����г�.get�����г�().add(�����);
				System.out.println("�����..");
			}
			while (����.hasNext()) {
				String ������ = ����.next();

				System.out.println("���� �׷�����.");
				// ȭ�鿡 �׷��ֱ�.
				JPanel ���г� = new JPanel(null);
				���г�.setBounds(12 + (i % 3) * 230, 10 + 35 * ((int) (i / 3)), 200, 30);
				���г�.setBackground(Color.GREEN);
				���г�.setBorder(new LineBorder(Color.black));
//				���г�.setName("��");

				JLabel ������� = new JLabel(������.split("/")[0]);
				�������.setBounds(0, 0, 50, 30);
				�������.setBorder(new LineBorder(Color.black));

				JLabel ������ = new JLabel(������.split("/")[2]);
				������.setBounds(55, 0, 90, 30);

				�������ư = new JButton();
				�������ư.setIcon(new ImageIcon(getClass().getResource("/images/����.png")));
				�������ư.setBounds(150, 0, 50, 30);
				�������ư.setName(������.split("/")[0]); // ������ ������ �Ѱ� ������ ã�� �����Ű�� ����.
				�������ư.addActionListener(this);
				// �Ұ� text�� setEnable false�� ����.

				System.out.println("Integer.parseInt(������.split(\"/\")[1]) : " + Integer.parseInt(������.split("/")[1]));
				if (Integer.parseInt(������.split("/")[1]) > 0) {
					�������ư.setVisible(false);
				}

				���г�.add(�������);
				���г�.add(������);
				���г�.add(�������ư);
				�����г�.get�����г�().add(���г�);
				i++;
			}

			String �¹��а�� = new DB.OracleDB().��������(���̵�);
			String[] res = �¹��а��.split("/");
			int �� = Integer.parseInt(res[1]);
			int �� = Integer.parseInt(res[2]);
			int �� = Integer.parseInt(res[3]);
			int ranking = Integer.parseInt(res[4]);
			int winrate = (int) ((�� / (�� + �� + �� * 1.0)) * 100);
			�����г�.������������(winrate, ranking);
//			����ȭ��.

			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �濡 ���� �� �߰��ؾ���..!!!!
//	����ȭ�� = new ����ȭ��();
//	����ī��.add(����ȭ��, "������");

	public static void outprint(String str) {
		try {
			PrintWriter pw = new PrintWriter(getSocket().getOutputStream(), true);
			pw.println(socket.getLocalPort() + "/" + str);
		} catch (IOException e) {
			System.out.println("������ ���� �ȵ�����.");
			System.exit(0);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// ���� ��ư ������ ��
		JButton ��ư = (JButton) e.getSource();
		outprint("������/" + ��ư.getName());

	}

	// ���.show
	public void �޴���() {
		���.show(����ȭ��, "�޴�");
	}

	private void ��������() {
		���.show(����ȭ��, "����ȭ��");
		���ϻ��ΰ�ħ();
	}

	public void ����ȭ������() {
		���.show(����ȭ��, "����ȭ��");
	}

	public void ���ȭ������() {
		���.show(����ȭ��, "���ȭ��");
	}
	// get set

	public static InGame get����ȭ��() {
		return ����ȭ��;
	}

	public static signUp getȸ������() {
		RoomsPanel.get����();
		return ȸ������;
	}

	public static Socket getSocket() {
		return socket;
	}

	public static JPanel get����ȭ��() {
		return ����ȭ��;
	}

	public static CardLayout get���() {
		return ���;
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		outprint("â����");
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	private void �ϼ���(boolean ���ð�) {
		����ȭ��.get������().�Ͻ���(���ð�);
		����ȭ��.get������().�Ͻ���(���ð�);
		����ȭ��.get������().get����ȭ����ȯ().setEnabled(false);
		����ȭ��.����������();
	}

}
