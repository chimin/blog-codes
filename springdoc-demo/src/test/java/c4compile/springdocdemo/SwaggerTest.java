package c4compile.springdocdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void swagger_shouldExist() throws IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/v3/api-docs.yaml", String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).contains("openapi");

        Path localSwaggerFile = Paths.get("swagger.yaml");
        if (!localSwaggerFile.toFile().exists()) {
            Files.writeString(localSwaggerFile, response.getBody());
        }

        Path tempSwaggerFile = Paths.get("build", "tmp", "test", "swagger.yaml");
        tempSwaggerFile.getParent().toFile().mkdirs();
        Files.writeString(tempSwaggerFile, response.getBody());

        assertThat(tempSwaggerFile).hasSameTextualContentAs(localSwaggerFile);
    }
}