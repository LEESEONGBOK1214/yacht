package 야추메인;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import 야추_클라.로그인;
import 야추_클라.회원가입;
import 화면.방목록화면;

public class 버튼이벤트 implements ActionListener {
	Socket socket = 야추Frame.socket;

	public void actionPerformed(ActionEvent e) {

//		System.out.println(e.getActionCommand());
		// 버튼이 눌리면
		// outprint로 서버에 전송하고
		// 그 값을 switch로 돌려서
		// 해당 값 처리하는 식으로 변경합시다.

		CardLayout 장면 = 야추Frame.get장면();
		JPanel 메인화면 = 야추Frame.get메인화면();

		switch (e.getActionCommand()) {
		case "로그인":
			장면.show(메인화면, "로그인");
			break;
		case "회원가입":
			장면.show(메인화면, "회원가입");
			break;
		case "가입":
			가입();
			break;
		case "취소":
		case "뒤로":
			장면.show(메인화면, "메뉴");
			break;
		case "시작":
			시작();
			break;
		case "방만들기":
			방만들기();
			break;
		case "들어가기":
			break;
		case "새로고침":
			outprint("새로고침");
			break;
		case "로그아웃":
			outprint("로그아웃");
			break;
		case "방목록으로":
			outprint("방나가기");
			break;
		// 테스팅용들
//		case "게임시작":
		case "바로시작":
			장면.show(메인화면, "게임화면");
			break;
		case "방목록보기":
			장면.show(메인화면, "방목록화면");
			break;
		}
	}

	private void 방만들기() {
		String title = JOptionPane.showInputDialog("방 제목을 입력해주세요.");
		if (title != null) {
			outprint("방만들기/" + title);
		}
	}

	private void 시작() {
		String uid = 로그인.getIdTextField().getText();
		String upw = 로그인.getPasswdTextField().getText();
		if (!로그인.getIdTextField().getText().equals("")) {
			if (!로그인.getPasswdTextField().getText().equals("")) {
				outprint("로그인/" + uid + "/" + upw);
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
		}
	}

	private void 가입() {
		if (!회원가입.get아이디받기().getText().equals("")) {
			if (!회원가입.get비밀번호받기().getText().equals("")) {
				if (!회원가입.get이름받기().getText().equals("")) {
					String 아이디 = 회원가입.get아이디받기().getText();
					String 비밀번호 = 회원가입.get비밀번호받기().getText();
					String 이름 = 회원가입.get이름받기().getText();
					outprint("회원가입/" + 아이디 + "/" + 비밀번호 + "/" + 이름);
				} else {
					JOptionPane.showMessageDialog(null, "이름을 확인하세요");
				}
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요");
			}
		} else {
			JOptionPane.showMessageDialog(null, "아이디를 확인하세요");
		}
	}

	protected void outprint(String str) {
		try {
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			pw.println(socket.getLocalPort() + "/" + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
