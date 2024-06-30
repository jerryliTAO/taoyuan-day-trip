package jerryli.taoyuandaytrip.pojo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Jerry
 * @create 2024-05-29-下午 02:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRequest {
    private Integer userId;
    private Integer attractionId;

    private String dateTime;
    private Integer price;
    private String period;
}
