package c4compile.jwtdemo.token;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

public class TokenUtils {
    public static String getAccessToken(String userName, String password) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "password");
        requestBody.add("client_id", "admin-cli");
        requestBody.add("username", userName);
        requestBody.add("password", password);

        ResponseEntity<Map<String, Object>> response = new RestTemplate().exchange(
                RequestEntity.post(URI.create("http://localhost:8080/realms/master/protocol/openid-connect/token"))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .body(requestBody),
                new ParameterizedTypeReference<>() {
                });
        if (!response.getStatusCode().is2xxSuccessful())
            throw new IllegalStateException("HTTP responded with status code " + response.getStatusCodeValue());

        return Optional.ofNullable(response.getBody().get("access_token"))
                .map(Object::toString)
                .orElseThrow(() -> new IllegalStateException("HTTP response does not have access token"));
    }
}
