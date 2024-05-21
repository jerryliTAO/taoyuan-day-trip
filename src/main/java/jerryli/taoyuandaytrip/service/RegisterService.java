package jerryli.taoyuandaytrip.service;

import jerryli.taoyuandaytrip.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jerry
 * @create 2024-05-21-下午 04:54
 */
public interface RegisterService {

    public String ckUser(@Param("user") User user);
    public int addUser(@Param("user")User user);

}
