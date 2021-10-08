package zatribune.spring.itextvelocity.controllers;


import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import zatribune.spring.itextvelocity.errors.BadReportEntryException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


@Slf4j
@ControllerAdvice
public class AdvisorController {



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(
            NotFoundException ex, WebRequest request) {
        log.error(ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'").format(new Date()));
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadReportEntryException.class)
    public ResponseEntity<Object> badReportEntryException(
            BadReportEntryException ex, WebRequest request) {
        log.error(ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'").format(new Date()));
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}