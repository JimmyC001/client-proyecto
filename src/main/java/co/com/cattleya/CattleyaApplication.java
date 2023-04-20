package co.com.cattleya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CattleyaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CattleyaApplication.class, args);
    }
}
