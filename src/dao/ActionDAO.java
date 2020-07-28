package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Action;

//DB上のactionテーブルに対応するDAO
public class ActionDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	// ユーザーIDを指定して、ユーザーのaction情報のListを取得
	// ユーザーIDが存在しない場合はnullを返す
	public List<Action> get(String userId) {
		Action action = null;
		List<Action> actionList = new ArrayList<Action>();

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM action WHERE userid = ? order by create_time desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をactionに格納
			while (rs.next()) {
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
				actionList.add(action);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return actionList;
	}
	
	// actionを指定して、ユーザーaction情報を保存
	// 戻り値:true 成功 , false 失敗
	public boolean save(Action action) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO action " + "( action_id, userid, start_date, finish_date, start_time, finish_time, action_place, action_reason, action_remarks, create_time) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, action.getAction_id());
			pStmt.setString(2, action.getUser_id());
			pStmt.setString(3, action.getStart_date());
			pStmt.setString(4, action.getFinish_date());
			pStmt.setString(5, action.getStart_time());
			pStmt.setString(6, action.getFinish_time());
			pStmt.setString(7, action.getAction_place());
			pStmt.setString(8, action.getAction_reason());
			pStmt.setString(9, action.getAction_remarks());
			pStmt.setString(10, action.getCreate_time());


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
	
	public List<Action> searchAction(String userId,String search) {
		Action action = null;
		List<Action> actionList = new ArrayList<Action>();

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM action WHERE userid = ? AND (action_place LIKE ? )  order by create_time desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			String like = "%";
			pStmt.setString(1, userId);
			pStmt.setString(2, like+search+like);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をactionに格納
			while (rs.next()) {
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
				actionList.add(action);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return actionList;
	}
}