package jerryli.taoyuandaytrip.service;

import jerryli.taoyuandaytrip.pojo.LoginRequest;
import jerryli.taoyuandaytrip.pojo.LoginResponse;
import jerryli.taoyuandaytrip.pojo.StatusResponse;
import org.apache.ibatis.annotations.Param;

import javax.naming.NameNotFoundException;

/**
 * @author Jerry
 * @create 2024-05-22-下午 10:29
 */
public interface LoginService {
    public LoginResponse login(LoginRequest request) throws NameNotFoundException;
}
