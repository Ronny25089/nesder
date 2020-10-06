package com.nesder.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nesder.dao.entity.Account;

public class UserContext implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserContext(Account user) {
		this.id = user.getAccount_id();
		this.username = user.getNick_name();
		this.password = user.getPassword();
		this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));

	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id 設定する id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username 設定する username
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities 設定する authorities
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
