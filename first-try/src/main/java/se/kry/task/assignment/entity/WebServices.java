package se.kry.task.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WebServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 200, message = "Name must be between 2 and 50 characters")
    private String name;
    private String method;

    @NotBlank
    @Size(min = 5, max = 2000, message = "URL must be between 5 and 2000 characters")
    private String url;
}
