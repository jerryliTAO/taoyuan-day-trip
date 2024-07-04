package jerryli.taoyuandaytrip.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-28-下午 10:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Integer id;
    private Integer userId;
    private String name;
    private String email;
    private String phone;
    private Integer totalPrice;
    private String number;
    private LocalDateTime orderTime;

    private List<CartItem> orderItem;
}
