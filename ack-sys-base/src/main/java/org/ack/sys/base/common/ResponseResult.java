package org.ack.sys.base.common;

public class ResponseResult {
    private int code;
    private Object data;
    private String msg;

    public ResponseResult() {
    }

    public ResponseResult(int code, Object data) {
        this.code = code;
        this.data = data;
        this.msg = "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
