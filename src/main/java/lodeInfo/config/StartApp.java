package lodeInfo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("lodeInfo")
@EntityScan(basePackages = "lodeInfo")
@ComponentScan("lodeInfo")
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);

    }
}