package jerryli.taoyuandaytrip.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.invoke.VarHandle;
import java.net.http.HttpRequest;

/**
 * @author Jerry
 * @create 2024-05-17-下午 04:34
 */
@Controller
public class PageController {

    @GetMapping({"/","index"})
    public String getIndex(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        Object principal = authentication.getPrincipal();
        Object details = authentication.getDetails();
        System.out.println(name);
        System.out.println(credentials);
        System.out.println(principal);
        System.out.println(details);

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
