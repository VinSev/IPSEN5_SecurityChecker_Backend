package nl.hsleiden.IPSEN5_SecurityChecker_Backend.utility;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Report;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.cell.TextCell;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class TableMaker {

    private static final String OVERFLOW_ON_SAME_PAGE_PDF = "overflowOnSamePage.pdf";





    public Table createTable(ScanReport scanReport) {
        final Table.TableBuilder tableBuilder = Table.builder()
                .addColumnOfWidth(150)
                .addColumnOfWidth(150)
                .addColumnOfWidth(150);

        TextCell h1 = TextCell.builder()
                .text("Naam")
                .backgroundColor(Color.PINK)
                .textColor(Color.WHITE)
                .borderColor(Color.PINK)
                .borderWidth(1F)
                .build();

        TextCell h2 = TextCell.builder()
                .text("Geslaagd")
                .backgroundColor(Color.PINK)
                .borderColor(Color.PINK)
                .textColor(Color.WHITE)
                .borderWidth(1F)
                .build();
        TextCell h3 = TextCell.builder()
                .text("Uitleg")
                .backgroundColor(Color.PINK)
                .borderColor(Color.PINK)
                .textColor(Color.WHITE)
                .borderWidth(1F)
                .build();

        tableBuilder.addRow(
                Row.builder()
                        .add(h1)
                        .add(h2)
                        .add(h3)
                        .build());

        scanReport.getResult().forEach(alert ->{
                Color score = new Color((255),(79),(79));

                String gehaald = "Nee";
                if (alert.isPassed()){
                    gehaald = "Ja";
                    score = new Color((159),(221),(135));
                }
//            int cijfer  = scan.getGrade();


                Color color = new Color((159),(221),(135));

                tableBuilder.addRow(
                        Row.builder()
                                .add(TextCell.builder()
                                        .text(alert.getTitle())
                                        .borderWidth(1F)
                                        .borderColor(Color.PINK)
                                        .build())
                                .add(TextCell.builder()
                                        .text(gehaald)
                                        .borderWidth(1F)
                                        .borderColor(Color.PINK)
                                        .textColor(score)
                                        .build())
                                .add(TextCell.builder()
                                        .text(alert.getDescription())
                                        .borderWidth(1F)
                                        .borderColor(Color.PINK)
                                        .build())
                                .build());


        });


        return tableBuilder.build();
    }

}