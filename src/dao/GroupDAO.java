package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.User;
import model.Action;
import model.ManagementAdomin;
import model.ManagementGroup;
import model.GroupShowModel;

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


	public List<GroupShowModel> getGroupShow(String groupId) {
		GroupShowModel groupShow = null;
		List<GroupShowModel> groupShowList = new ArrayList<GroupShowModel>();
		Action action = null;
		User user = null;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT u.userid,u.name,a.action_id,a.START_TIME ,a.FINISH_TIME ,a.ACTION_PLACE ,a.ACTION_REASON ,a.ACTION_REMARKS ,a.START_DATE ,a.FINISH_DATE ,a.CREATE_TIME  "
					+ "FROM ACTION  a, BELONGS  b,USER  u "
					+ "where a.userid=u.userid AND b.MANAGEMENT_GROUP_ID = ? AND b.userid = u.userid order by a.create_time desc;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, groupId);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をuserに格納
			while (rs.next()) {
				//user情報格納
				user = new User();
				user.setName(rs.getString("name"));
				user.setName(rs.getString("userid"));
				//action情報格納
				action = new Action();
				action.setAction_id(rs.getString("action_id"));
				action.setUser_id(rs.getString("userid"));
				action.setStart_date(rs.getString("start_date"));
				action.setStart_date(rs.getString("finish_date"));
				action.setStart_time(rs.getString("start_time"));
				action.setFinish_time(rs.getString("finish_time"));
				action.setAction_place(rs.getString("action_place"));
				action.setAction_reason(rs.getString("action_reason"));
				action.setAction_remarks(rs.getString("action_remarks"));
				action.setCreate_time(rs.getString("create_time"));
				//GroupShowに情報格納
				groupShow = new GroupShowModel();
				groupShow.setAction(action);
				groupShow.setUser(user);
				//groupShowをリストに入れる
				groupShowList.add(groupShow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return groupShowList;
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