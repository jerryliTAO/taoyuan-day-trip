package jerryli.taoyuandaytrip.service.impl;

import jerryli.taoyuandaytrip.mapper.RegisterMapper;
import jerryli.taoyuandaytrip.pojo.User;
import jerryli.taoyuandaytrip.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jerry
 * @create 2024-05-21-下午 04:56
 */
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public String ckUser(User user) {
        User userByEmail = registerMapper.getUserByEmail(user.getEmail());

        if(userByEmail!=null){
            return "此信箱已有人使用";
        }
        return "ok";
    }

    @Override
    public int addUser(User user) {

        String ckUser = ckUser(user);
        if(ckUser.equals("ok")){
            String encodePassword = encoder.encode(user.getPassword());
            user.setPassword(encodePassword);
            return registerMapper.addUser(user);
        }else {
            return 0;
        }
    }


}
