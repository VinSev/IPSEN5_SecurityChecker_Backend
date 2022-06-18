package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SecurityAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.utility.TableMaker;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.structure.Table;

import java.io.IOException;
import java.util.ArrayList;


@Service
public class PdfService {




    public void makePdf(ArrayList<SecurityAlert> securityAlerts) throws IOException {

        PDDocument doc = new PDDocument();
        PDPage mypage = new PDPage();
        doc.addPage(mypage);
        PDFont pdfFont= PDType1Font.HELVETICA_BOLD;
        int fontSize = 14;



        PDDocumentInformation docInfo =  doc.getDocumentInformation();

        docInfo.setAuthor("Bruno Seriese");
        docInfo.setTitle("Security Analyse");
        docInfo.setSubject("Get Big Marketing");



        PDPageContentStream contentStream = new PDPageContentStream(doc, mypage, PDPageContentStream.AppendMode.APPEND,true,true);
        contentStream.setFont(pdfFont, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(50,750);
        contentStream.showText("Get Big Marketing - Beveiliging Scan Resultaten");
        contentStream.endText();


        contentStream.beginText();
        contentStream.newLineAtOffset(50,670);
        contentStream.showText("Geachte gebruiker");
        contentStream.endText();





        contentStream.beginText();
        contentStream.newLineAtOffset(50,645);
        contentStream.showText("Uw website website, is succesvol gescand op veiligheid.");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50,625);
        contentStream.showText("in dit document staan de uitgebreide resultaten van de ze scan");
        contentStream.endText();

        TableMaker tabbie = new TableMaker();


        Table mytabbie = tabbie.createTable(securityAlerts,"Bruno","www.google.com");





        TableDrawer tableDrawer = TableDrawer.builder()
                .contentStream(contentStream)
                .startX(50f)
                .startY(mypage.getMediaBox().getUpperRightY() - 220f)
                .table(mytabbie)
                .build();

        // And go for it!
        tableDrawer.draw();
//
        contentStream.close();
//
//
//
//





        doc.save("G:\\uniwerk\\pdf\\myTest.pdf");
    }
}
