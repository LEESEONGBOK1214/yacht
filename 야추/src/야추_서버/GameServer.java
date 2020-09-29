package 야추_서버;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {

	public static ArrayList<PrintWriter> m_OutputList;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("야추서버");
		m_OutputList = new ArrayList<PrintWriter>();
		// 접속을 모아둘 어레이리스트.
		try {
			ServerSocket s_socket = new ServerSocket(8888); // 포트번호
			
			while(true) {
				//연결동안 계속 돌면서 데이터 확인
				
				Socket c_socket = s_socket.accept();
				ServerThread c_thread = new ServerThread();
				c_thread.setSocket(c_socket);
				
				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));
				
				c_thread.start();
			}
		} catch (IOException e) {
			System.out.println("오류");
			e.printStackTrace();
		}
	}

}
