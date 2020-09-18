package 야추_서버;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//public class MainServer {
//	// 1. 서버 통신 모듈 작성하기.
//	// 서버에서 소켓을 열어 클라이언트를 대기하는 프로그래밍.
//	// 사용 클래스 : "ServerSocket", "Socket"
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		try {
//			ServerSocket s_socket = new ServerSocket(8888);
//			// serversocket 소켓명 = new serversocket(포트번호);
//
//			// --------------------------------------------------------
//			Socket c_socket = s_socket.accept();
//			// accept : 클라이언트가 들어오는것을 대기하는 역할.
//			// => 클라이언트가 포트번호(8888) 포트로 연결을 시도한다면,
//			// accept는 대기를 풀고, 클라이언트와 연결시키는
//			// "Socket" 클래스를 생성하여 반환하게 됩니다.
//			// 그래서 "accept" 메소드로 부터 받은 "c_socket"이
//			// 바로 클라이언트랑 1:1 연결된 소켓입니다.
//			// => c_socket으로 클라이언트랑 통신이 가능.
//			// --------------------------------------------------------
//
//			// --------------------------------------------------------
//			OutputStream output_data = c_socket.getOutputStream();
//			// c_socket.getOutputStream()
//			// 클라이언트와 연결된 소켓으로부터 "OUtputStream" 객체를 가져옵니다.
//			// 이 "OutputStream" 객체에 서버에서 전달할 메세지를 작성하여
//			// 전달하기만 하면 됩니다.
//			// --------------------------------------------------------
//
//			// --------------------------------------------------------
//			String sendDataString = "Welcome to My Server";
//			output_data.write(sendDataString.getBytes());
//			// 우리가 전달할 "Welcome to My Server"라는 메세지를 "write" 메소드를
//			// 로 전달하는 모습입니다.
//			// --------------------------------------------------------
//			s_socket.close();
//			c_socket.close();
//
//			// 요약 :
//			// 1. ServerSocket은 클라이언트 연결요청이 있을때까지 대기.
//			// 2. 클라이언트가 연결 요청이 있다면 Socket을 생성하여 연결시킴
//			// 3. 연결된 Socket으로 데이터를 Stream(스트림) 형태로 주고 받는다.
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}

public class 방파는사람 {
	public static void main(String[] args) {
		System.out.println("메인 서버입니다.");
		try {
			ServerSocket s_socket = new ServerSocket(8888);
			
			Socket c_socket = s_socket.accept();
			
			ReceiveThread rec_thread = new ReceiveThread();
			rec_thread.setSocket(c_socket);
			
			SendThread send_thread = new SendThread();
			send_thread.setSocket(c_socket);
			
			rec_thread.start();
			send_thread.start();



		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
