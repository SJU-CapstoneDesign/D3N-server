package com.example.d3nserver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class D3NServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(D3NServerApplication.class, args);
    }
}
