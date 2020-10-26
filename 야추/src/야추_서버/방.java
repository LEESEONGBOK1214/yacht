package ����_����;

import java.io.IOException;
import java.util.ArrayList;

public class �� extends Thread {
	ArrayList<����> userList;
	���� roomOwner; // ����
	String roomName; // �� �̸�

	public ��() { // �ƹ��� ���� ���� ������ ��
		userList = new ArrayList<����>();
	}

	public ��(���� _user) {
		// ������ ���� ���鶧
		userList = new ArrayList<����>();
		userList.add(_user); // ������ �߰���Ų ��
		this.roomOwner = _user; // ������ ������ �����.
	}

	public ��(ArrayList<����> _userList) {
		// ���� ����Ʈ�� ���� ������
		this.userList = _userList; // ��������Ʈ ����
		this.roomOwner = userList.get(0);
		// ù��° ������ �������� ����
	}

	public void EnterRoom(���� _user) {
		userList.add(_user);
	}

	public void ExitRoom(���� _user) { 
		userList.remove(_user); 
		if (userList.size() < 1) {
	    //��� �ο��� �� ���� �����ٸ� 
			�����.RemoveRoom(this); 
			// �� ���� �����Ѵ�. 
			return; 
		} 
		if (userList.size() < 2) {
			// �濡 ���� �ο��� 1�� ���϶�
			this.roomOwner = userList.get(0);
			// ����Ʈ�� ù��° ������ ������ �ȴ�. return;
		}
	}

	// ���� ����
	@SuppressWarnings("unused")
	public void Broadcast(byte[] data) {
		for (���� user : userList) {
			// �濡 ���� ������ ����ŭ �ݺ�
			// �� �������� �����͸� �����ϴ� �޼��� ȣ��~
			// ex) user.SendData(data);
			try {
				user.m_socket.getOutputStream().write(data);
				// �̷������� ����Ʈ�迭�� ������. //
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void SetOwner(���� _user) {
		this.roomOwner = _user; // Ư�� ����ڸ� �������� �����Ѵ�.
	}

	public void SetRoomName(String _name) {
		// �� �̸��� ����
		this.roomName = _name;
	}

	public ���� GetUserByNickName(String _nickName) {
		// �г����� ���ؼ� �濡 ���� ������ ������
		for (���� user : userList) {
			if (user.get�̸�().equals(_nickName)) {
				return user;
				// ������ ã�Ҵٸ�
			}
		}
		return null;
		// ã�� ������ ���ٸ�
	}

	public String GetRoomName() {
		// �� �̸��� ������
		return roomName;
	}

	public int GetUserSize() { //
		// ������ ���� ����
		return userList.size();
	}

	public ���� GetOwner() {
		// ������ ����
		return roomOwner;
	}
}
