package zatribune.spring.itextvelocity.controllers;


import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zatribune.spring.itextvelocity.dto.PdfRequest;
import zatribune.spring.itextvelocity.services.HtmlToPdfService;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RequestMapping("/report")
@RestController
public class ReportingController {


    private final HtmlToPdfService service;

    public ReportingController(HtmlToPdfService service) {
        this.service = service;
    }

    @PostMapping(value = "/pdf",produces = {MediaType.APPLICATION_PDF_VALUE,MediaType.TEXT_PLAIN_VALUE})
     public ResponseEntity<?> generateReport(@RequestBody PdfRequest pdfRequest, HttpServletRequest request) throws NotFoundException {

        return service.generate(pdfRequest);
     }

}
