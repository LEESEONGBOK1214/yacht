package 야추_서버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//송신 스레드의 모습입니다.

// 우선 해당 클래스를 스레드로 사용하기 위해서  ↓ "Thread" 클래스를 상속하였습니다.
public class SendThread extends Thread {
	private Socket m_Socket;

	// 그러면 "run()" 메소드를 오버라이딩 할수가 있습니다.
	// 우리들은 이 "run()" 메소드에 할 일들을 프로그래밍 해야 합니다.
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		try {
			// -----------------------------------------------------------------------------------
			BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter sendWriter = new PrintWriter(m_Socket.getOutputStream());
			String sendString;
			// "System.in" 을 사용하기 편하도록 만들기 위해서 BufferedReader 클래스로 변경하고.
			// 소켓의 "OutputStream" 값을 사용하기 편하도록 "PrintWriter" 클래스로 변경하고 있습니다.
			// ps. 여기서 InputStreamReader, BufferedReader, PrintWriter 클래스는
			// 다른 강의에서 더 한다고 함. 찾아보도록 해야함.
			// -----------------------------------------------------------------------------------

			// -----------------------------------------------------------------------------------
			while (true) {
				sendString = tmpbuf.readLine();

				sendWriter.println(sendString);
				sendWriter.flush();
			}
			// 실제로 데이터를 전송하는 while(true) 부분.
			// while(true)임으로 무한 루프를 돌면서 데이터를 전송하게 됩니다.
			// 우선 "tmpbuf"는 "System.in"에서 데이터를 받아오는 객체인데, 쉽게 말해서
			// 키보드 입력을 대기하고 키보드로부터 데이터를 받으면 해당 버퍼에 전달 하게 됩니다.
			// = 키보드 입력 > tmpbuf버퍼에 씌움 -> readLine으로 받아와 문자열로 저장.
			// 그 후에 "SendWriter"에 해당 문자열을 전송하게 된다. -> 소켓으로 해당 문자열 전송
			// -----------------------------------------------------------------------------------
			
			// [ 3줄요약 ]
			//  1. Thread 상속받고, run()메소드를 재정의 한다.
			//	2. System.in으로 키보드 입력을 대기/입력 받는다.
			//	3. Socket의 OutputStream으로 데이터를 전송한다.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSocket(Socket _socket) {
		m_Socket = _socket;
		// 메인으로부터 "Socket" 객체를 받기 위한 메소드.
	}
}
