package com.nesder.vo.resp;

import com.nesder.utils.CONSTANT;

public class ApiResponse {

	private String statusCode = CONSTANT.SUCCESS_CODE;

	private String Msg = "success";

	private Object data;
	
	/**
	 * @return msg
	 */
	public String getMsg() {
		return Msg;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @param msg 設定する msg
	 */
	public void setMsg(String msg) {
		Msg = msg;
	}

	/**
	 * @return data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data 設定する data
	 */
	public void setData(Object data) {
		this.data = data;
	}

}