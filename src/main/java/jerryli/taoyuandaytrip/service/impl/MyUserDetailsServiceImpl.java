package jerryli.taoyuandaytrip.service.impl;

import jerryli.taoyuandaytrip.mapper.UserMapper;
import jerryli.taoyuandaytrip.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @create 2024-05-22-下午 05:06
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.getUserByEmail(email);
        if(user == null){
            String msg = "帳號: " + email + "不存在";
            System.out.println(msg);
            throw new UsernameNotFoundException(msg);
        }

        return user;
    }
}
