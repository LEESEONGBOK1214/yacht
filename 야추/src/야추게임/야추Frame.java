package ���߰���;

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

import ����_����.����ȭ��;
import ����_����.����;
import ����_Ŭ��.�α���;
import ����_Ŭ��.�޴�;
import ����_Ŭ��.ȸ������;

public class ����Frame extends JFrame implements ActionListener {
	private static ����ȭ�� ����ȭ��;
	
	private ȸ������ ȸ������;
	private �޴� �޴�;
	private �α��� �α���;
	private static CardLayout ���; // ī�� ���̾ƿ��� add�� ���̾ƿ����� �ϳ��� show ����.
	private static JPanel ����ȭ��;
//	private ����Ȯ�� scorecheck;
	private String[] scores;
	private String yourname;
	private static ����ȭ�� ����ȭ��;
	String ����;

	static Socket socket;
	Connection conn;
	PreparedStatement ptst;
	
	����Frame() {
		super("yacht!");
		// �⺻ ����.
		setSize(720, 700);
		setLocation(400, 50); // ȭ�� ��� ����

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
//		System.out.println("socket ����");
		��� = new CardLayout();
		����ȭ�� = new JPanel(���);
		
		�޴� = new �޴�();
		
		�޴�.get���ӽ���().addActionListener(this);
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
		
		����ȭ�� = new ����ȭ��(null);
		����ȭ��.add(����ȭ��, "����ȭ��");

		����ȭ�� = new ����ȭ��();
		����ȭ��.add(����ȭ��, "���ȭ��");

		���.show(����ȭ��, "�޴�");
		
		
		add(����ȭ��);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		switch(e.getActionCommand()) {
		case "���ӽ���" :
			���.show(����ȭ��, "�α���");
			
			break;
			
		case "ȸ������" :
			���.show(����ȭ��, "ȸ������");
			break;
			
//		case "�ʱ�ȭ" :
//			System.out.println("�ʱ�ȭ����?");
//			�����ȸ();
//			break;
			
		case "����":
			ȸ������();
			break;
			
		case "���" :
		case "�ڷ�" :
			���.show(����ȭ��, "�޴�");
			break;
			
		case "����":
			�α���();
			break;

		// �׽��ÿ��
		case "�ٷν���":

			���.show(����ȭ��, "����ȭ��");
			break;
		case "���Ϻ���":

			���.show(����ȭ��, "���ȭ��");
			break;
		}
	}
	
	// �濡 ���� �� �߰��ؾ���..!!!!
//	����ȭ�� = new ����ȭ��();
//	����ī��.add(����ȭ��, "������");
	public static void �޴���() {
//		System.out.println("�������� �����!");
		���.show(����ȭ��, "�޴�");
	}

	public static void ���ȭ������() {
		// ���ȭ�� ����.
		���.show(����ȭ��, "���ȭ��");
	}

	public static void ����ȭ������(���� ������) {
		// ����ȭ�� ����.
		// ���Ͽ��� ������ �߰��ϰ� �ű�� �̵����Ѿ���.
		���.show(����ȭ��, "����ȭ��");
	}

	String loginstatus = null;
	private void �α���() {
		if(!�α���.getIdTextField().getText().equals("")) {
			if(!�α���.getPasswdTextField().getText().equals("")) {
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
							���� �α������� = new ����(socket);
							pw.println("�α���/"+�α���.getIdTextField().getText()+"/"+�α���.getPasswdTextField().getText());
							System.out.println("������ �α��� ������ ����.");
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							while((loginstatus=in.readLine()) == null) {
								
							}
							System.out.println("loginstatus : " + loginstatus);
							if (loginstatus.equals("�α��μ���")) {
								try {
//											Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),320);
									BufferedReader in1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
									os = socket.getOutputStream();
									ost = new OutputStreamWriter(os);
									pw = new PrintWriter(ost,true);
									pw.println(�α���.getIdTextField().getText());
									
//
//									System.out.println("����ī��, \"���ȭ��\"�� ���˵�...");


									���.show(����ȭ��, "���ȭ��");
									System.out.println("�α��μ���");
//									Thread thread = new Thread(new Runnable() {
//										
//										@Override
//										public void run() {
//											
//											try {
//												OutputStream os = socket.getOutputStream();
//												OutputStreamWriter ost = new OutputStreamWriter(os);
//												PrintWriter pw = new PrintWriter(ost,true);
//												pw.println("���̵���");
//												while((yourname = in1.readLine()) == null) {
//													System.out.println(yourname);
//												}
//												System.out.println("���� �̸� : "+yourname);
//
//												ī��.show(����ī��, "������");
//												
//											} catch (IOException e) {
//												// TODO Auto-generated catch block
//												e.printStackTrace();
//											}
//										}
//									});
//									thread.start();
									
								
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
//										yourname=br.readLine();
//										socket.close();
							}else {
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
			}else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���.");
		}
	}// end of �α��� Method

	private void ȸ������() {
		if (!ȸ������.get���̵�ޱ�().getText().equals("")) {
			if (!ȸ������.get��й�ȣ�ޱ�().getText().equals("")) {
				if (!ȸ������.get�̸��ޱ�().getText().equals("")) {
					try {
						OutputStream os = socket.getOutputStream();
						OutputStreamWriter ost = new OutputStreamWriter(os);
						PrintWriter pw = new PrintWriter(ost, true);
						String ���̵� = ȸ������.get���̵�ޱ�().getText();
						String ��й�ȣ = ȸ������.get��й�ȣ�ޱ�().getText();
						String �̸� = ȸ������.get�̸��ޱ�().getText();
						pw.println("ȸ������/" + ���̵� + "/" + ��й�ȣ + "/" + �̸�);
						System.out.println("ȸ������ pw.println ��.");
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						Thread thread = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {

									while ((���� = in.readLine()) == null) {

									}
									JOptionPane.showMessageDialog(null, ����);
									System.out.println("���� : " + ����);
									if (����.equals("ȸ�������� �Ϸ�Ǿ����ϴ�.")) {
										���.show(����ȭ��, "�޴�");
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						thread.start();

//						if(rs != null && rs.next()) {
//							JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���̵��Դϴ�.");
//						}else {
//							String sql = "insert into ������ (id,passwd,name) values ('"+makeInfo.get���̵�ޱ�().getText()+"','"
//									+makeInfo.get��й�ȣ�ޱ�().getText()+"','"+makeInfo.get�̸��ޱ�().getText()+"')";
//							try {
//								stmt.executeUpdate(sql);
//								JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
//								card.show(mainCard, "�޴�");
//							} catch (SQLException e1) {
//								e1.printStackTrace();
//							}
//							System.out.println("����");
//						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					catch (NullPointerException e) {
//						System.out.println("����Frame() -> ȸ������() -> �̸��޴� ������ null �߻�.");
//					}

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

	public static ����ȭ�� get����ȭ��() {
		return ����ȭ��;
	}

	public static Socket getSocket() {
		return socket;
	}
}
