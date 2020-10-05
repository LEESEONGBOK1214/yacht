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

	public static String 회원가입(String id, String pw, String name) {
		sql = "insert into yat_user values(?, ?, ?)";
		System.out.println("id : " + id + "," + "pw : " + pw + "," + "name : " + name);
		//									id,pw,name
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, name);

			rs = pstm.executeQuery();
			
			if(rs.next()) {
				System.out.println("회원가입에 성공 하셨습니다.");
				return "회원가입에 성공 하셨습니다.";
			} else {
				System.out.println("회원가입에 실패했습니다. 다시 시도해 주세요.");
				return "회원가입에 실패했습니다. 다시 시도해 주세요.";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "잘못된출력";

	}

	public boolean 로그인(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return false;
	}

}