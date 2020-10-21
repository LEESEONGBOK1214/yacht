package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleDB {
	static Connection conn;
	static PreparedStatement pstm;
	static ResultSet rs;
	static String sql;

	public OracleDB() {
		conn = DBConnection.getConnection();
		pstm = null;
		rs = null;
	}

	public boolean ȸ������(String id, String pw, String name) {
		sql = "insert into yat_user values(?, ?, ?)";
		System.out.println("id : " + id + "," + "pw : " + pw + "," + "name : " + name);
		// id,pw,name
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, name);

			rs = pstm.executeQuery();

			if (rs.next()) {
//				System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
				return true;
			} else {
//				System.out.println("ȸ�����Կ� �����߽��ϴ�. �ٽ� �õ��� �ּ���.");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���� : " + e.getCause());
		}
		return false;

	}

	public boolean �α���(String id, String pw) {
		// TODO Auto-generated method stub
		if (checkId(id)) {
			if (checkPw(pw)) {
				System.out.println("���� ������ ����.");
				return true;

			} else
				return false;
		} else
			return false;
	}

	public boolean checkId(String id) { // �ش� ���̵� �ִ��� ������ Ȯ��
		sql = "select user_id from yat_user";
//		System.out.println();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String nowId = rs.getString(1);
				if (nowId.equals(id)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean checkPw(String pw) { // �ش� ��й�ȣ�� �ִ��� ������ Ȯ��
		sql = "select user_pw from yat_user";

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String nowPw = rs.getString(1);
				if (nowPw.equals(pw)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<String> selectAll() { // �ش� ��й�ȣ�� �ִ��� ������ Ȯ��
		String sql = "select * from books";
		ArrayList<String> ��ȯ�� = new ArrayList<String>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ��ȯ��;
	}// end of method

}