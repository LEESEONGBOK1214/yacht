package ����.����;

import java.util.ArrayList;

public class room {
	String ����;
	ArrayList<user> ������;
	boolean ����;
	
	public room(user _owner, String title) {
		������ = new ArrayList<user>();
		������.add(_owner);
		this.���� = title;
		this.���� = false;
	}

	public room() {
	}

	public String ����(user ����) {
		if(������.size()<2) {
			������.add(����);
			return "�� ��������";
		}else {
			return "���� ��á���ϴ�.";
		}
		
	}
	
	
	// get set
	public String get�����̸�() {
		return ������.get(0).get�̸�();
	}
	
	public ArrayList<user> get������() {
		return ������;
	}

	public boolean is�����() {
		return ����;
	}

	public void set�����(boolean ��) {
		this.���� = ��;
	}

	public String get����() {
		return ����;
	}

	public void set����(String ����) {
		this.���� = ����;
	}
}
