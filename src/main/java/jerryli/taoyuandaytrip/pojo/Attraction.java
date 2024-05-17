package jerryli.taoyuandaytrip.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-17-下午 04:45
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attraction {

    private int id;
    private String name;
    private String description;
    private String district;
    private String address;
    private Double longitude;
    private Double latitude;
    private String openTime;

    private List<String> image;
    private List<String> category;

}
