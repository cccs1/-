package cn.itsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author $USER
 * @date ${DATE} ${TIME}
 * 描述：
 */
@SpringBootApplication
@MapperScan("cn.itsource.*.mapper")
public class PatHomeApp {
    public static void main(String[] args) {
        SpringApplication.run(PatHomeApp.class);
    }
}