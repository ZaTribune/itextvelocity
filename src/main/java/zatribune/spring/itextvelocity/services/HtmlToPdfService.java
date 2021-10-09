package zatribune.spring.itextvelocity.services;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import zatribune.spring.itextvelocity.dto.PdfRequest;

import java.io.OutputStream;

public interface HtmlToPdfService {
    ResponseEntity<?> generate(PdfRequest request) throws NotFoundException;
}
