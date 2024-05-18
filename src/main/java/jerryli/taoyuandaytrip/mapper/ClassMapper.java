package jerryli.taoyuandaytrip.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-17-下午 05:31
 */
public interface ClassMapper {
    public List<String> getClassById(@Param("id") Integer id);

}
