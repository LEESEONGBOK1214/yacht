package ����_����;

import java.util.ArrayList;

public class �� {
	ArrayList<����> ������;
	boolean ����;
	
	public ��(���� _owner) {
		������ = new ArrayList<����>();
		������.add(_owner);
		���� = false;
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

}
