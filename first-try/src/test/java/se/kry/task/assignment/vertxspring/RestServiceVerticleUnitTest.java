/*
package se.kry.task.assignment.vertxspring;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

public class RestServiceVerticleUnitTest {

    private Vertx vertx;

    private int port = 8081;

    @BeforeTestClass
    public static void beforeClass() {

    }

    @BeforeEach
    public void setup(TestContext testContext) throws IOException {
        vertx = Vertx.vertx();

        // Pick an available and random
        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();

        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));

        vertx.deployVerticle(RestServiceVerticleUnitTest.class.getName(), options, testContext.asyncAssertSuccess());
    }

    @AfterEach
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void givenId_whenReceivedArticle_thenSuccess(TestContext testContext) {
        final Async async = testContext.async();

        vertx.createHttpClient()
                .getNow(port, "localhost", "/api/baeldung/articles/article/12345", response -> {
                    response.handler(responseBody -> {
                        testContext.assertTrue(responseBody.toString()
                                .contains("\"id\" : \"12345\""));
                        async.complete();
                    });
                });
    }

}
*/
