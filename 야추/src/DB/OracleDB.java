package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import 야추.서버.방;

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

	public boolean 회원가입(String id, String pw, String name) throws SQLException {
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
		} finally {
			finalClose(conn, pstm, rs);
		}

		return false;

	}

	public String 로그인(String id, String pw) throws SQLException {
		// TODO Auto-generated method stub
		if (checkId(id)) {
			if (checkPw(pw)) {
				System.out.println("들어온 유저가 맞음.");
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
						String 출력문 = "";
						출력문 += rs.getString(1) + "/"; // 유저명
						출력문 += rs.getString(2) + "/"; // 승
						출력문 += rs.getString(3) + "/"; // 무
						출력문 += rs.getString(4) + "/"; // 패
						출력문 += rs.getString(5); // 랭킹
						System.out.println("DB > 로그인성공 > 출력문 : " + 출력문);
						return 출력문;
					} else {
						return null;
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("오류 : " + e.getCause());
				} finally {
					finalClose(conn, pstm, rs);
				}

				return null;

			} else {
				System.out.println("비밀번호 틀림!");
			}
			return null;
		} else {
			System.out.println("아이디 없음!");
			return null;
		}
	}

	public boolean checkId(String id) throws SQLException { // 해당 아이디가 있는지 없는지 확인
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

	public boolean checkPw(String pw) throws SQLException { // 해당 비밀번호가 있는지 없는지 확인
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

	public void 방생성(방 room) throws SQLException {
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
		// 1: 방장 유저 포트 번호
		// 2: 일반 유저 포트 번호
		// 3: 방제목
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, room.get유저들().get(0).getSocket().getPort());
			pstm.setInt(2, 0);
			pstm.setString(3, room.get제목());

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("DB > 방생성 > 성공");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("DB > 방생성 > 실패. 오류 발생!");
		} finally {
			finalClose(conn, pstm, rs);
		}
	}

	public boolean 방입장(int 방장port, int 유저port) throws SQLException {
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
		// 1: 방장 유저 소켓
		// 2: 일반 유저 소켓
		// 3: 방제목
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, 유저port);
			pstm.setInt(2, 방장port);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("DB > 방입장 > 성공");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("DB > 방생성 > 실패. 오류 발생!");
		} finally {
			finalClose(conn, pstm, rs);
		}
		return false;
	}

	public void 방삭제(int port) throws SQLException {
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
		// 1: 방장 유저 소켓
		// 2: 일반 유저 소켓
		// 3: 방제목
		try {
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, port);

			rs = pstm.executeQuery();
			System.out.println("DB > 방삭제 > port : " + port);
			if (rs.next()) {
				System.out.println("DB > 방삭제 > 성공");
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

	public ArrayList<String> 방목록가져오기() throws SQLException {
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
		// 1: 방장 유저 소켓
		// 2: 일반 유저 소켓
		// 3: 방제목

		ArrayList<String> 방목록 = new ArrayList<String>();
		try {
			pstm = conn.prepareStatement(sql);

			rs = pstm.executeQuery();
			while (rs.next()) { // 방이 있을때만 true겠죠? ㅎ-ㅎ
				// u1 socket
				// u2 socket
				// title
				String 방정보 = "";
				방정보 += rs.getInt(1);
				방정보 += ("/" + rs.getInt(2));
				방정보 += ("/" + rs.getString(3));
				방목록.add(방정보);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			finalClose(conn, pstm, rs);
		}
		return 방목록;
	}

	public void 서버종료() throws SQLException {
		Connection conn = null;
		try {
			conn = DBconn.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 서버 종료시엔 모든 방이 사라져야함.
		String sql = "delete from yat_room";
		try {
			pstm = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finalClose(conn, pstm, rs);
		}
	}

	public boolean 방나가기(int 방장port, int 유저port) throws SQLException {
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
		// 1: 방장 유저 소켓
		// 2: 일반 유저 소켓
		// 3: 방제목
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, 0);
			pstm.setInt(2, 방장port);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("DB > 방나가기 > 성공");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("DB > 방생성 > 실패. 오류 발생!");
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
	 * void 메서드명(){ Connection conn = null; try { conn = DBconn.getConnection(); }
	 * catch (Exception e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } PreparedStatement pstm = null; ResultSet rs = null;
	 * String sql=""; }
	 */
}