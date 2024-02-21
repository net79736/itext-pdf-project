package com.leep.itextpdfproject.web;

import com.leep.itextpdfproject.service.ITextPdfService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
@Controller
public class PdfController {

    @Autowired
    private ITextPdfService iTextPdfService;

    @GetMapping("/")
    public String home() {
        return "index_default";
    }

    @PostMapping(value ="/generatePdfFileUsingIText")
    public void generatePdfFileUsingIText(HttpServletResponse response, @RequestParam(name = "json_data") String contentIText) {
        log.info("generatePdfFileUsingIText contentIText : {}", contentIText);
        try {
            ByteArrayInputStream byteArrayInputStream = iTextPdfService.createPdf(contentIText);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=file.pdf");

            byte[] inSrc = contentIText.getBytes(StandardCharsets.UTF_8);
            byte[] temp = new byte[10];
            ByteArrayInputStream input = null;
            input = new ByteArrayInputStream(inSrc);

            // Read from ByteArrayInputStream and print to console
//            exampleByteArrayStreamV2();

            IOUtils.copy(byteArrayInputStream, response.getOutputStream());
        } catch (IOException e) {
            log.error("generatePdfFileUsingIText : {}", e.getMessage());
        }
    }

    @PostMapping(value ="/generatePdfFileUsingIText2")
    public void generatePdfFileUsingIText2(HttpServletResponse response, @RequestParam(name = "json_data") String contentIText) {
        log.info("generatePdfFileUsingIText contentIText : {}", contentIText);
        try {
            ByteArrayInputStream byteArrayInputStream = iTextPdfService.createPdf(contentIText);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; file.pdf");

            // Convert text to byte array using ISO-8859-1 (Latin-1) encoding
            byte[] inSrc = contentIText.getBytes(StandardCharsets.UTF_8);

            byte[] temp = new byte[10];

            ByteArrayInputStream input = null;

            input = new ByteArrayInputStream(inSrc);

            input.read(temp, 0, temp.length);   // 읽어 온 데이터를 temp에 담는다.

            System.out.println("Input Source: " + new String(inSrc, StandardCharsets.UTF_8));

            // Read from ByteArrayInputStream and print to console
            IOUtils.copy(input, response.getOutputStream());
        } catch (IOException e) {
            log.error("generatePdfFileUsingIText : {}", e.getMessage());
        } finally {
//            IOUtils.closeQuietly();
        }
    }

    public static void exampleByteArrayStream () {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;

        byte[] temp = new byte[10];

        ByteArrayInputStream input = null;

        input = new ByteArrayInputStream(inSrc);

        input.read(temp, 0, temp.length);   // 읽어 온 데이터를 temp에 담는다.

        System.out.println("Input Source: " + Arrays.toString(inSrc));
    }

    public static void exampleByteArrayStreamV2() {
        String text = "김치맛 종욱.";

        // Convert text to byte array using ISO-8859-1 (Latin-1) encoding
        byte[] inSrc = text.getBytes(StandardCharsets.UTF_8);

        byte[] outSrc = null;

        byte[] temp = new byte[10];

        ByteArrayInputStream input = null;

        input = new ByteArrayInputStream(inSrc);

        input.read(temp, 0, temp.length);   // 읽어 온 데이터를 temp에 담는다.

        System.out.println("Input Source: " + new String(inSrc, StandardCharsets.UTF_8));
    }
}