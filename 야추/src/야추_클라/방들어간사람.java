package 야추_클라;

import java.io.IOException;
import java.net.Socket;

//public class MainClient {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		// 2.클라이언트 통신모듈 작성.
//		// 클라이언트는 서버와 다르게 아주 간단하게 작성이 가능
//		// "Socket"객체를 생성하여 서버와 연결 시도,
//		// 연결되면 스트림 형태로 데이터를 주고 받기만 하면 된다.
//		
//		try {
//			// ---------------------------------------------------
//			Socket c_socket = new Socket("192.168.0.3", 8888);
//			// "Socket" 객체를 생성하여 연결을 시도하는 부분.
//			// 연결할 IP주소와 Port 번호를 매개변수로 넘겨주어야 해당 주소로 연결을 시도.
//			// 연결할 주소와, port번호를 맞게 작성하자.
//			// ---------------------------------------------------
//			
//			
//			// ---------------------------------------------------
//			InputStream input_data = c_socket.getInputStream();
//			
//			byte[] receiveBuffer = new byte[100];
//			input_data.read(receiveBuffer);
//			
//			System.out.println(new String(receiveBuffer));
//			// 만약 연결이 완료 되었다면 데이터를 스트림 형태로 받아와서 콘솔창에 출력.
//			// ---------------------------------------------------
//			
//			// ---------------------------------------------------
//			// 요약 :
//			// 	1. "Socket"을 사용하여, IP, Port 주소로 연결 요청.
//			//	2. 연결이 되면 스트림 형태로 데이터를 주고 받는다.
//			// ---------------------------------------------------
//		} catch (IOException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//		// 서버를 먼저 실행시킴 => 클라이언트를 대기.
//		// 클라이언트를 실행시키면 서버와 연결을 시도함.
//		// 클라이언트가 실행되자 서버는 반응. => 연결이 됨.
//		// 연결되면 서버는 Welcome to My Server 문자열을 클라이언트에게 전달, 종료.
//		// 클라이언트도 서버에서 받은 문자열을 출력 후 종료.
//	}
//
//}


// 2장 메인 클라이언트 
public class 방들어간사람 {

	public static void main(String[] args) {
		System.out.println("클라이언트입니다.");
		try {
			// ---------------------------------------------------
			Socket c_socket = new Socket("172.30.1.8", 8888);
			
			ReceiveThread rec_thread = new ReceiveThread();
			rec_thread.setSocket(c_socket);
			
			SendThread send_thread = new SendThread();
			send_thread.setSocket(c_socket);
			
			send_thread.start();
			rec_thread.start();
			
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}