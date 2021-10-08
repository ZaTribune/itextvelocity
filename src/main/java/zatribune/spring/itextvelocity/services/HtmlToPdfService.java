package zatribune.spring.itextvelocity.services;

import zatribune.spring.itextvelocity.dto.PdfRequest;

public interface HtmlToPdfService {
    String convert(PdfRequest request);
}
