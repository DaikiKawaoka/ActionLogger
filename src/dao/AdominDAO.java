package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Action;
import model.ManagementGroup;

//DB上のactionテーブルに対応するDAO
public class AdominDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	// ユーザーIDを指定して、ユーザーの管理グループのListを取得
		// ユーザーIDが存在しない場合はnullを返す
		public List<ManagementGroup> get(String userId) {
			ManagementGroup group = null;
			List<ManagementGroup> adominGroupList = new ArrayList<ManagementGroup>();

			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// SELECT文の準備
				String sql = "SELECT ma.management_group_id,mg.group_name,mg.create_time FROM MANAGEMENTGROUP mg , MANAGEMENTADOMIN ma WHERE mg.management_group_id = ma.MANAGEMENT_GROUP_ID  AND ma.adomin_id = ? order by mg.create_time desc";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, userId);

				// SELECTを実行
				ResultSet rs = pStmt.executeQuery();

				// SELECT文の結果をグループに格納
				while (rs.next()) {
					group = new ManagementGroup();
					group.setManagement_group_id(rs.getString("management_group_id"));
					group.setGroup_name(rs.getString("Group_name"));
					group.setCreate_time(rs.getString("create_time"));
					adominGroupList.add(group);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return adominGroupList;
		}

	// ユーザーIDを指定して、ユーザーの管理グループのListを取得
	// ユーザーIDが存在しない場合はnullを返す
	public Boolean getAdominGroupBoolean(String groupId,String userId) {

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT count(*) FROM MANAGEMENTADOMIN where Adomin_id = ? AND MANAGEMENT_GROUP_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,userId);
			pStmt.setString(2,groupId);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			if(count == 0) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}