package 야추_서버;

import java.io.InputStream;
import java.net.Socket;

public class Room extends Thread {
	private Socket socket;
	private int RoomId;
	private User A;
	private User B;
	Room(Socket _socket) {
		this.socket = _socket;
	}
	Room(User A, User B) {
		this.A = A;
		this.B = B;
	}

	public void run() {
		// TODO Auto-generated method stub
		super.run();
		InputStream is;

	}
}
