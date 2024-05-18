package jerryli.taoyuandaytrip.service.impl;

import jerryli.taoyuandaytrip.mapper.AttractionMapper;
import jerryli.taoyuandaytrip.mapper.ClassMapper;
import jerryli.taoyuandaytrip.mapper.ImageMapper;
import jerryli.taoyuandaytrip.pojo.Attraction;
import jerryli.taoyuandaytrip.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-17-下午 05:59
 */
@Service
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    AttractionMapper attractionMapper;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    ClassMapper classMapper;

    @Override
    public List<Attraction> getAllAttraction() {
        List<Attraction> allAttraction = attractionMapper.getAllAttraction();
        int attractionIndex = 0;
        for (Attraction attraction : allAttraction) {
            List<String> imageById = imageMapper.getImageById(attraction.getId());
            List<String> classById = classMapper.getClassById(attraction.getId());
            attraction.setImage(imageById);
            attraction.setCategory(classById);

            // update attraction data
            allAttraction.set(attractionIndex,attraction);
            attractionIndex++;

        }

        return allAttraction;
    }

    @Override
    public Attraction getAttractionById(Integer id) {
        Attraction attraction = attractionMapper.getAttractionById(id);
        List<String> imageById = imageMapper.getImageById(id);
        List<String> classById = classMapper.getClassById(id);
        attraction.setImage(imageById);
        attraction.setCategory(classById);

        return attraction;
    }
}
