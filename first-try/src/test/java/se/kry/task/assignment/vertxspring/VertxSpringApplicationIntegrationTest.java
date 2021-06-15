package se.kry.task.assignment.vertxspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class VertxSpringApplicationIntegrationTest {

    @Autowired
    private Integer port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void givenUrl_whenReceivedArticles_thenSuccess() throws InterruptedException {
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("http://localhost:" + port + "/api/v1/endpoints", String.class);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}


