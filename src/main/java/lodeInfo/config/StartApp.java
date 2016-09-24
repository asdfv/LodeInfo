package lodeinfo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ComponentScan("lodeinfo")
@EnableJpaRepositories("lodeinfo.repository")
@EntityScan(basePackages = "lodeinfo")
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);

    }
}