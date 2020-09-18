package 야추_서버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread{
	private Socket m_Socket;
	
	// -------------------------------------------------------
	public void run() {
		super.run();
		
		try {
			BufferedReader tmpbuf = new BufferedReader
					(new InputStreamReader(m_Socket.getInputStream()));
			
			String receiveString;
			
			while(true) {
				receiveString = tmpbuf.readLine();
				
				if(receiveString == null) {
					System.out.println("상대방 연결이 끊겼습니다.");
					break;
				}else {
					System.out.println("상대방 : " + receiveString);
				}
				
			}
			
			tmpbuf.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	// "Thread"를 상속받아 "run" 메소드를 재정의하는것은 동일합니다
	// 그리고 소켓의 "InputStream" 값을 사용하기 편하게 하기 위해서
	// "BufferedReader"로 변경해주고 있습니다.
	// 실제로 데이터를 받아와서 뿌려주는 부분은 "while"내부 부분입니다.
	// 여기도 또한 무한루프로 데이터를 확인하고 뿌려줍니다.
	// 전송받은 데이터는 소켓의 "InputStream"에 쌓이게 되고,
	// 우리들은 이것은 "readLine"메소드로 가져오게 됩니다.
	// 받은 데이터가 "null"이면 소켓연결이 끊어진것이기 때문에
	// break로 무한루프를 빠져나가게 됩니다.
	// "null"이 아니라면 데이터를 콘솔창에 뿌려주게 됩니다.
	
	// [ 3줄요약 ]
	//	1. Thread를 상속받고, run()메소드를 재정의한다.
	//	2. Socket의 InputStream으로 데이터를 대기/수신한다.
	//	3. 수신한 데이터가 "null"일시 스레드 종료, 아니면 콘솔창에 뿌려준다.
	// ------------------------------------------------------
	public void setSocket(Socket _socket) {
		m_Socket = _socket;
		// 메인으로부터 "Socket" 객체를 받기 위한 메소드.
	}
}
