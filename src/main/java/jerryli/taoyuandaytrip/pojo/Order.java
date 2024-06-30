package jerryli.taoyuandaytrip.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jerry
 * @create 2024-05-28-下午 10:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Integer userId;
    private String name;
    private String email;
    private String phone;
    private Integer totalPrice;
    private String number;
}
