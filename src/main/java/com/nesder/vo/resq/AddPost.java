package com.nesder.vo.resq;

public class AddPost {

	private Integer id;
	
	private String title;
	
	private Integer marks;
	
	private String content;
	
	private Integer browse;
	
	private Boolean enable_edit;
	
	private Integer channel_id;
	
	private Integer created_account;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBrowse() {
		return browse;
	}

	public void setBrowse(Integer browse) {
		this.browse = browse;
	}

	public Boolean getEnable_edit() {
		return enable_edit;
	}

	public void setEnable_edit(Boolean enable_edit) {
		this.enable_edit = enable_edit;
	}

	public Integer getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Integer channel_id) {
		this.channel_id = channel_id;
	}

	public Integer getCreated_account() {
		return created_account;
	}

	public void setCreated_account(Integer created_account) {
		this.created_account = created_account;
	}
		
	
}