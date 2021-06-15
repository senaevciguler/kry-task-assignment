package se.kry.task.assignment.service;

import se.kry.task.assignment.entity.WebServices;

import java.util.List;
import java.util.Optional;

public interface WebServicesService {

    List<WebServices> findAll();

    Optional<WebServices> findById(long id);

    WebServices create(WebServices webServices);

    WebServices update(long id, WebServices webServices);

    WebServices deleteById(long id);
}
