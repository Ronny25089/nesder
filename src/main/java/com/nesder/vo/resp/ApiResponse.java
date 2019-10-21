package com.nesder.vo.resp;

public class ApiResponse {

	private int statusCode = 200;

	private String Msg = "success";

	private Object data;

	/**
	 * @return statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode 設定する statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return msg
	 */
	public String getMsg() {
		return Msg;
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