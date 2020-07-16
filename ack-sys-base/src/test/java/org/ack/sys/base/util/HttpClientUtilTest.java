package org.ack.sys.base.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientUtilTest {

    @Test
    void testSendFile(){
        String file = "E:\\test\\img\\test.jpg";
        String path = "/20200708/tmp2.jpg";
        String url = "http://127.0.0.1:6060/upload";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", path);
        List<File> list = new ArrayList<File>();
        list.add(new File(file));
        String rt = HttpClientUtil.sendHttpPost(url, map, list);
        assertNotNull(rt);
        System.out.println(rt);
    }

    @Test
    void getHttpClient() {
    }

    @Test
    void sendHttpPost() {
    }

    @Test
    void sendHttpGet() {
    }

    @Test
    void testSendHttpPost() {
    }

    @Test
    void testSendHttpPost1() {
    }

    @Test
    void testSendHttpPost2() {
    }

    @Test
    void sendHttpPostJson() {
    }

    @Test
    void sendHttpPostXml() {
    }

    @Test
    void convertStringParamter() {
    }
}