package com.nesder.vo.resq;

public class RegistUser {
	
	private int id;

	private String account_id;

	private String role;

	private String password;

	private String nick_name;

	private String email;

	private Integer gender;

	private Integer age;

	private String introduction;

	private String avatarurl;

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id 設定する id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return account_id
	 */
	public String getAccount_id() {
		return account_id;
	}

	/**
	 * @param account_id 設定する account_id
	 */
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role 設定する role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 設定する password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return nick_name
	 */
	public String getNick_name() {
		return nick_name;
	}

	/**
	 * @param nick_name 設定する nick_name
	 */
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email 設定する email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender 設定する gender
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age 設定する age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction 設定する introduction
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return avatarurl
	 */
	public String getAvatarurl() {
		return avatarurl;
	}

	/**
	 * @param avatarurl 設定する avatarurl
	 */
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}

}
