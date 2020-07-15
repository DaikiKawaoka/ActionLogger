package model;

public class Action {
	private int action_id; //主キー
	private int user_id; //FK
	private String action_date;
	private String start_time;
	private String finish_time;
	private String action_place;
	private String action_reason; //理由
	private String action_remark; //備考
}
