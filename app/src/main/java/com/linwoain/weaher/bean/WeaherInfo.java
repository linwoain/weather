package com.linwoain.weaher.bean;


import com.linwoain.bean.BaseBean;

/**
 * 天气的实体类
 * @author snow0358530
 *
 */
public class WeaherInfo extends BaseBean{

	private static final long serialVersionUID = -5242103167896581258L;
	
	private int resultcode;
	
	private String reason;
	
	private String error_code;

	private Result result;

	public int getResultcode() {
		return resultcode;
	}

	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	

	
}
