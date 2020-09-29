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

	public String ȸ������(String id, String pw, String name) {
		sql = "insert into yat_user values('?, ?, ?')";
		//									id,pw,name
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, name);

			rs = pstm.executeQuery();
			
			if(rs.next()) {
				System.out.println("ȸ�����Կ� ���� �ϼ̽��ϴ�.");
				return "ȸ�����Կ� ���� �ϼ̽��ϴ�.";
			} else {
				System.out.println("ȸ�����Կ� �����߽��ϴ�. �ٽ� �õ��� �ּ���.");
				return "ȸ�����Կ� �����߽��ϴ�. �ٽ� �õ��� �ּ���.";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "�߸������";

	}

}