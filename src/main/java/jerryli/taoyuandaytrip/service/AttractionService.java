package jerryli.taoyuandaytrip.service;

import jerryli.taoyuandaytrip.pojo.Attraction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-17-下午 05:26
 */

public interface AttractionService {

    public List<Attraction> getAllAttraction();

    public Attraction getAttractionById(Integer id);



}
