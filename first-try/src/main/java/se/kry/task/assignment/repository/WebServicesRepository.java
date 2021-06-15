package se.kry.task.assignment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.kry.task.assignment.entity.WebServices;

public interface WebServicesRepository extends JpaRepository<WebServices, Long> {
}
