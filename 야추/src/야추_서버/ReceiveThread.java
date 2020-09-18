package ����_����;

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
					System.out.println("���� ������ ������ϴ�.");
					break;
				}else {
					System.out.println("���� : " + receiveString);
				}
				
			}
			
			tmpbuf.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	// "Thread"�� ��ӹ޾� "run" �޼ҵ带 �������ϴ°��� �����մϴ�
	// �׸��� ������ "InputStream" ���� ����ϱ� ���ϰ� �ϱ� ���ؼ�
	// "BufferedReader"�� �������ְ� �ֽ��ϴ�.
	// ������ �����͸� �޾ƿͼ� �ѷ��ִ� �κ��� "while"���� �κ��Դϴ�.
	// ���⵵ ���� ���ѷ����� �����͸� Ȯ���ϰ� �ѷ��ݴϴ�.
	// ���۹��� �����ʹ� ������ "InputStream"�� ���̰� �ǰ�,
	// �츮���� �̰��� "readLine"�޼ҵ�� �������� �˴ϴ�.
	// ���� �����Ͱ� "null"�̸� ���Ͽ����� ���������̱� ������
	// break�� ���ѷ����� ���������� �˴ϴ�.
	// "null"�� �ƴ϶�� �����͸� �ܼ�â�� �ѷ��ְ� �˴ϴ�.
	
	// [ 3�ٿ�� ]
	//	1. Thread�� ��ӹް�, run()�޼ҵ带 �������Ѵ�.
	//	2. Socket�� InputStream���� �����͸� ���/�����Ѵ�.
	//	3. ������ �����Ͱ� "null"�Ͻ� ������ ����, �ƴϸ� �ܼ�â�� �ѷ��ش�.
	// ------------------------------------------------------
	public void setSocket(Socket _socket) {
		m_Socket = _socket;
		// �������κ��� "Socket" ��ü�� �ޱ� ���� �޼ҵ�.
	}
}
