package c4compile.jwtdemo.admin;

import c4compile.jwtdemo.token.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getAdminSomething_shouldReturnNotAuthenticated_whenNotUsingJwt() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/admin/something", String.class);

        assertEquals(401, response.getStatusCodeValue());
    }

    @Test
    public void getAdminSomething_shouldReturnNotAuthenticated_whenUsingInvalidJwt() {
        ResponseEntity<String> response = testRestTemplate.exchange(
                RequestEntity.get(URI.create("/admin/something"))
                        .header("Authorization", "Bearer abc")
                        .build(),
                String.class);

        assertEquals(401, response.getStatusCodeValue());
    }

    @Test
    public void getAdminSomething_shouldSuccess_whenUsingJwt() {
        String accessToken = TokenUtils.getAccessToken("admin", "admin");

        ResponseEntity<String> response = testRestTemplate.exchange(
                RequestEntity.get(URI.create("/admin/something"))
                        .header("Authorization", "Bearer " + accessToken)
                        .build(),
                String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Something from admin!", response.getBody());
    }

}