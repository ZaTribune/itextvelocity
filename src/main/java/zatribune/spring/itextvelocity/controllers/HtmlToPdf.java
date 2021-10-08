package zatribune.spring.itextvelocity.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import zatribune.spring.itextvelocity.reporting.VelocityTemplateParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

public class HtmlToPdf {

    public static void main(String[] args) {
        PdfWriter pdfWriter;
        // create a new document
        Document document;
        try {
            VelocityTemplateParser parser=new VelocityTemplateParser();
            //todo:update usage
            String html = parser.generateHTML(null,null);

            document = new Document();
            // document header attributes
            document.addAuthor("ZaTribune");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("muhammadali40k@gmail.com");
            document.addTitle("HTML to PDF using itext");
            document.setPageSize(PageSize.LETTER);

            OutputStream file = new FileOutputStream(new File("Test.pdf"));

            pdfWriter = PdfWriter.getInstance(document, file);

            // open document
            document.open();

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);

            xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(html));
            // close the document
            document.close();
            // close the writer
            pdfWriter.close();


            System.out.println("PDF generated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

