package org.ack.sys.common;

public class ResponseResult {
    private int code;
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(int code, Object data) {
        this.code = code;
        this.data = data;
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

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
