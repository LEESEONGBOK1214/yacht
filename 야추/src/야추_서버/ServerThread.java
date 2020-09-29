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
	ArrayList<Room> RoomList = new ArrayList<Room>();

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
