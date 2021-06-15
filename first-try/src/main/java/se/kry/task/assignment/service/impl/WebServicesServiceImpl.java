package se.kry.task.assignment.service.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kry.task.assignment.entity.WebServices;
import se.kry.task.assignment.exception.ResourceNotFoundException;
import se.kry.task.assignment.repository.WebServicesRepository;
import se.kry.task.assignment.service.WebServicesService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebServicesServiceImpl implements WebServicesService {

    private final WebServicesRepository webServicesRepository;

    public List<WebServices> findAll() {
        return webServicesRepository.findAll();
    }

    @Override
    public Optional<WebServices> findById(long id) {
        return webServicesRepository.findById(id);
    }

    @Override
    public WebServices create(WebServices webServices) {
        return webServicesRepository.save(webServices);
    }

    @Override
    public WebServices update(long id, WebServices webServices) {
        findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        webServices.setId(id);
        return webServicesRepository.save(webServices);
    }

    @Override
    public WebServices deleteById(long id) {
        WebServices webServices = webServicesRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        webServicesRepository.deleteById(id);

        return webServices;
    }
}
