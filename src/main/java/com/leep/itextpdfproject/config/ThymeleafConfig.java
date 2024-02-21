package com.leep.itextpdfproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Collections;

@Configuration
public class ThymeleafConfig {

    private static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";

    @Bean
    public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        // 템플릿 Resolver의 우선 순위. 여러 Resolver가 존재하는 경우, 우선 순위에 따라 어떤 Resolver가 먼저 사용될지 결정된다. 이 경우, 우선순위가 1이므로 다른 Resolver보다 이 Resolver가 우선적으로 사용 됨
        templateResolver.setOrder(1);
        // Resolver가 적용되는 템플릿 경로를 지정한다. 여기서는 "html/" 디렉토리에 있는 템플릿만 Resolver가 적용한다. 다른 디렉토리에 있는 템플릿은 해당 Resolver가 처리하지 않는다.
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        // 템플릿 파일의 위치와 확장자를 지정한다. 여기서는 "/mail/" 디렉토리에 있는 HTML 파일을 템플릿으로 사용한다.
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        // 템플릿의 모드를 지정. 여기서는 HTML 모드를 사용하고 있으며, 이는 템플릿이 HTML 형식으로 작성되었음을 나타낸다.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // 템플릿의 문자 인코딩을 지정
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        // 템플릿 캐싱을 활성화 또는 비활성화. false 이므로 템플릿이 변경될 때마다 매번 새로운 템플릿을 로드한다. 이는 개발 중에 템플릿 변경을 즉시 반영하고자 할 때 유용하다.
        templateResolver.setCacheable(false);
        return templateResolver;
    }

}
