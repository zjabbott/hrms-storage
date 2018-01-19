package com.ln;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@AutoConfigurationPackage
@Configuration
@EntityScan("com.ln.model")
@SpringBootApplication(
        scanBasePackages = { "com.ln", "com.ln.dao" }
)
@MapperScan("com.ln.dao")
public class HrmsStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrmsStorageApplication.class, args);
    }
}
