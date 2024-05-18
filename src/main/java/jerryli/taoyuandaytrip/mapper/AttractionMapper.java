package jerryli.taoyuandaytrip.mapper;

import jerryli.taoyuandaytrip.pojo.Attraction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-17-下午 04:44
 */
public interface AttractionMapper {

    public List<Attraction> getAllAttraction();

    public Attraction getAttractionById(@Param("id") Integer id);
}
