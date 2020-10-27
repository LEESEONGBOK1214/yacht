package 야추_서버;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class 게임서버 {
	public static ArrayList<유저> 유저목록 = new ArrayList<유저>();
	public static ArrayList<PrintWriter> m_OutputList;

	public static void main(String[] args) {
		System.out.println("게임 서버가 활성화 됐습니다.");
		try {
			ServerSocket s_socket = new ServerSocket(8888); // 포트번호
			m_OutputList = new ArrayList<PrintWriter>();

			while (true) {
				// 연결동안 계속 돌면서 데이터 확인

				Socket c_socket = s_socket.accept();
				// 연결된 창의 포트를 가져옴
				유저 c_thread = new 유저(c_socket);
				// 가져온 포트로 유저 생성.
				c_thread.setM_socket(c_socket);

				유저목록.add(c_thread);
				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));

				c_thread.start();
			}
		} catch (IOException e) {
			System.out.println("오류");
			e.printStackTrace();
		}

		System.out.println("게임 서버가 비활성화 됐습니다.");
	}

	public PrintWriter serach유저(Socket _socket) {
		for(PrintWriter pw : m_OutputList) {
			if()
		}
		return null;
	}
}
