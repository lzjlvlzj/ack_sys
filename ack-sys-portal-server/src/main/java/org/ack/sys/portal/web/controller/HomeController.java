package org.ack.sys.portal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String test(){
        return "index";
    }
    @RequestMapping("/test")
    public String test1() {
    	return "test";
    }
}
