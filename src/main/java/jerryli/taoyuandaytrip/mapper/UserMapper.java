package jerryli.taoyuandaytrip.mapper;

import jerryli.taoyuandaytrip.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jerry
 * @create 2024-05-22-下午 05:53
 */
public interface UserMapper {

    public User getUserByEmail(@Param("email") String email);

    public User getUserById(@Param("id") Integer id);
}
