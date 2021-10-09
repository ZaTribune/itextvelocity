package zatribune.spring.itextvelocity.reporting;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import zatribune.spring.itextvelocity.db.entities.Report;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creates a PDF file from html {@link String} generated by {@link VelocityTemplateParser}
 * You can either return a {@link ResponseEntity} of the pdf path {@link String} or an {@link OutputStream} of the file.
 **/


@Slf4j
@Component
public class ITextPdfCreator {

    @Value("${reporting.outputPath}")
    private String outputPath;

    @Value("${reporting.author}")
    private String author;

    @Value("${reporting.creator}")
    private String creator;

    public ResponseEntity<?> createPdf(String html, Report report, Rectangle pageSize, MediaType mediaType) {
        PdfWriter pdfWriter;
        // create a new pdf document
        Document pdfDocument = new Document();
        String resultPath = String.format("%s%s%s", outputPath, report.getName(), new SimpleDateFormat("yyyyMMddhhmmss'.pdf'")
                .format(new Date()));
        // document header attributes
        pdfDocument.addAuthor(author);
        pdfDocument.addCreationDate();
        pdfDocument.addCreator(creator);
        pdfDocument.addTitle(report.getName());
        pdfDocument.setPageSize(pageSize);

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?>responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        try (OutputStream os = Files.newOutputStream(Paths.get(resultPath))) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            pdfWriter = PdfWriter.getInstance(pdfDocument, os);
            // open document
            pdfDocument.open();

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);

            xmlWorkerHelper.parseXHtml(pdfWriter, pdfDocument, new StringReader(html));
            // close the document
            pdfDocument.close();
            // close the writer
            pdfWriter.close();
            if (mediaType.equals(MediaType.APPLICATION_PDF)) {
                PdfReader reader = new PdfReader(resultPath);
                //you can only retrieve the ByteArrayOutputStream after using a stamper
                PdfStamper stamper = new PdfStamper(reader, baos);
                stamper.close();//you need to wait for it to close
                byte[] byteARy = baos.toByteArray();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
                responseEntity= new ResponseEntity<>(byteARy, headers, HttpStatus.OK);
            } else {
                headers.setContentType(MediaType.TEXT_PLAIN);
                responseEntity= new ResponseEntity<>(resultPath,headers, HttpStatus.OK);
            }
            log.info("PDF [{}] generated successfully", resultPath);

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }
}
