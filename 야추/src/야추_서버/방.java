package ����_����;

import java.util.ArrayList;

public class �� {
	String ����;
	

	ArrayList<����> ������;
	boolean ����;
	
	public ��(���� _owner, String title) {
		������ = new ArrayList<����>();
		������.add(_owner);
		this.���� = title;
		this.���� = false;
	}
	
	public String ����(���� ����) {
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
	
	public ArrayList<����> get������() {
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
