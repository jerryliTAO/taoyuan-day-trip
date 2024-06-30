package jerryli.taoyuandaytrip.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Jerry
 * @create 2024-05-28-下午 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private Integer id;
    private String date;
    private Integer price;
    private String period;
    private Integer orderId;
//    private Integer attractionId;
//    private Integer userId;
    private String image;

    private Attraction attraction;
    private User user;
}
