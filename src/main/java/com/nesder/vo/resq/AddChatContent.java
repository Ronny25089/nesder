package com.nesder.vo.resq;

public class AddChatContent {
	
	private int id;
	
	private String content;
	
	private int chat_Group_Id;

	private int created_account;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public int getChat_Group_Id() {
		return chat_Group_Id;
	}

	public void setChat_Group_Id(int chat_Group_Id) {
		this.chat_Group_Id = chat_Group_Id;
	}

	public int getCreated_account() {
		return created_account;
	}

	public void setCreated_account(int created_account) {
		this.created_account = created_account;
	}

	
}