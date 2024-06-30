package jerryli.taoyuandaytrip.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-29-下午 02:12
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    private User user;
    private Integer totalPrice;
    private List<CartItem> cartItemList;
}
