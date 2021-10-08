package zatribune.spring.itextvelocity.services;


import com.itextpdf.text.PageSize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zatribune.spring.itextvelocity.reporting.ITextPdfCreator;
import zatribune.spring.itextvelocity.reporting.VelocityTemplateParser;
import zatribune.spring.itextvelocity.db.entities.Report;
import zatribune.spring.itextvelocity.db.repositories.ReportRepository;
import zatribune.spring.itextvelocity.dto.PdfRequest;


@Slf4j
@Service
public class HtmlToPdfServiceImpl implements HtmlToPdfService{

    private final ReportRepository repository;
    private final VelocityTemplateParser parser;
    private final ITextPdfCreator pdfCreator;

    public HtmlToPdfServiceImpl(ReportRepository repository,VelocityTemplateParser parser,ITextPdfCreator pdfCreator) {
        this.repository = repository;
        this.parser=parser;
        this.pdfCreator=pdfCreator;
    }

    @Override
    public String convert(PdfRequest request) {

        Report report=repository.findById(request.getReportId()).orElseThrow();//todo:add exception
        String html = parser.generateHTML(request.getData(),report);

        //todo: add page size to the request
        return pdfCreator.createPdf(html,report, PageSize.A4);
    }
}
