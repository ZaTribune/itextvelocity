package zatribune.spring.itextvelocity.controllers;


import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zatribune.spring.itextvelocity.dto.PdfRequest;
import zatribune.spring.itextvelocity.services.HtmlToPdfService;


@Slf4j
@RequestMapping("/report")
@RestController
public class ReportingController {


    private final HtmlToPdfService service;

    public ReportingController(HtmlToPdfService service) {
        this.service = service;
    }

    @PostMapping("/pdf")
     public String generateReport(@RequestBody PdfRequest pdfRequest) throws NotFoundException {
        return service.convert(pdfRequest);
     }

}
