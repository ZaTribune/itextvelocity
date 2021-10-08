package zatribune.spring.itextvelocity.services;

import javassist.NotFoundException;
import zatribune.spring.itextvelocity.dto.PdfRequest;

public interface HtmlToPdfService {
    String convert(PdfRequest request) throws NotFoundException;
}
