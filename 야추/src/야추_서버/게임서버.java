package 야추_서버;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class 게임서버 {
	static ArrayList<유저> 유저목록 = new ArrayList<유저>();

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("서버가 시작됐습니다.");
			System.out.println("서버의 포트는 " + serverSocket.getLocalPort());
			Socket socket = null;
			while ((socket = serverSocket.accept()) != null) {
				boolean flag = true;
				for (유저 value : 유저목록) {
					if (socket.getPort() == value.getSocket().getPort()) {
						// 같은 유저가 있으면... 안되니까.
						flag = false;
						// 플래그를 걸어 종료 시킴.
						break;
					}
				}
				// 같은 유저가 있을때 안들어감.
				if (flag) {
					유저 client = new 유저(socket);
					client.start();
					유저목록.add(client);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
