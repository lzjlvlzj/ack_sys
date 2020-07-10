package org.ack.sys.file.web.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void init(){
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

    @Test
    void upload() throws Exception {
        String path = "C:\\Users\\ack\\Downloads\\mywx.jpg";
        String url = "/upload";
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");
        FileInputStream fis = new FileInputStream(path);

        MockMultipartFile mfile = new MockMultipartFile("C:/Users/Administrator/Desktop", "数据导入_非正常户信息_20170414.xls", "application/vnd_ms-excel", fis);
    }
    @Test
    void findImg() throws Exception{

        /**
         * 1、mockMvc.perform执行一个请求。
         * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
         * 3、ResultActions.param添加请求传值
         * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
         * 5、ResultActions.andExpect添加执行完成后的断言。
         * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
         *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
         * 5、ResultActions.andReturn表示执行完成后返回相应的结果。
         */

        /*
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/")
                .param("name","lvgang")
                .accept(MediaType.TEXT_HTML_VALUE))
                // .andExpect(MockMvcResultMatchers.status().isOk())             //等同于Assert.assertEquals(200,status);
                // .andExpect(MockMvcResultMatchers.content().string("hello lvgang"))    //等同于 Assert.assertEquals("hello lvgang",content);
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        */
        String url = "/";
        RequestBuilder rb = MockMvcRequestBuilders.get(url);
        MvcResult mvcResult= mockMvc.perform(rb).andReturn();
        int status=mvcResult.getResponse().getStatus();                 //得到返回代码
        String content=mvcResult.getResponse().getContentAsString();    //得到返回结果
        System.out.println(content);
        assertEquals(200,status);                        //断言，判断返回代码是否正确
        System.out.println(content);
    }
}