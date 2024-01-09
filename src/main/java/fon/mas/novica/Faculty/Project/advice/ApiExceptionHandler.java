package fon.mas.novica.Faculty.Project.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<ApiException> handleFileNotFoundEx(FileNotFoundException ex){
        ApiException apiException = new ApiException(ex.getMessage(), ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiException> handleIllegalArgumentEx(IllegalArgumentException ex){
        ApiException apiException = new ApiException(ex.getMessage(), ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
