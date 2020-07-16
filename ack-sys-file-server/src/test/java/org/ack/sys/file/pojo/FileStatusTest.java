package org.ack.sys.file.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileStatusTest {
    @Test
    void testCode(){
        int code = FileStatus.UPLOAD_SUCCESS.getCode();
        String msg = FileStatus.UPLOAD_SUCCESS.getMsg();
        System.out.println(msg);
        assertEquals(200, code);
    }
}