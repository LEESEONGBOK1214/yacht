package ����_����;
//package ����_����;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class ��ƴ� extends Thread {
//	���� user_A; // ����
//	���� user_B; // ����
//	String roomName; // �� �̸�
//
//	public ��ƴ�() { // �ƹ��� ���� ���� ������ ��
//		userList = new ArrayList<����>();
//	}
//
//	public ��ƴ�(���� _user) {
//		// ������ ���� ���鶧
//		userList = new ArrayList<����>();
//		userList.add(_user); // ������ �߰���Ų ��
//		this.user_A = _user; // ������ ������ �����.
//	}
//
//
//	public void EnterRoom(���� _user) {
//		user_B = _user;
//	}
//
//	public void ExitRoom(���� _user) { 
//		_user = null;
//		if (user_A == null && user_B == null) {
//	    //��� �ο��� �� ���� �����ٸ� 
//			�����.RemoveRoom(this); 
//			// �� ���� �����Ѵ�. 
//			return; 
//		} 
//		if (userList.size() < 2) {
//			// �濡 ���� �ο��� 1�� ���϶�
//			this.user_A = userList.get(0);
//			// ����Ʈ�� ù��° ������ ������ �ȴ�. return;
//		}
//	}
//
//	// ���� ����
//	@SuppressWarnings("unused")
//	public void Broadcast(byte[] data) {
//		for (���� user : userList) {
//			// �濡 ���� ������ ����ŭ �ݺ�
//			// �� �������� �����͸� �����ϴ� �޼��� ȣ��~
//			// ex) user.SendData(data);
//			try {
//				user.getM_socket().getOutputStream().write(data);
//				// �̷������� ����Ʈ�迭�� ������. //
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void SetOwner(���� _user) {
//		this.user_A = _user; // Ư�� ����ڸ� �������� �����Ѵ�.
//	}
//
//	public void SetRoomName(String _name) {
//		// �� �̸��� ����
//		this.roomName = _name;
//	}
//
//	public String GetRoomName() {
//		// �� �̸��� ������
//		return roomName;
//	}
//
//	public int GetUserSize() { //
//		// ������ ���� ����
//		return userList.size();
//	}
//
//	public ���� GetOwner() {
//		// ������ ����
//		return user_A;
//	}
//}
