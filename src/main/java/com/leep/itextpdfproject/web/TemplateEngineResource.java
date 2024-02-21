package com.leep.itextpdfproject.web;

import com.leep.itextpdfproject.config.ThymeleafConfig;
import com.leep.itextpdfproject.vo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/api")
public class TemplateEngineResource {

    @RequestMapping("/html-viewer")
    public ResponseEntity getHtmlViwer() {
        String htmlContent = null;
        try {
            ApplicationContext context =
                    new AnnotationConfigApplicationContext(ThymeleafConfig.class);

            List orderItemList = Arrays.asList(
                    new Item("생수", 12000),
                    new Item("티슈", 3500),
                    new Item("맥주", 9800));

            final Context ctx = new Context(Locale.KOREA);
            ctx.setVariable("name", "Brady");
            ctx.setVariable("orderDate", new Date());
            ctx.setVariable("orderItemList", orderItemList);

            TemplateEngine templateEngine = context.getBean(TemplateEngine.class);
            htmlContent = templateEngine.process("html/email-template", ctx);
        } catch (BeansException e) {
            log.error("message : {}", e.getMessage());
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(htmlContent, HttpStatus.OK);
    }

}
