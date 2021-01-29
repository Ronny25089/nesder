package com.nesder.vo.resq;

public class AddForum {

	private int created_account;

	private String mName;
	
	private String introduction;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreated_account() {
		return created_account;
	}

	public void setCreated_account(int created_account) {
		this.created_account = created_account;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}