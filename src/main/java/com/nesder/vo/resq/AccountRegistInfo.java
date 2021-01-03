package com.nesder.vo.resq;

import java.util.Date;
/**
 * アカウント登録情報Dto
 * @author yalinhe
 *
 */
public class AccountRegistInfo {
	private String nick_name;
	private String email;
	private String password;
	private Integer gender;
	private Date birthday;
	private String introduction;
	private Date create_date;
	/**
	 * @return nick_name
	 */
	public String getNick_name() {
		return nick_name;
	}
	/**
	 * @param nick_name セットする nick_name
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
	 * @param email セットする email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return gender
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * @param gender セットする gender
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * @return birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday セットする birthday
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return introduction
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * @param introduction セットする introduction
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * @return create_date
	 */
	public Date getCreate_date() {
		return create_date;
	}
	/**
	 * @param create_date セットする create_date
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
}