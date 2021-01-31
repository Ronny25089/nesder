package com.nesder.vo.resq;

public class AddChannel {

	private int id;

	private String name;

	private String introduction;

	private int fid;

	private int created_account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getCreated_account() {
		return created_account;
	}

	public void setCreated_account(int created_account) {
		this.created_account = created_account;
	}
}