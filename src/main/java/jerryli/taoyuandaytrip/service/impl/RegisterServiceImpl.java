package jerryli.taoyuandaytrip.service.impl;

import jerryli.taoyuandaytrip.mapper.RegisterMapper;
import jerryli.taoyuandaytrip.pojo.User;
import jerryli.taoyuandaytrip.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @create 2024-05-21-下午 04:56
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterMapper registerMapper;

    @Override
    public String ckUser(User user) {
        User userByAccount = registerMapper.getUserByAccount(user.getAccount());
        User userByEmail = registerMapper.getUserByEmail(user.getEmail());

        if(userByAccount!=null){
            return "此帳號已有人使用";
        }
        if(userByEmail!=null){
            return "此信箱已有人使用";
        }
        return "ok";
    }

    @Override
    public int addUser(User user) {
        String password = user.getPassword();



        return registerMapper.addUser(user);
    }
}
