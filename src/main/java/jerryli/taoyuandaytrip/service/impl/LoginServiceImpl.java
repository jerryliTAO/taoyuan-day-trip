package jerryli.taoyuandaytrip.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jerryli.taoyuandaytrip.mapper.UserMapper;
import jerryli.taoyuandaytrip.pojo.request.LoginRequest;
import jerryli.taoyuandaytrip.pojo.LoginResponse;
import jerryli.taoyuandaytrip.pojo.User;
import jerryli.taoyuandaytrip.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
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
    MyUserDetailsServiceImpl myUserDetailsService;
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    JwtServiceImpl jwtService;

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @Override
    public LoginResponse login(LoginRequest loginRequest,HttpServletRequest request,HttpServletResponse response) throws NameNotFoundException, BadCredentialsException {
        String token = null;

        String accessToken = null;
        try {
            authenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword()));

            User user = (User) myUserDetailsService.loadUserByUsername(loginRequest.getEmail());
            UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //save authentication to SecurityContextHolder
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticate);
            SecurityContextHolder.setContext(context);
            /**
             * In Spring Security 6, the default behavior is that the SecurityContextHolderFilter will only read the SecurityContext from SecurityContextRepository
             * and populate it in the SecurityContextHolder. So if we want to save the authentication between requests, we must save the context into repository
             */
            securityContextRepository.saveContext(context, request, response);

            // create jwt
            accessToken = jwtService.createAccessToken(user);
        } catch (AuthenticationException e) {
            System.out.println("帳號或密碼錯誤");
        }

        if (accessToken == null) {
            return new LoginResponse("error", accessToken, "null");
        }

        return new LoginResponse("success", accessToken, "null");
    }
}
