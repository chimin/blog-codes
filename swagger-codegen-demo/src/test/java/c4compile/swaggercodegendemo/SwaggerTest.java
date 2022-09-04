package c4compile.swaggercodegendemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SwaggerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void swagger_shouldExist() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/swagger.json", String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).contains("swagger");
    }
}
