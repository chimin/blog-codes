package c4compile.jwtdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublicControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getSomething_shouldSuccess() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/something", String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Something!", response.getBody());
    }
}