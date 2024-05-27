package jerryli.taoyuandaytrip.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jerryli.taoyuandaytrip.pojo.User;
import jerryli.taoyuandaytrip.service.impl.JwtServiceImpl;
import jerryli.taoyuandaytrip.service.impl.MyUserDetailsServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Jerry
 * @create 2024-05-24-下午 07:40
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtServiceImpl jwtService;

    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = authHeader.substring(7);
        Claims claims = jwtService.extractClaims(token);
        userEmail =(String) claims.get("email");

        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        // check if there's no user login
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            //according email to load user information
            User user =(User) myUserDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(token,user)) {
                //if token is still valid, setting user is authenticating and saving into securityContextHolder
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
                this.securityContextRepository.saveContext(context,request,response);
            } else {
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
