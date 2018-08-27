package com.staycon.service.impl;

import com.staycon.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class DefaultEmailService implements EmailService {

    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.enable}")
    private Boolean enabled;

    @Value("${site.url}")
    private String url;

    @Autowired
    public DefaultEmailService (TemplateEngine templateEngine) {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(templateResolver);
        this.templateEngine = templateEngine;
    }

    private void sendMessage (MimeMessagePreparator preparator) {
        if (enabled) {
            mailSender.send(preparator);
        }

    }

    @Async
    @Override
    public void sendVerificationEmail(String emailAdress, String token) {

        Context context = new Context ();
        context.setVariable("name", "Orkhan");
        context.setVariable("url", url);
        context.setVariable("token", token);
        String emailBody = templateEngine.process("verifyemail", context);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setTo(emailAdress);
                messageHelper.setFrom(new InternetAddress("no-reply@staycon.com"));
                messageHelper.setSubject("Please Verify Email Adress");
                messageHelper.setSentDate(new Date());
                messageHelper.setText(emailBody);

            }
        };

        sendMessage(preparator);
    }
}
