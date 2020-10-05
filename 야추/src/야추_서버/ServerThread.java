package 야추_서버;

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

//이거 쓰는거 아님.. 일단 냅둠.
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
					System.out.println("입력 : " + inline);
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
		case "회원가입":
			try {
				out = new PrintWriter(m_socket.getOutputStream(), true);
				out.println("회원가입에 성공했습니다.");
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
 * = new Socket(); socket.connect(isa); socket.setSoTimeout(10000); // 10초
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