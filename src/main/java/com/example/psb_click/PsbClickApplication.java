package com.example.psb_click;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "JOYDA-CLICK Integration API", version = "1.0",
        description = "Integration with CLICK for Joyda (description)"))
public class PsbClickApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsbClickApplication.class, args);
    }

}
