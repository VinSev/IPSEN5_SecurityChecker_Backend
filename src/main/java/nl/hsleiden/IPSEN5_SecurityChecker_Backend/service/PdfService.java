package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Report;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.utility.TableMaker;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.structure.Table;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


@Service
public class PdfService {

    public void makePdf(Report report) throws IOException, URISyntaxException {

        PDDocument doc = new PDDocument();
        PDPage front_page = new PDPage();
        doc.addPage(front_page);
        PDFont pdfFont = PDType1Font.HELVETICA_BOLD;
        int fontSize = 14;

        PDImageXObject pdImage = PDImageXObject.createFromFile("src/main/resources/piggie2.png", doc);


        PDDocumentInformation docInfo = doc.getDocumentInformation();

        docInfo.setAuthor("Bruno Seriese");
        docInfo.setTitle("Security Analyse");
        docInfo.setSubject("Get Big Marketing");


        PDPageContentStream contentStream = new PDPageContentStream(doc, front_page, PDPageContentStream.AppendMode.APPEND, true, true);
        contentStream.setNonStrokingColor(Color.PINK);
        contentStream.addRect(0, 770, 700, 50);
        contentStream.fill();

//        contentStream.drawImage(pdImage, 70, 250);

        contentStream.setFont(pdfFont, fontSize);
        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.newLineAtOffset(50, 700);
        contentStream.showText("Get Big Marketing - Beveiliging Scan Resultaten");
        contentStream.endText();


        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.newLineAtOffset(50, 670);
        contentStream.showText("Geachte gebruiker");
        contentStream.endText();


        contentStream.beginText();
        contentStream.newLineAtOffset(50, 645);
        contentStream.showText("Uw website website, is succesvol gescand op veiligheid.");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50, 625);
        contentStream.showText("in dit document staan de uitgebreide resultaten van deze scan");
        contentStream.endText();

        for(ScanReport scanReport : report.getScanReports()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            TableMaker tabbie = new TableMaker();

            Table mytabbie = tabbie.createTable(scanReport);
            TableDrawer tableDrawer = TableDrawer.builder()
                    .page(page)
                    .startX(50f)
                    .startY(front_page.getMediaBox().getUpperRightY() - 180f)
                    .table(mytabbie)
                    .endY(80F)
                    .build();
            tableDrawer.draw(() -> doc, () -> new PDPage(PDRectangle.A4), 10f);
        }

        contentStream.close();


        doc.save("src/main/resources/pdf/test.pdf");
    }

    public void makeTables(ScanReport scanRep) {

    }
}
