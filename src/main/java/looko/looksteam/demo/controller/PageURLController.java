package looko.looksteam.demo.controller;

import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageURLController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping("/")
    public String indexHTML(Model model){
//        Player player = playerService.selectPlayer("76561198367830998");
//        model.addAttribute("player", player);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String loginHTML(){
        return "login";
    }

}
