package ����_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//�۽� �������� ����Դϴ�.

// �켱 �ش� Ŭ������ ������� ����ϱ� ���ؼ�  �� "Thread" Ŭ������ ����Ͽ����ϴ�.
public class SendThread extends Thread {
	private Socket m_Socket;

	// �׷��� "run()" �޼ҵ带 �������̵� �Ҽ��� �ֽ��ϴ�.
	// �츮���� �� "run()" �޼ҵ忡 �� �ϵ��� ���α׷��� �ؾ� �մϴ�.
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		try {
			// -----------------------------------------------------------------------------------
			BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter sendWriter = new PrintWriter(m_Socket.getOutputStream());
			String sendString;
			// "System.in" �� ����ϱ� ���ϵ��� ����� ���ؼ� BufferedReader Ŭ������ �����ϰ�.
			// ������ "OutputStream" ���� ����ϱ� ���ϵ��� "PrintWriter" Ŭ������ �����ϰ� �ֽ��ϴ�.
			// ps. ���⼭ InputStreamReader, BufferedReader, PrintWriter Ŭ������
			// �ٸ� ���ǿ��� �� �Ѵٰ� ��. ã�ƺ����� �ؾ���.
			// -----------------------------------------------------------------------------------

			// -----------------------------------------------------------------------------------
			while (true) {
				sendString = tmpbuf.readLine();

				sendWriter.println(sendString);
				sendWriter.flush();
			}
			// ������ �����͸� �����ϴ� while(true) �κ�.
			// while(true)������ ���� ������ ���鼭 �����͸� �����ϰ� �˴ϴ�.
			// �켱 "tmpbuf"�� "System.in"���� �����͸� �޾ƿ��� ��ü�ε�, ���� ���ؼ�
			// Ű���� �Է��� ����ϰ� Ű����κ��� �����͸� ������ �ش� ���ۿ� ���� �ϰ� �˴ϴ�.
			// = Ű���� �Է� > tmpbuf���ۿ� ���� -> readLine���� �޾ƿ� ���ڿ��� ����.
			// �� �Ŀ� "SendWriter"�� �ش� ���ڿ��� �����ϰ� �ȴ�. -> �������� �ش� ���ڿ� ����
			// -----------------------------------------------------------------------------------
			
			// [ 3�ٿ�� ]
			//  1. Thread ��ӹް�, run()�޼ҵ带 ������ �Ѵ�.
			//	2. System.in���� Ű���� �Է��� ���/�Է� �޴´�.
			//	3. Socket�� OutputStream���� �����͸� �����Ѵ�.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSocket(Socket _socket) {
		m_Socket = _socket;
		// �������κ��� "Socket" ��ü�� �ޱ� ���� �޼ҵ�.
	}
}
