package jerryli.taoyuandaytrip;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan(basePackages = "jerryli.taoyuandaytrip.mapper")
@SpringBootApplication
public class TaoyuanDayTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaoyuanDayTripApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
