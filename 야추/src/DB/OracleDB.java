package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public boolean 회원가입(String id, String pw, String name) {
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
				System.out.println("회원가입이 완료되었습니다.");
				return true;
			} else {
				System.out.println("회원가입에 실패했습니다. 다시 시도해 주세요.");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean 로그인(String id, String pw) {
		// TODO Auto-generated method stub
		if (checkId(id)) {
			if (checkPw(pw)) {
				System.out.println("들어온 유저가 맞음.");
				return true;

			} else
				return false;
		} else
			return false;
	}

	public boolean checkId(String id) { // 해당 아이디가 있는지 없는지 확인
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

	public boolean checkPw(String pw) { // 해당 비밀번호가 있는지 없는지 확인
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

	public boolean checkName(String name) { // 해당 비밀번호가 있는지 없는지 확인
		sql = "select user_name from yat_user";

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String nowName = rs.getString(1);
				if (nowName.equals(name)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}