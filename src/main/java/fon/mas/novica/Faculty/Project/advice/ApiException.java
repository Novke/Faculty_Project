package fon.mas.novica.Faculty.Project.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {

    private String message;
    private ZonedDateTime timestamp;

}
