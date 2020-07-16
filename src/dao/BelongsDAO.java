package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.User;
import model.ManagementAdomin;
import model.ManagementGroup;
import java.util.ArrayList;
import java.util.List;

//DB上のuserテーブルに対応するDAO
public class BelongsDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	// ユーザーIDを指定して、ユーザー情報を取得
		// ユーザーIDが存在しない場合はnullを返す
		public List<ManagementGroup> get(String userId) {
			ManagementGroup group = null;
			List<ManagementGroup> belongsList = new ArrayList<ManagementGroup>();

			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// SELECT文の準備
				String sql = "SELECT b.management_group_id, mg.group_name, mg.create_time FROM MANAGEMENTGROUP mg , BELONGS b WHERE mg.management_group_id = b.MANAGEMENT_GROUP_ID  AND b.userid = ? order by mg.create_time desc";

				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, userId);

				// SELECTを実行
				ResultSet rs = pStmt.executeQuery();

				// SELECT文の結果をuserに格納
				while (rs.next()) {
					group = new ManagementGroup();
					group.setManagement_group_id(rs.getString("management_group_id"));
					group.setGroup_name(rs.getString("group_name"));
					group.setCreate_time(rs.getString("create_time"));
					belongsList.add(group);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return belongsList;
		}

	// 所属テーブル作成
	// 戻り値:true 成功 , false 失敗
	public boolean save(ManagementGroup managementGroup, String userid) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO BELONGS  " + "( management_group_id, userid ) "
					+ "VALUES ( ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, managementGroup.getManagement_group_id());
			pStmt.setString(2, userid);

			// INSERT文を実行
			int result = pStmt.executeUpdate();
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