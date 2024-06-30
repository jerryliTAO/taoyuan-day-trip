package jerryli.taoyuandaytrip.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jerryli.taoyuandaytrip.pojo.request.LoginRequest;
import jerryli.taoyuandaytrip.pojo.LoginResponse;

import javax.naming.NameNotFoundException;

/**
 * @author Jerry
 * @create 2024-05-22-下午 10:29
 */
public interface LoginService {
    public LoginResponse login(LoginRequest loginRequest,HttpServletRequest request,HttpServletResponse response) throws NameNotFoundException;
}
