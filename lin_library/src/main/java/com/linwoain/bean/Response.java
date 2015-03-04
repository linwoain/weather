/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014年11月8日 上午11:36:29    
 */
package com.linwoain.bean;

/**
 * 网络操作返回最外层的实体类
 *
 * @author linwoain
 * @version 2014年9月3日 下午6:33:04
 */
public class Response<T> {

	public static final int SUCCESS = 10000;
	public static final int FAILED = 40000;
	private int status;

	private String msg;

	private T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

}
