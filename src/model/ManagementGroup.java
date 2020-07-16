package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ManagementGroup {
	private String management_group_id;
	private String group_name;
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
	
	public String getManagement_group_id() {
		return management_group_id;
	}
	public void setManagement_group_id(String management_group_id) {
		this.management_group_id = management_group_id;
	}

	// management_group_idがNULLの時にランダムの18桁をセットする
		public void setManagement_group_id() {
			if (this.management_group_id == null) {
				String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
				StringBuilder salt = new StringBuilder();
				Random rnd = new Random();
				while (salt.length() < 18) { // length of the random string.
					int index = (int) (rnd.nextFloat() * SALTCHARS.length());
					salt.append(SALTCHARS.charAt(index));
				}
				String saltStr = salt.toString();
				this.management_group_id = saltStr;
			}
		}

	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	
}
