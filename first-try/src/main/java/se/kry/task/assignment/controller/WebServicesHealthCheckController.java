package se.kry.task.assignment.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.kry.task.assignment.dto.WebServicesDto;
import se.kry.task.assignment.entity.WebServices;
import se.kry.task.assignment.exception.ResourceNotFoundException;
import se.kry.task.assignment.service.WebServicesService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WebServicesHealthCheckController {

    private final WebServicesService webServicesService;
    private final ModelMapper modelMapper;

    @GetMapping("/endpoints")
    public List<WebServicesDto> findAll() {
        return webServicesService.findAll()
                .stream()
                .map(endpoint -> modelMapper.map(endpoint, WebServicesDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/endpoints/{id}")
    public WebServicesDto findById(@NonNull @PathVariable(value = "id") Long id) {
        WebServices product = webServicesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        return modelMapper.map(product, WebServicesDto.class);
    }

    @PostMapping("/endpoints")
    public ResponseEntity<WebServicesDto> create(@Valid @RequestBody WebServicesDto webServicesDto) {
        log.info("EndpointControllerImpl create: Create New Product Request");
        WebServices endpoint = webServicesService.create(modelMapper.map(webServicesDto, WebServices.class));

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/endpoints/{id}")
                .buildAndExpand(endpoint.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(modelMapper.map(endpoint, WebServicesDto.class));
    }


    @PutMapping("/endpoints/{id}")
    public ResponseEntity<WebServicesDto> update(@NonNull Long id, @Valid @RequestBody WebServicesDto endpointRequest) {
        WebServices endpoint = modelMapper.map(endpointRequest, WebServices.class);
        endpoint = webServicesService.update(id, endpoint);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/endpoints/{id}")
                .buildAndExpand(endpoint.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(modelMapper.map(endpoint, WebServicesDto.class));
    }


    @DeleteMapping("/endpoints/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        webServicesService.deleteById(id);
    }
}
