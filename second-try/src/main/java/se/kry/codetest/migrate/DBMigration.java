package se.kry.codetest.migrate;

import io.vertx.core.Vertx;
import se.kry.codetest.PollerDB;

public class DBMigration {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        PollerDB connector = new PollerDB(vertx);
        connector.query("CREATE TABLE IF NOT EXISTS services (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "url VARCHAR(128) NOT NULL, " +
                "name VARCHAR(128)," +
                "status_response INTEGER," +
                "user_cookie_id VARCHAR(128)," +
                "creation_date DATETIME2(3))").onComplete(done -> {
            if (done.succeeded()) {
                System.out.println("completed db migrations");
            } else {
                done.cause().printStackTrace();
            }
            vertx.close(shutdown -> {
                System.exit(0);
            });
        });
    }
}
