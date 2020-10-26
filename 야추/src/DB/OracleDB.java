package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleDB {
//	Connection conn;
//	static PreparedStatement pstm;
//	static ResultSet rs;
	static String sql;
	DBConnection DBconn;

	public OracleDB() {
		DBconn = DBConnection.getInstance();
//		conn = DBconn.getConnection();
//		pstm = null;
//		rs = null;
	}

	public boolean 회원가입(String id, String pw, String name) {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
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
//				System.out.println("회원가입이 완료되었습니다.");
				return true;
			} else {
//				System.out.println("회원가입에 실패했습니다. 다시 시도해 주세요.");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("오류 : " + e.getCause());
		}
		return false;

	}

	public boolean 로그인(String id, String pw) {
		// TODO Auto-generated method stub
		if (checkId(id)) {
			if (checkPw(pw)) {
				System.out.println("들어온 유저가 맞음.");
				return true;

			} else {
				System.out.println("비밀번호 틀림!");
			}
			return false;
		} else {
			System.out.println("아이디 없음!");
			return false;
		}
	}

	public boolean checkId(String id) { // 해당 아이디가 있는지 없는지 확인
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
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
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
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

	public ArrayList<String> selectAll() { // 해당 비밀번호가 있는지 없는지 확인
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from books";
		ArrayList<String> 반환값 = new ArrayList<String>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 반환값;
	}// end of method
	
	
	/*
	 void 메서드명(){
	 	Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql="";
	 }
	 */
}