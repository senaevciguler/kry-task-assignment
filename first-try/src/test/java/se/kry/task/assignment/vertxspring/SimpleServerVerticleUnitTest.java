/*
package se.kry.task.assignment.vertxspring;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kry.task.assignment.verticles.ServerVerticle;

import java.io.IOException;
import java.net.ServerSocket;

public class SimpleServerVerticleUnitTest {
    private Vertx vertx;

    private int port = 8081;

    @BeforeEach
    public void setup(TestContext testContext) throws IOException {
        vertx = Vertx.vertx();

        // Pick an available and random
        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();

        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));

        vertx.deployVerticle(ServerVerticle.class.getName(), options, testContext.asyncAssertSuccess());
    }

    @AfterEach
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void whenReceivedResponse_thenSuccess(TestContext testContext) {
        final Async async = testContext.async();

        vertx.createHttpClient()
            .getNow(port, "localhost", "/", response -> response.handler(responseBody -> {
                testContext.assertTrue(responseBody.toString()
                    .contains("Welcome"));
                async.complete();
            }));
    }

}
*/
