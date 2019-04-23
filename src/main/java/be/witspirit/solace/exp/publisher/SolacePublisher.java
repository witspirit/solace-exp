package be.witspirit.solace.exp.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolacePublisher {

    public static void main(String[] args) {
        SpringApplication.run(SolacePublisher.class, args);
    }

}
