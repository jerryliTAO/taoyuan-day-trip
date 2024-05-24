package jerryli.taoyuandaytrip.controller;

import jerryli.taoyuandaytrip.pojo.LoginRequest;
import jerryli.taoyuandaytrip.pojo.LoginResponse;
import jerryli.taoyuandaytrip.pojo.StatusResponse;
import jerryli.taoyuandaytrip.service.impl.LoginServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NameNotFoundException;

/**
 * @author Jerry
 * @create 2024-05-22-下午 06:18
 */

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    LoginServiceImpl loginService;

    @PostMapping("/api/login")
//    @RequestBody LoginRequest loginRequest
    public ResponseEntity<LoginResponse> login(@Param("email") String email,
                                                @Param("password") String password) throws NameNotFoundException {
        LoginRequest loginRequest = new LoginRequest(email, password);
        LoginResponse login = loginService.login(loginRequest);

        return  ResponseEntity.ok(login);


    }


}
