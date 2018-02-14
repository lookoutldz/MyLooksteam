package looko.looksteam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageURLController {

    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }
}
