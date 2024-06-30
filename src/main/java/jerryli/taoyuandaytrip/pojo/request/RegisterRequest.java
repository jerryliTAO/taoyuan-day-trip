package jerryli.taoyuandaytrip.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @create 2024-05-22-下午 10:20
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String account;
    private String email;
    private String password;
}
