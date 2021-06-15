package se.kry.task.assignment.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.kry.task.assignment.entity.WebServices;
import se.kry.task.assignment.service.WebServicesService;

import java.util.List;

@Component
public class ServerVerticle extends AbstractVerticle {

    @Autowired
    private WebServicesService service;


    @Autowired
    private Integer defaultPort;

    private void getAllArticlesHandler(RoutingContext routingContext) {
        vertx.eventBus()
            .<String>send(EndpointRecipientVerticle.GET_ALL_ARTICLES, "", result -> {
                if (result.succeeded()) {
                    routingContext.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(200)
                        .end(result.result()
                        .body());
                } else {
                    routingContext.response()
                        .setStatusCode(500)
                        .end();
                }
            });
    }

    @Override
    public void start() throws Exception {
        super.start();

        List<WebServices> webServices= service.findAll();

        for(WebServices webService:webServices) {
            Router router = Router.router(vertx);
            router.get(webService.getUrl())
                .handler(this::getAllArticlesHandler);

            vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port", defaultPort));
        }
    }

}
