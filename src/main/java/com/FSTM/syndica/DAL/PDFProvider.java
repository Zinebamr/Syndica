package com.FSTM.syndica.DAL;

import com.FSTM.syndica.Model.Reunion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PDFProvider {
    public void generate(Reunion reunion) throws DocumentException {
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String outputPath = "Document/invitation/invitation_" + formattedDate + ".pdf";
        File outputDir = new File("Document/invitation");
        outputDir.mkdirs(); // Create directories if they don't exist

        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));

            // Open the document
            document.open();

            // Create a font for the text
            BaseFont baseFont = BaseFont.createFont("Helvetica", BaseFont.CP1252, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12, Font.BOLD);
            String text  = "You are invited to the reunion "+ reunion.titre
                                +" sous le titre : " + reunion.titre
                                +" pour ordere de : " + reunion.ordre
                                +" date : " + reunion.date.toString();
            // Add a paragraph with the invitation text
            Paragraph paragraph = new Paragraph(text, font);
            document.add(paragraph);

            // Close the document
            document.close();

            // Open the output directory
            openOutputDirectory(outputDir);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openOutputDirectory(File outputDir) throws IOException {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            Desktop.getDesktop().open(outputDir);
        }
    }
}

