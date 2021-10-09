package zatribune.spring.itextvelocity.controllers;


import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import zatribune.spring.itextvelocity.dto.PdfRequest;
import zatribune.spring.itextvelocity.services.HtmlToPdfService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@RequestMapping("/report")
@RestController
public class ReportingController {


    private final HtmlToPdfService service;

    public ReportingController(HtmlToPdfService service) {
        this.service = service;
    }

    @PostMapping(value = "/pdf", produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> generateReport(@RequestBody PdfRequest pdfRequest, @RequestHeader("Accept") MediaType accept)
            throws NotFoundException, HttpMediaTypeNotAcceptableException {
        List<MediaType> supportedMediaTypes = List.of(MediaType.APPLICATION_PDF, MediaType.TEXT_PLAIN);

        if (supportedMediaTypes.contains(accept))
            return service.generate(pdfRequest,accept);
        else {
            throw new HttpMediaTypeNotAcceptableException(supportedMediaTypes);
        }
    }

}
