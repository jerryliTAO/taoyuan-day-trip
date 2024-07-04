package jerryli.taoyuandaytrip.controller;

import jerryli.taoyuandaytrip.mapper.AttractionMapper;
import jerryli.taoyuandaytrip.mapper.ClassMapper;
import jerryli.taoyuandaytrip.mapper.ImageMapper;
import jerryli.taoyuandaytrip.pojo.Attraction;
import jerryli.taoyuandaytrip.service.AttractionService;
import jerryli.taoyuandaytrip.service.impl.AttractionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-17-下午 04:58
 */
@RestController
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ImageMapper imageMapper;

    @GetMapping("/api/attraction")
    public List<Attraction> getAllAttraction(){
        List<Attraction> allAttraction = attractionService.getAllAttraction();
        return allAttraction;
    }

    @GetMapping("/api/attraction/{id}")
    public Attraction getAttractionById(@PathVariable("id") Integer id){

        Attraction attraction = attractionService.getAttractionById(id);
        return attraction;
    }

    @GetMapping("/api/class/{id}")
    public Object getClassById(@PathVariable("id") Integer id){

        Object classById = classMapper.getClassById(id);
        return classById;
    }
    @GetMapping("/api/image/{id}")
    public Object getImageById(@PathVariable("id") Integer id){

        List<String> allImageById = imageMapper.getImageById(id);
        return allImageById;
    }


}
