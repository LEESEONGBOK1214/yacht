package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDB {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	String sql;

	OracleDB() {
		conn = DBConnection.getConnection();
		pstm = null;
		rs = null;
	}

	public String 회원가입(String id, String pw, String name) {
		sql = "insert into yat_user values('?, ?, ?')";
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

}