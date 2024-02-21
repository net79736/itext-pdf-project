package com.leep.itextpdfproject.service.impl;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;
import com.leep.itextpdfproject.service.ITextPdfService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class iTextPdfServiceImpl implements ITextPdfService {

    @Override
    public ByteArrayInputStream createPdf(String html) throws IOException {
        String FONT = "fonts/NanumBarunGothic.ttf";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ConverterProperties properties = new ConverterProperties();
        FontProvider fontProvider = new DefaultFontProvider(false, false, false);

        FontProgram fontProgram = FontProgramFactory.createFont(FONT);
        fontProvider.addFont(fontProgram);
        properties.setFontProvider(fontProvider);

        HtmlConverter.convertToPdf(html, outputStream, properties);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream;
    }

}
