package jerryli.taoyuandaytrip.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jerryli.taoyuandaytrip.pojo.*;
import jerryli.taoyuandaytrip.pojo.request.LoginRequest;
import jerryli.taoyuandaytrip.pojo.request.RegisterRequest;
import jerryli.taoyuandaytrip.service.impl.LoginServiceImpl;
import jerryli.taoyuandaytrip.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NameNotFoundException;

/**
 * @author Jerry
 * @create 2024-05-22-下午 06:18
 */

@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    LoginServiceImpl loginService;
    @Autowired
    RegisterServiceImpl registerService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login( @RequestBody LoginRequest loginRequest,
                                                HttpServletRequest request,
                                               HttpServletResponse response) throws NameNotFoundException {

        LoginResponse login = loginService.login(loginRequest,request,response);

        return  ResponseEntity.ok(login);
    }



    private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    @GetMapping("/auth/logout")
    public void logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response){
        this.logoutHandler.logout(request,response,authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
    }

    @PostMapping("/auth/register")
    public ResponseEntity<StatusResponse> register(@RequestBody RegisterRequest request){
        User user = new User(0, request.getAccount(), request.getEmail(), request.getPassword(), null, "user");
        int result = registerService.addUser(user);
        if(result == 1){
            return ResponseEntity.ok(new StatusResponse("ok"));
        }else{
            return ResponseEntity.ok(new StatusResponse("帳號已有人使用，請換別的帳號"));
        }
    }



}
