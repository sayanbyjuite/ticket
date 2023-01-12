package com.example.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {TicketIdNotFoundException.class})
    public ResponseEntity<Object> handleTicketNotFoundException(TicketIdNotFoundException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CustomException exception =
                new CustomException(e.getMessage(),
                                    httpStatus,
                                    ZonedDateTime.now());
        return new ResponseEntity<>(exception, httpStatus);
    }
}
