package c4compile.swaggerclientcodegendemo;

import c4compile.swaggerclientcodegendemo.generated.ApiClient;
import c4compile.swaggerclientcodegendemo.generated.math.api.MathApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerClientConfig {
    @Bean
    public MathApi mathApi(@Value("${math-api-client.base-path}") String basePath) {
        return new MathApi(new ApiClient().setBasePath(basePath));
    }
}
