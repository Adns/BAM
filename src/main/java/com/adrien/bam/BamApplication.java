package com.adrien.bam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.adrien.bam.*")
public class BamApplication {

    public static void main(String[] args) {
        SpringApplication.run(BamApplication.class, args);
    }

}
