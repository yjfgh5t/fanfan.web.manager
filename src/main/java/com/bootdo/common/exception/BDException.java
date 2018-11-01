package com.bootdo.common.exception;

/**
 * 自定义异常
 */
public class BDException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;

	/**
	 * 验证异常
	 */
	public static int VERIFY_ERROR_CODE=1;

	public static int BUSINESS_ERROR_CODE=2;

    public BDException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public BDException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public BDException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public BDException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
