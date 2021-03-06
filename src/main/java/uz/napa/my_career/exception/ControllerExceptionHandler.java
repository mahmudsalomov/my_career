package uz.napa.my_career.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handlerException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @ExceptionHandler({ServerBadRequestException.class})
    public ResponseEntity<?> handlerException(ServerBadRequestException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
