package com.julioluis.cenordlayered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CenordLayeredApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenordLayeredApplication.class, args);
    }

}
