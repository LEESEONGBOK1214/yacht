package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ����_����.��;
import ����_����.����;

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
		}finally{
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
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
				sql = "select user_name from yat_user where user_id = ?";
				try {
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, id);
					rs = pstm.executeQuery();
					if (rs.next()) {
						return rs.getString(1);
					} else {
						return null;
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("���� : " + e.getCause());
				}finally{
					if(rs!=null)rs.close();
					if(pstm!=null)pstm.close();
					if(conn!=null)conn.close();
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
		}finally{
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
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
		}finally{
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
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
		// 1: ���� ���� ����
		// 2: �Ϲ� ���� ����
		// 3: ������
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, room.get������().get(0).getSocket().getPort());
			pstm.setString(2, room.get�����̸�());
			pstm.setString(3, room.get����());
			
			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("DB > ����� > ����");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB > ����� > ����. ���� �߻�!");
		}finally{
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
		}
	}

	public boolean �����(int port) throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "delete from yat_room where u1_socket= ?";
		// 1: ���� ���� ����
		// 2: �Ϲ� ���� ����
		// 3: ������
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, port);
			
			rs = pstm.executeQuery();
			if (rs.next()) {
				System.out.println("DB > ����� > ����");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
		}
		return false;
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
		String sql = "select u1_socket, title from yat_room";
		// 1: ���� ���� ����
		// 2: �Ϲ� ���� ����
		// 3: ������
		
		ArrayList<String> ����  = new ArrayList<String>();
		try {
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			while(rs.next()) { // ���� �������� true����? ��-��
				// u1 socket
				// u2 socket
				// title
				String �������� = "";
				�������� += rs.getInt(1);
				�������� += rs.getString(2);
				����.add(��������);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
		}
		return ����;
	}

	/*
	 * void �޼����(){ Connection conn = null; try { conn = DBconn.getConnection(); }
	 * catch (Exception e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } PreparedStatement pstm = null; ResultSet rs = null;
	 * String sql=""; }
	 */
}