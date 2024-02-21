package com.leep.itextpdfproject.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface ITextPdfService {
    ByteArrayInputStream createPdf(String html) throws IOException;
}
