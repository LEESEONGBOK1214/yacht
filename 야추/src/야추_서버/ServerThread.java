package ����_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServerThread extends Thread {
	private Socket m_socket;
	private String m_ID;
	ArrayList<GameRoom> RoomList = new ArrayList<GameRoom>();

//�̰� ���°� �ƴ�.. �ϴ� ����.
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		InputStream is;
		try {
			while(true) {
				is = m_socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				
				String inline;
				while ((inline = br.readLine()) != null) {
					System.out.println("�Է� : " + inline);
					Process(inline);
				}
				inline = null;
				System.out.println("Run inline : " + inline);
				m_socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Process(String inline) {
		// TODO Auto-generated method stub
		String[] command = inline.split("/");
		ResultSet rs;
		PrintWriter out;

		switch (command[0]) {
		case "ȸ������":
			try {
				out = new PrintWriter(m_socket.getOutputStream(), true);
				out.println("ȸ�����Կ� �����߽��ϴ�.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public void setSocket(Socket _socket) {
		m_socket = _socket;
	}
}


/*
 * InetSocketAddress isa; Socket socket;
 * 
 * static String url = "jdbc:oracle:thin:@localhost:1521:xe"; static String id =
 * "system"; static String passwd = "oracle"; static Connection conn; static
 * Statement stmt;
 * 
 * public void run() { try { isa = new
 * InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 8888); socket
 * = new Socket(); socket.connect(isa); socket.setSoTimeout(10000); // 10��
 * 
 * OutputStream os = socket.getOutputStream(); InputStream is =
 * socket.getInputStream(); InputStreamReader isr = new InputStreamReader(is);
 * BufferedReader br = new BufferedReader(isr);
 * 
 * String inline; while ((inline = br.readLine()) != null) { for (int i = 0; i <
 * 10; i++) { System.out.println(i); } return; }
 * 
 * } catch (IOException e) { e.printStackTrace();
 * 
 * } }
 */