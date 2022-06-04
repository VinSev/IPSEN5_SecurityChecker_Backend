package nl.hsleiden.IPSEN5_SecurityChecker_Backend.utility;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.cell.TextCell;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class TableMaker {

    private static final String OVERFLOW_ON_SAME_PAGE_PDF = "overflowOnSamePage.pdf";


    public void createTwoPageTableWithRepeatedHeader() throws IOException {




    }

    public Table createTable(ArrayList<ScanCategory> scans, String name, String website) {
        final Table.TableBuilder tableBuilder = Table.builder()
                .addColumnOfWidth(150)
                .addColumnOfWidth(150)
                .addColumnOfWidth(150);

        TextCell h1 = TextCell.builder()
                .text("Naam")
                .backgroundColor(Color.BLACK)
                .textColor(Color.WHITE)
                .borderWidth(1F)
                .build();

        TextCell h2 = TextCell.builder()
                .text("Geslaagd")
                .backgroundColor(Color.BLACK)
                .textColor(Color.WHITE)
                .borderWidth(1F)
                .build();
        TextCell h3 = TextCell.builder()
                .text("Uitleg")
                .backgroundColor(Color.BLACK)
                .textColor(Color.WHITE)
                .borderWidth(1F)
                .build();

        tableBuilder.addRow(
                Row.builder()
                        .add(h1)
                        .add(h2)
                        .add(h3)
                        .build());

        scans.forEach(scan ->{

            Color score = Color.RED;

            String gehaald = "Nee";
            if (scan.getGrade() >5){
                gehaald = "Ja";
                score = Color.GREEN;
            }
            tableBuilder.addRow(
                    Row.builder()
                            .add(TextCell.builder()
                                    .text(scan.getTitle())
                                    .borderWidth(1F)
                                    .build())
                            .add(TextCell.builder()
                                    .text(gehaald)
                                    .borderWidth(1F)
                                    .textColor(score)
                                    .build())
                            .add(TextCell.builder()
                                    .text("result")
                                    .borderWidth(1F)
                                    .build())
                            .build());
        });


        return tableBuilder.build();
    }

}