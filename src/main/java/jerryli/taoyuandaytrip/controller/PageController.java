package jerryli.taoyuandaytrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jerry
 * @create 2024-05-17-下午 04:34
 */
@Controller
public class PageController {

    @GetMapping({"/","index"})
    public String getIndex(){
        return"index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return"login";
    }

    @GetMapping({"/attraction","/attraction/{id}"})
    public String getAttraction(){
        return"attraction";
    }

    @GetMapping("/cart")
    public String getCart(){
        return"cart";
    }
    @GetMapping("/register")
    public String getRegister(){
        return"register";
    }
    @GetMapping("/order")
    public String getOrder(){
        return"order";
    }

}
