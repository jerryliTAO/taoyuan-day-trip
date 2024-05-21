package jerryli.taoyuandaytrip.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Jerry
 * @create 2024-05-21-下午 04:33
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id;
    private String account;
    private String email;
    private String password;
    private String phone;
    private LocalDateTime registTime;
}
