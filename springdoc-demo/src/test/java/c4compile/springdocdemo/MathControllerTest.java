package c4compile.springdocdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MathControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void divide_shouldSuccess() {
        ResponseEntity<DivideResponse> response = testRestTemplate.postForEntity("/divide",
                new DivideRequest().setDividend(10).setDivisor(3),
                DivideResponse.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getQuotient()).isEqualTo(3);
        assertThat(response.getBody().getRemainder()).isEqualTo(1);
    }
}