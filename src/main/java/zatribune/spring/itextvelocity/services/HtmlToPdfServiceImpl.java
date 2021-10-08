package zatribune.spring.itextvelocity.services;


import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zatribune.spring.itextvelocity.controllers.VelocityTemplateParser;
import zatribune.spring.itextvelocity.db.entities.Report;
import zatribune.spring.itextvelocity.db.repositories.ReportRepository;
import zatribune.spring.itextvelocity.dto.PdfRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Service
public class HtmlToPdfServiceImpl implements HtmlToPdfService{


    @Value("${reporting.outputPath}")
    private String outputPath;

    @Value("${reporting.author}")
    private String author;

    @Value("${reporting.creator}")
    private String creator;

    private final ReportRepository repository;
    private final VelocityTemplateParser parser;

    public HtmlToPdfServiceImpl(ReportRepository repository,VelocityTemplateParser parser) {
        this.repository = repository;
        this.parser=parser;
    }

    @Override
    public String convert(PdfRequest request) {

        Report report=repository.findById(request.getReportId()).orElseThrow();//todo:add exception
        String html = parser.generateHTML(request.getData(),report);
        String resultPath = null;

        PdfWriter pdfWriter;
        // create a new pdf document
        Document pdfDocument=new Document();
        try {

            // document header attributes
            pdfDocument.addAuthor(author);
            pdfDocument.addCreationDate();
            pdfDocument.addCreator(creator);
            pdfDocument.addTitle("HTML to PDF using itext");
            pdfDocument.setPageSize(PageSize.LETTER);

            resultPath=String.format("%s%s%s",outputPath,report.getName(),new SimpleDateFormat("yyyyMMddhhmmss'.pdf'").format(new Date()));
            OutputStream file = new FileOutputStream(new File(resultPath));

            pdfWriter = PdfWriter.getInstance(pdfDocument, file);

            // open document
            pdfDocument.open();

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);

            xmlWorkerHelper.parseXHtml(pdfWriter, pdfDocument, new StringReader(html));
            // close the document
            pdfDocument.close();
            // close the writer
            pdfWriter.close();


            System.out.println("PDF generated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultPath;
    }
}
