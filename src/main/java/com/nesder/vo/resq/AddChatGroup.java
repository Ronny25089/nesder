package com.nesder.vo.resq;

public class AddChatGroup {
	
	private int id;
	
	private int Group_Account_Type;
	
	private String gName;

	private String introduction;

	private String avatorUrl;

	private int created_account;

	private int chat_Type;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroup_Account_Type() {
		return Group_Account_Type;
	}

	public void setGroup_Account_Type(int group_Account_Type) {
		Group_Account_Type = group_Account_Type;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	

	public String getAvatorUrl() {
		return avatorUrl;
	}

	public void setAvatorUrl(String avatorUrl) {
		this.avatorUrl = avatorUrl;
	}

	public int getCreated_account() {
		return created_account;
	}

	public void setCreated_account(int created_account) {
		this.created_account = created_account;
	}

	public int getChat_Type() {
		return chat_Type;
	}

	public void setChat_Type(int chat_Type) {
		this.chat_Type = chat_Type;
	}

	
}