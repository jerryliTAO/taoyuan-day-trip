package jerryli.taoyuandaytrip.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-17-下午 05:54
 */
public interface ImageMapper {
    public List<String> getImageById(@Param("id")Integer id);
}
