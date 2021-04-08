package com.iflysse.helper.bean;

public class Result<T> {
 
	private ResultCode errorInfo;
    private T data;
 
    public Boolean getSuccess() {
		return errorInfo.getSuccess();
	}

	public Integer getCode() {
		return errorInfo.getCode();
	}

	public String getMessage() {
		return errorInfo.getMessage();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Result(ResultCode errorInfo, T data){
		this.errorInfo = errorInfo;
    	this.data = data;
    }
 
}
