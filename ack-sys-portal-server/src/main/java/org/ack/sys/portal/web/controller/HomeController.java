package org.ack.sys.portal.web.controller;

import org.ack.sys.pojo.PortalCarousel;
import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.portal.pojo.Card;
import org.ack.sys.portal.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页
 */
@Controller
public class HomeController {
    @Autowired
    private HomeService homeServiceImpl;
    @RequestMapping("/")
    public String home(Model model){
        PortalMenu menu = homeServiceImpl.findMenu();
        List<Card> cardList = homeServiceImpl.findCardList();
        List<PortalCarousel> portalCarouselList = homeServiceImpl.findCarouselByPosition();
        model.addAttribute("aaa", "bbbbb");
        model.addAttribute("menus", menu.getChildren());
        model.addAttribute("cardList", cardList);
        model.addAttribute("carouselList", portalCarouselList);
        return "index";
    }
    @RequestMapping("/test")
    public String test1() {
    	return "test";
    }
}
