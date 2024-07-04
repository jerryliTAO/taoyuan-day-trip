package jerryli.taoyuandaytrip.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jerry
 * @create 2024-07-03-下午 04:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer userId;
    private String prime;
    private Integer price;
    private String name;
    private String email;
    private String phone_number;
}
