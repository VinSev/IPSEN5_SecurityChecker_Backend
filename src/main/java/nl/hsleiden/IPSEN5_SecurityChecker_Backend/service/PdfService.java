package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public class PdfService {




    public void makePdf() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = document.getPage(1);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.newLineAtOffset(25, 700);
        contentStream.endText();
    }
}
