/*
package se.kry.task.assignment.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.kry.task.assignment.entity.WebServices;
import se.kry.task.assignment.repository.WebServicesRepository;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class DbBootstrap implements CommandLineRunner {

    @Autowired
    private WebServicesRepository webServicesRepository;

    @Override
    public void run(String... arg0) {

        IntStream.range(0, 10)
          .forEach(count -> this.webServicesRepository
                  .save(new WebServices(new Random().nextLong(),
                          UUID.randomUUID().toString(),
                          UUID.randomUUID().toString(),
                          UUID.randomUUID().toString())));

    }
}
*/
