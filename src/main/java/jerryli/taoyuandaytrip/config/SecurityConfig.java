package jerryli.taoyuandaytrip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

/**
 * @author Jerry
 * @create 2024-05-21-下午 06:08
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService MyUserDetailsServiceImpl;
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    //setting customize UserDetailsService
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(MyUserDetailsServiceImpl);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(
                                "/","/index","/attraction/**",
                                "/register","/auth/login","/auth/register",
                                "/api/**").permitAll()
                        .requestMatchers("/auth/logout" ).hasAuthority("user")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .permitAll())
                .csrf().disable();
        ;

        return http.build();
    }



    // access statics without authenticating
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web)->web.ignoring().requestMatchers("/css/**","/script/**","/images/**");
    }
    ;

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password(encoder().encode("userPass"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }


}
