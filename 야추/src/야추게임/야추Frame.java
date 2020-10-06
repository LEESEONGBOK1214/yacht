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

public class ����Frame extends JFrame implements ActionListener {
	private �ֻ���Panel �ֻ����г�;
	private ��������Panel �����г�;
	private ����Panel �����г�;
	
	private ȸ������ ȸ������;
	private �޴� �޴�;
	private �α��� �α���;
	private CardLayout ī��; // ī�� ���̾ƿ��� add�� ���̾ƿ����� �ϳ��� show ����.
	private JPanel ����ī��;
//	private ����Ȯ�� scorecheck;
	private String[] scores;
	private String yourname;
	private ���ȭ�� waitpage;
	String ����;
	Socket socket;
	Connection conn;
	PreparedStatement ptst;
	
	����Frame() {
		super("yacht!");
		// �⺻ ����.
		setSize(700, 700);
		setLocation(1920 / 2 - this.getWidth() / 2, 1080 / 2 - this.getHeight() / 2); // ȭ�� ��� ����
		
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
		ī�� = new CardLayout();
		����ī�� = new JPanel(ī��);
		
		�޴� = new �޴�();
		
		�޴�.get���ӽ���().addActionListener(this);
		�޴�.getȸ������().addActionListener(this);
		����ī��.add(�޴�,"�޴�");
		
		ȸ������ = new ȸ������();
		ȸ������.get����().addActionListener(this);
		ȸ������.get���().addActionListener(this);
		����ī��.add(ȸ������,"ȸ������");
		
		�α��� = new �α���();
		�α���.getStartButton().addActionListener(this);
		�α���.getBackButton().addActionListener(this);
		����ī��.add(�α���,"�α���");
		
		ī��.show(����ī��, "�޴�");
		
		
		add(����ī��);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		switch(e.getActionCommand()) {
		
		case "���ӽ���" :
			ī��.show(����ī��, "�α���");
			break;
			
		case "ȸ������" :
			ī��.show(����ī��, "ȸ������");
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
			ī��.show(����ī��, "�޴�");
			break;
			
//		case "����" :
//			�α���();
//			break;
		}
	}
	

	String loginstatus = null;
	private void �α���() {
		if(!�α���.getIdTextField().getText().equals("")) {
			if(!�α���.getPasswdTextField().getText().equals("")) {
				
				Thread loginthread = new Thread(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						// ������ ���� ä�� �Է¹޴� in
						BufferedReader in;
						// ������ ������� os
						OutputStream os;
						
						try {
							// �����κ��� ����� �� �ֵ��� �����´�.
							os = socket.getOutputStream();
							OutputStreamWriter ost = new OutputStreamWriter(os);
							PrintWriter pw = new PrintWriter(ost,true);
							
							String ���̵� = �α���.getIdTextField().getText();
							String ��й�ȣ = �α���.getPasswdTextField().getText();
							// ������    �α���/ ���̵� / ��й�ȣ ����. 
							pw.println("�α���/"+ ���̵�+"/"+ ��й�ȣ);
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							while((loginstatus=in.readLine()) == null) {
								
							}
							
							if(loginstatus.equals("�α��μ���")) {
								try {
//											Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),320);
									BufferedReader in1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
									os = socket.getOutputStream();
									ost = new OutputStreamWriter(os);
									pw = new PrintWriter(ost,true);
									pw.println(�α���.getIdTextField().getText());
									
									waitpage = new ���ȭ��();
									����ī��.add(waitpage,"���ȭ��");
									ī��.show(����ī��, "���ȭ��");
									System.out.println("�α��μ���");
									Thread thread = new Thread(new Runnable() {
										
										@Override
										public void run() {
											
											try {
												OutputStream os = socket.getOutputStream();
												OutputStreamWriter ost = new OutputStreamWriter(os);
												PrintWriter pw = new PrintWriter(ost,true);
												pw.println("���̵���");
												while((yourname = in1.readLine()) == null) {
													System.out.println(yourname);
												}
												System.out.println("���� �̸� : "+yourname);
												�����г� = new ����Panel(�α���.getIdTextField().getText(),yourname, socket);
												����ī��.add(�����г�,"������");
												ī��.show(����ī��, "������");
												
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									thread.start();
									
								
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
										ī��.show(����ī��, "�޴�");
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
}
