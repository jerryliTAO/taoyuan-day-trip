package jerryli.taoyuandaytrip.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jerryli.taoyuandaytrip.pojo.LoginRequest;
import jerryli.taoyuandaytrip.pojo.LoginResponse;
import jerryli.taoyuandaytrip.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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

    @PostMapping("/api/login")

//

    public ResponseEntity<LoginResponse> login( @RequestBody LoginRequest loginRequest,
                                                HttpServletRequest request,
                                               HttpServletResponse response) throws NameNotFoundException {

//    public ResponseEntity<LoginResponse> login(@Param("email") String email,
//                                               @Param("password") String password
//                                               ) throws NameNotFoundException {
//        LoginRequest loginRequest = new LoginRequest(email, password);

        LoginResponse login = loginService.login(loginRequest,request,response);
//        LoginResponse login = loginService.login(loginRequest);

        return  ResponseEntity.ok(login);


    }



    private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    @GetMapping("/api/logout")
    public void logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response){
        this.logoutHandler.logout(request,response,authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

    }



}
