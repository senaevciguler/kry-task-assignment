package se.kry.task.assignment;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import se.kry.task.assignment.verticles.EndpointRecipientVerticle;
import se.kry.task.assignment.verticles.ServerVerticle;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
public class AssignmentApplication {

	@Autowired
	private ServerVerticle serverVerticle;

	@Autowired
	private EndpointRecipientVerticle serviceVerticle;

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@PostConstruct
	public void deployVerticle() {
		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(serverVerticle);
		vertx.deployVerticle(serviceVerticle);
	}
}
