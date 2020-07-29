package test.java.Resilience4j.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CircuitBreakApp {

    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakApp.class, args);
    }
}
