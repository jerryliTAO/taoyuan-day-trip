package jerryli.taoyuandaytrip.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jerry
 * @create 2024-05-24-下午 05:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String status;
    private String accessToken;
    private String refreshToken;
}
