package com.example.Main;

import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfResources;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

public class TestPdfItext {
  public static void main(String[] args) throws IOException, URISyntaxException {
    URL fileUrl = TestPdfItext.class.getClassLoader().getResource("sample.pdf");
    PdfDocument pdfDocument = new PdfDocument(new PdfReader(new File(fileUrl.toURI())));
    for(int i = 0; i < pdfDocument.getNumberOfPages(); i++) {
      PdfPage pdfPage = pdfDocument.getPage(i + 1);
      PdfResources resources = pdfPage.getResources();
      Set<PdfName> pdfNames = resources.getResourceNames();
      for(PdfName pdfName : pdfNames) {
        System.out.println(pdfName.toString());
        PdfDictionary resource = resources.getResource(pdfName);
        System.out.println(resource);
      }
    }
  }
}
