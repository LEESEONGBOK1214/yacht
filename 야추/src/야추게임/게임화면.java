package ���߰���;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ����ȭ�� extends JPanel implements ActionListener, MouseListener{
	������ ������;
	������ ������;
	�ֻ��� �ֻ�����[] = new �ֻ���[5];
	
	int ��;
	String ���� = "";
	boolean ����; // true�� �� ����.
	CardLayout ���;
	public ����ȭ��() {

		// �� �Ӽ� ����
		
		��� = new CardLayout();
		setLayout(���);
		
		// ��ü �ʱ�ȭ
		for(int i =0;i<�ֻ�����.length;i++) {
			�ֻ�����[i] = new �ֻ���();
			�ֻ�����[i].addMouseListener(this);
		}
		������ = new ������(�ֻ�����);
		������ = new ������();
		
		// ��ü ����
		
		
		
		
		// ����
		
		add(������, "�ֻ���������");
		add(������, "��������");
		
		���.show(this, "�ֻ���������");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void �ֻ�������(){
		// ��� �� �����ϰ� ���� �׷��ֱ� ����.
		
		System.out.print("�ֻ������� >");
		// ������=true�� �ֻ��� ����ŭ �׸���
		������.������.removeAll();
		
		System.out.print("for in >");
		for(int i=0;i<5;i++) { // �ֻ����� ������ 5��.
			������.������.repaint();
			������.�ֻ�����.repaint();
			
			if(�ֻ�����[i].������) {
				System.out.println("\n�ֻ���"+i +"��°�� ������!");
				
				�ֻ�����[i].setBounds(82*i, 0, 70, 70);
				������.������.add(�ֻ�����[i]);
			}else {
				System.out.println("\n�ֻ���"+ i +"��°�� �������!");
				
				�ֻ�����[i].setBounds((i * 102) + 10, 0, 70, 70);
				������.�ֻ�����.add(�ֻ�����[i]);
			}
		}
		System.out.print("for out >");
	}
	
	void �����Ǽ���() {
		
	}
	
	void �����Ǽ���() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//���콺 Ŭ�� ��
		System.out.print("��ȭMouseClicked > ");
//		System.out.println(e.getSource().getClass());
		if(e.getSource().getClass() == �ֻ���.class) {
			// �����Ѱ� �ֻ��� �̸�!!!
			�ֻ��� �����ֻ��� = (�ֻ���)e.getSource();
			�����ֻ���.������ = !�����ֻ���.������;
			System.out.print("if�� >");
			�ֻ�������();
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
