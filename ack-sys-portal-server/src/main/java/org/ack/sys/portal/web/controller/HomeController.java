package org.ack.sys.portal.web.controller;

import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.portal.service.PortalMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {
    @Autowired
    private PortalMenuService portalMenuServiceImpl;
    @RequestMapping("/")
    public String home(Model model){
        PortalMenu menu = portalMenuServiceImpl.findSortedMenu();
        model.addAttribute("aaa", "bbbbb");
        model.addAttribute("menus", menu.getChildren());
        return "index";
    }
    @RequestMapping("/test")
    public String test1() {
    	return "test";
    }
}
