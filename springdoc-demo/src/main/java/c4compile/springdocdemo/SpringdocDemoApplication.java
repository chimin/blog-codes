package c4compile.springdocdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Calculator",
                version = "1.0.0"))
public class SpringdocDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdocDemoApplication.class, args);
    }

}
