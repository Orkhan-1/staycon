package com.staycon.config;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Created by Orkhan Gasanov on May 07, 2018
 */

@Configuration
public class CommonConfiguration {

    @Bean
    public EmbeddedServletContainerCustomizer errorHandler() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
                configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/accessForbidden"));
            }
        };
    }

    @Bean
    public PolicyFactory getHtmlPolicy() {
        return new HtmlPolicyBuilder()
                .allowCommonBlockElements()
                .allowCommonBlockElements()
                .toFactory();
    }

}