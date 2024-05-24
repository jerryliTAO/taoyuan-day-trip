package jerryli.taoyuandaytrip.service.impl;

import io.jsonwebtoken.Claims;
import jerryli.taoyuandaytrip.mapper.UserMapper;
import jerryli.taoyuandaytrip.pojo.LoginRequest;
import jerryli.taoyuandaytrip.pojo.LoginResponse;
import jerryli.taoyuandaytrip.pojo.User;
import jerryli.taoyuandaytrip.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;

/**
 * @author Jerry
 * @create 2024-05-22-下午 10:22
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtServiceImpl jwtService;



    @Override
    public LoginResponse login(LoginRequest loginRequest) throws NameNotFoundException {
        String token = null;

        Authentication authenticate = authenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword()));
        User user =(User) authenticate.getPrincipal();
        String accessToken = jwtService.createAccessToken(user);
        System.out.println(accessToken);
        Claims claims = jwtService.extractClaims(accessToken);
        System.out.println(claims);


        return new LoginResponse(accessToken, "null");
    }
}
