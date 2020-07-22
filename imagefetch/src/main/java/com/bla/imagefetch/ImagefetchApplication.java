package com.bla.imagefetch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ImagefetchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImagefetchApplication.class, args);
    }

}
