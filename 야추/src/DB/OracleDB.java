package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ����.����.��;

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

	public boolean ȸ������(String id, String pw, String name) throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		sql = "insert into yat_user(user_id, user_pw, user_name) values(?, ?, ?)";
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
		} finally {
			finalClose(conn, pstm, rs);
		}

		return false;

	}

	public String �α���(String id, String pw) throws SQLException {
		// TODO Auto-generated method stub
		if (checkId(id)) {
			if (checkPw(pw)) {
				System.out.println("���� ������ ����.");
				Connection conn = null;
				try {
					conn = DBconn.getConnection();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PreparedStatement pstm = null;
				ResultSet rs = null;
				sql = "select user_name, win, draw, lose, B.RANK\r\n"
						+ "from yat_user A, (select user_id, ROW_NUMBER() OVER (ORDER BY win DESC) AS RANK from yat_user) B\r\n"
						+ "where A.user_id = B.user_id and A.user_id = ?";
				try {
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, id);
					rs = pstm.executeQuery();
					if (rs.next()) {
						String ��¹� = "";
						��¹� += rs.getString(1) + "/"; // ������
						��¹� += rs.getString(2) + "/"; // ��
						��¹� += rs.getString(3) + "/"; // ��
						��¹� += rs.getString(4) + "/"; // ��
						��¹� += rs.getString(5); // ��ŷ
						System.out.println("DB > �α��μ��� > ��¹� : " + ��¹�);
						return ��¹�;
					} else {
						return null;
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("���� : " + e.getCause());
				} finally {
					finalClose(conn, pstm, rs);
				}

				return null;

			} else {
				System.out.println("��й�ȣ Ʋ��!");
			}
			return null;
		} else {
			System.out.println("���̵� ����!");
			return null;
		}
	}

	public boolean checkId(String id) throws SQLException { // �ش� ���̵� �ִ��� ������ Ȯ��
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
		} finally {
			finalClose(conn, pstm, rs);
		}

		return false;
	}

	public boolean checkPw(String pw) throws SQLException { // �ش� ��й�ȣ�� �ִ��� ������ Ȯ��
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
		} finally {
			finalClose(conn, pstm, rs);
		}

		return false;
	}

	public void �����(�� room) throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "insert into yat_room values(?, ?, ?)";
		// 1: ���� ���� ��Ʈ ��ȣ
		// 2: �Ϲ� ���� ��Ʈ ��ȣ
		// 3: ������
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, room.get������().get(0).getSocket().getPort());
			pstm.setInt(2, 0);
			pstm.setString(3, room.get����());

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("DB > ����� > ����");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("DB > ����� > ����. ���� �߻�!");
		} finally {
			finalClose(conn, pstm, rs);
		}
	}

	public boolean ������(int ����port, int ����port) throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "update yat_room set u2_port = ? where u1_port = ?";
		// 1: ���� ���� ����
		// 2: �Ϲ� ���� ����
		// 3: ������
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ����port);
			pstm.setInt(2, ����port);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("DB > ������ > ����");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("DB > ����� > ����. ���� �߻�!");
		} finally {
			finalClose(conn, pstm, rs);
		}
		return false;
	}

	public void �����(int port) throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "delete from yat_room where u1_port= ?";
		// 1: ���� ���� ����
		// 2: �Ϲ� ���� ����
		// 3: ������
		try {
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, port);

			rs = pstm.executeQuery();
			System.out.println("DB > ����� > port : " + port);
			if (rs.next()) {
				System.out.println("DB > ����� > ����");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.commit();
			conn.setAutoCommit(true);
			finalClose(conn, pstm, rs);
		}
	}

	public ArrayList<String> ���ϰ�������() throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from yat_room";
		// 1: ���� ���� ����
		// 2: �Ϲ� ���� ����
		// 3: ������

		ArrayList<String> ���� = new ArrayList<String>();
		try {
			pstm = conn.prepareStatement(sql);

			rs = pstm.executeQuery();
			while (rs.next()) { // ���� �������� true����? ��-��
				// u1 socket
				// u2 socket
				// title
				String ������ = "";
				������ += rs.getInt(1);
				������ += ("/" + rs.getInt(2));
				������ += ("/" + rs.getString(3));
				����.add(������);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			finalClose(conn, pstm, rs);
		}
		return ����;
	}

	public void ��������() throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ���� ����ÿ� ��� ���� ���������.
		String sql = "delete from yat_room";
		try {
			pstm = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finalClose(conn, pstm, rs);
		}
	}

	public boolean �泪����(int ����port, int ����port) throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "update yat_room set u2_port = ? where u1_port = ?";
		// 1: ���� ���� ����
		// 2: �Ϲ� ���� ����
		// 3: ������
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, 0);
			pstm.setInt(2, ����port);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("DB > �泪���� > ����");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("DB > ����� > ����. ���� �߻�!");
		} finally {
			finalClose(conn, pstm, rs);
		}
		return false;
	}

	private void finalClose(Connection conn, PreparedStatement pstm, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (pstm != null)
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/*
	 * void �޼����(){ Connection conn = null; try { conn = DBconn.getConnection(); }
	 * catch (Exception e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } PreparedStatement pstm = null; ResultSet rs = null;
	 * String sql=""; }
	 */
}