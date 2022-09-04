package c4compile.swaggercodegendemo;

import c4compile.swaggercodegendemo.generated.models.DivideRequest;
import c4compile.swaggercodegendemo.generated.models.DivideResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void divide_shouldSuccess() {
        ResponseEntity<DivideResponse> response = testRestTemplate.postForEntity("/divide",
                new DivideRequest().dividend(10).divisor(3),
                DivideResponse.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getQuotient()).isEqualTo(3);
        assertThat(response.getBody().getRemainder()).isEqualTo(1);
    }
}
