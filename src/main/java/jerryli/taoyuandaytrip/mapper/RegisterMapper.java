package jerryli.taoyuandaytrip.mapper;

import jerryli.taoyuandaytrip.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jerry
 * @create 2024-05-21-下午 04:39
 */
public interface RegisterMapper {

    public User getUserByAccount(@Param("account")String account);
    public User getUserByEmail(@Param("email")String email);
    public int addUser(@Param("user")User user);
}
