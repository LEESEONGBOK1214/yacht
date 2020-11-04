package 미사용클래스들;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

	public static ArrayList<PrintWriter> m_OutputList;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("서버가 시작됐습니다.");
		m_OutputList = new ArrayList<PrintWriter>();
		// ArrayList로 m_OutputList 객체를 생성하고 할당하게됨.
		// 이 객체의 역할은 서버로 접속하는 클라이언트의 "Output"을 모아둔 배열.
		// 클라이언트가 접속하게 되면, 클라이언트의 "OutputStream"만
		// 이 객체에 모아두게 됩니다.
		// 그 코드는 밑에있는 	m_OutputList.add 부분.
		try {
			ServerSocket s_socket = new ServerSocket(8888);
			// 서버 소켓, 포트번호 8888번호로 엶.
			
			while(true)
			{
				Socket c_socket = s_socket.accept();
//				ClientManagerThread c_thread = new ClientManagerThread();
//				c_thread.setSocket(c_socket);
//				// 서버소켓에서 클라이언트가 감지되면 "Socket"클래스
//				// 즉, 클라이언트 소켓을 생성하여 "ClientManagerThread"에 담아서
//				// 해당 스레드를 실행하게 함. -> c_thread.start();
//				
//				
//				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));
//				// 이 부분에서 OutputStream만을 모아두는 명령어.
//				
//				
//				c_thread.start();
				
				
				// ClientManagerThread 파일로.
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}