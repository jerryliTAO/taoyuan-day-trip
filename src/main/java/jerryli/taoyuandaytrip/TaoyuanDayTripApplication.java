package jerryli.taoyuandaytrip;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "jerryli.taoyuandaytrip.mapper")
@SpringBootApplication
public class TaoyuanDayTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaoyuanDayTripApplication.class, args);
    }

}
