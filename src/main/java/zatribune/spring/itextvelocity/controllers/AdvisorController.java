package zatribune.spring.itextvelocity.controllers;


import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
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

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'").format(new Date()));
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body,headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadReportEntryException.class)
    public ResponseEntity<Object> badReportEntryException(
            BadReportEntryException ex, WebRequest request) {
        log.error(ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'").format(new Date()));
        body.put("message", ex.getMessage());
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(body,headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Object> unsupportedMediaTypeStatusException(
            HttpMediaTypeNotAcceptableException ex, WebRequest request) {
        String message=String.format("Supported Media types are %s. Please, adjust your HTTP [Accept] header.",ex.getSupportedMediaTypes());
        log.error(message);
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'").format(new Date()));
        body.put("message", message);
        return new ResponseEntity<>(body,headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
