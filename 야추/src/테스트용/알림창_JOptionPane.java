package 테스트용;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class 알림창_JOptionPane {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// System.out.printf("%d %f %c %s", 1, 1.0, 1.0, 'c', "asdf");
		System.out.printf("%d\n", 1);
		System.out.printf("%f\n", 1.0);
		new Ex01();
	}
}

class Ex01 extends JFrame {
	Ex01() {
		super("알림창");
		JOptionPane 알림창 = new JOptionPane();
		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(3);
		
		
		// showMessageDialog
		// - Component parentComponent
		// 메시지창 다이얼로그가 어떤 Frame에서 나타나게 될 것인지를
		// 정해주는 변수. null 값이거나 설정해준 값에 Frame이 없다면
		// 기본값 Frame(default Frame)이 지정됩니다.
		// 쉽게 null 로 둬도 될 것 같네요.
		
		
		//기본 알림창은 null, 메세지만 입력.
		// 다른 경고(Warning), 오류(Error), 질문(Question)
		알림창.showMessageDialog(null, "알림창입니다.");

	}

}