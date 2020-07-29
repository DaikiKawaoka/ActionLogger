package model;

import java.io.Serializable;

public class Belongs implements Serializable{
	private String management_group_id; //FK
	private String user_id; //FK
	
	public String getManagement_group_id() {
		return management_group_id;
	}
	public void setManagement_group_id(String management_group_id) {
		this.management_group_id = management_group_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
