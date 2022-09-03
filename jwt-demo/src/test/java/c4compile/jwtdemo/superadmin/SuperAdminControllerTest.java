package c4compile.jwtdemo.superadmin;

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
class SuperAdminControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getSuperAdminSomething_shouldReturnNotAuthenticated_whenNotUsingJwt() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/super-admin/something", String.class);

        assertEquals(401, response.getStatusCodeValue());
    }

    @Test
    public void getSuperAdminSomething_shouldReturnForbidden_whenJwtDoesNotHaveSuperAdminRole() {
        String accessToken = TokenUtils.getAccessToken("admin", "admin");

        ResponseEntity<String> response = testRestTemplate.exchange(
                RequestEntity.get(URI.create("/super-admin/something"))
                        .header("Authorization", "Bearer " + accessToken)
                        .build(),
                String.class);

        assertEquals(403, response.getStatusCodeValue());
    }

}