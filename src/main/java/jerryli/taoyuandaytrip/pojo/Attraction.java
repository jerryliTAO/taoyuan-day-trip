package jerryli.taoyuandaytrip.pojo;

import lombok.*;
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
@Builder
public class Attraction {

    private Integer id;
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
