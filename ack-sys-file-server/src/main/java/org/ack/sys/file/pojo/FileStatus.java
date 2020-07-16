package org.ack.sys.file.pojo;

/**
 * 文件上传状态
 */
public enum FileStatus {
    FILE_IS_EMPTY(400, "参数错误,上传文件为空!"),
    FILE_FORMAT_ERROR(400, "文件格式错误!"),
    FILE_SIZE_ERROR(400, "文件大小超过限制"),
    UPLOAD_SUCCESS(200, "上传成功"),
    FILE_FORMAT_CORRECT(200, "文件格式正确"),
    UPLOAD_ERROR(500, "未知原因,上传失败!");

    private int code;
    private String msg;

    FileStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
