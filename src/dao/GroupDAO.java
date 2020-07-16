package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import model.Action;
import model.ManagementAdomin;
import model.ManagementGroup;

//DB上のuserテーブルに対応するDAO
public class GroupDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public ManagementGroup checkAndGetGroup(String groupId) {
		ManagementGroup group = null;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM managementgroup WHERE MANAGEMENT_GROUP_ID  = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, groupId);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をuserに格納
			while (rs.next()) {
				group = new ManagementGroup();
				group.setManagement_group_id(rs.getString("management_group_id"));
				group.setGroup_name(rs.getString("group_name"));
				group.setCreate_time(rs.getString("create_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return group;
	}

	// ユーザーIDを指定して、ユーザー情報を取得
	// ユーザーIDが存在しない場合はnullを返す
	public ManagementGroup getShowGroup(String groupId) {
		ManagementGroup group = null;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT u.userid,u.name,a.START_TIME ,a.FINISH_TIME ,a.ACTION_PLACE ,a.ACTION_REASON ,a.ACTION_REMARKS ,a.START_DATE ,a.FINISH_DATE ,a.CREATE_TIME  "
					+ "FROM  MANAGEMENTADOMIN  ma ,ACTION  a, BELONGS  b,USER  u ,MANAGEMENTGROUP mg "
					+ "where a.userid=u.userid AND mg.MANAGEMENT_GROUP_ID =b.MANAGEMENT_GROUP_ID  AND ma.MANAGEMENT_GROUP_ID =mg.MANAGEMENT_GROUP_ID AND u.userid<>ma.adomin_id AND b.MANAGEMENT_GROUP_ID = '?'order by a.create_time desc;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, groupId);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をuserに格納
			while (rs.next()) {
				Action action = new Action();
				User user = new User();
				group.setManagement_group_id(rs.getString("management_group_id"));
				group.setGroup_name(rs.getString("group_name"));
				group.setCreate_time(rs.getString("create_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return group;
	}

	// ユーザーを指定して、ユーザー情報を保存
	// 戻り値:true 成功 , false 失敗
	public boolean save(ManagementGroup managementGroup, String userid) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO MANAGEMENTGROUP  " + "( management_group_id, group_name, create_time ) "
					+ "VALUES ( ?, ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, managementGroup.getManagement_group_id());
			pStmt.setString(2, managementGroup.getGroup_name());
			pStmt.setString(3, managementGroup.getCreate_time());

			// INSERT文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql2 = "INSERT INTO MANAGEMENTADOMIN  " + "( management_group_id, adomin_id ) " + "VALUES ( ?, ? )";
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			pStmt2.setString(1, managementGroup.getManagement_group_id());
			pStmt2.setString(2, userid);

			// INSERT文を実行
			result = pStmt2.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}