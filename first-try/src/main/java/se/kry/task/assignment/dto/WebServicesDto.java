package se.kry.task.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebServicesDto {
    private Long id;

    @NonNull
    private String name;
    private String method;

    @NonNull
    private String url;
}
