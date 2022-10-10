package c4compile.swaggerclientcodegendemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "c4compile\\.swaggerclientcodegendemo\\.generated\\..*")})
public class SwaggerClientCodegenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerClientCodegenDemoApplication.class, args);
    }

}
