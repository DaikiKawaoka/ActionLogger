package model;

//�Ǘ����[�U�Ǘ��N���X
public class ManagementAdomin {
	private String adomin_id; // FK (user_id ������)
	private String management_group_id;  // FK

	public String getAdomin_id() {
		return adomin_id;
	}
	public void setAdomin_id(String adomin_id) {
		this.adomin_id = adomin_id;
	}
	public String getManagement_group_id() {
		return management_group_id;
	}
	public void setManagement_group_id(String management_group_id) {
		this.management_group_id = management_group_id;
	}
	
	
}
