package se.kry.task.assignment.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.kry.task.assignment.service.WebServicesService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

@Slf4j
@Component
public class EndpointRecipientVerticle extends AbstractVerticle {

    public static final String GET_ALL_ARTICLES = "/api/v1/endpoints";

    @Autowired
    private WebServicesService webServicesService;

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus()
                .<String>consumer(GET_ALL_ARTICLES)
                .handler(getAllEndpointService(webServicesService));
    }

    private Handler<Message<String>> getAllEndpointService(WebServicesService service) {
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(Json.encode(service.findAll()));
            } catch (EncodeException e) {
                System.out.println("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause()
                        .toString());
            }
        });
    }

    private int getResponseCodeFromUrl(String url) {
        int code = 0;
        try {
            URL serviceUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);
            connection.connect();

            code = connection.getResponseCode();
            connection.disconnect();
        } catch (UnknownHostException h) {
            log.error("Host unknown for the given URL. " + url + " " + h);
            code = -1;
        } catch (SocketTimeoutException to) {
            log.error("Timeout for the given URL. " + url + " " + to);
        } catch (IOException e) {
            log.error("Could not connect to the given URL. " + url + " " + e);
        }
        return code;
    }
}
