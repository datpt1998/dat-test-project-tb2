package com.example.Main;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class PdfTest {
    public static void main(String[] args) throws URISyntaxException, IOException {
        ClassLoader classLoader = PdfTest.class.getClassLoader();
        URL pdfUrl = classLoader.getResource("sample.pdf");
        PDDocument document = PDDocument.load(new File(pdfUrl.toURI()));
        PDFTextStripper textStripper = new PDFTextStripper();
        textStripper.setStartPage(0);
        textStripper.setEndPage(1);
        System.out.println(textStripper.getText(document));
        for(int i = 0; i<document.getPages().getCount(); i++) {
            PDResources resources = document.getPage(i).getResources();
            for(COSName cosName : resources.getXObjectNames()) {
                System.out.println("Object: "+cosName.getName());
            }
            for(COSName cosName : resources.getFontNames()) {
                System.out.println("Font: "+cosName.getName());
            }
            for(COSName cosName : resources.getPatternNames()) {
                System.out.println("Pattern: "+cosName.getName());
            }
            for(COSName cosName : resources.getPropertiesNames()) {
                System.out.println("Properties: "+cosName.getName());
            }
            for(COSName cosName : resources.getShadingNames()) {
                System.out.println("Shading: "+cosName.getName());
            }
            for(COSName cosName : resources.getColorSpaceNames()) {
                System.out.println("Color space: "+cosName.getName());
            }
            for(COSName cosName : resources.getExtGStateNames()) {
                System.out.println("ExtGState: "+cosName.getName());
            }
        }
    }
}
