package jerryli.taoyuandaytrip.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jerryli.taoyuandaytrip.service.RegisterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jerry
 * @create 2024-05-21-下午 04:26
 */
@Hidden
@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public String register(@Param("account") String account,
                           @Param("email") String email,
                           @Param("password") String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        System.out.println(encode);

        System.out.println("============");
        System.out.println(bCryptPasswordEncoder.matches(password, encode));



        System.out.println(account);
        System.out.println(account);
        System.out.println(account);
        return "success";
    }
}
