package jerryli.taoyuandaytrip.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @create 2024-05-22-下午 10:31
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponse {
    private String status;
    private String token;
}
