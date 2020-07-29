package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

public class Action implements Serializable{
	private String action_id; // ��L�[
	private String user_id; // FK
	private String start_date;
	private String finish_date;
	private String start_time;
	private String finish_time;
	private String action_place;
	private String action_reason; // ���R
	private String action_remarks; // ���l
	private String create_time;

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public void setCreate_time() {
		if (this.create_time == null) {
			// 現在日時を取得
			Date date1 = new Date();
			// 表示形式を指定
			SimpleDateFormat sdformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fdate1 = sdformat1.format(date1);
			this.create_time = fdate1;
		}
	}

	public String getAction_id() {
		return action_id;
	}

	// action_idがNULLの時にランダムの18桁をセットする
	public void setAction_id() {
		if (this.action_id == null) {
			String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			StringBuilder salt = new StringBuilder();
			Random rnd = new Random();
			while (salt.length() < 18) { // length of the random string.
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			String saltStr = salt.toString();
			this.action_id = saltStr;
		}
	}
	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getFinish_date() {
		return finish_date;
	}

	public void setFinish_date(String finish_date) {
		this.finish_date = finish_date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(String finish_time) {
		this.finish_time = finish_time;
	}

	public String getAction_place() {
		return action_place;
	}

	public void setAction_place(String action_place) {
		this.action_place = action_place;
	}

	public String getAction_reason() {
		return action_reason;
	}

	public void setAction_reason(String action_reason) {
		this.action_reason = action_reason;
	}

	public String getAction_remarks() {
		return action_remarks;
	}

	public void setAction_remarks(String action_remark) {
		this.action_remarks = action_remark;
	}

}
