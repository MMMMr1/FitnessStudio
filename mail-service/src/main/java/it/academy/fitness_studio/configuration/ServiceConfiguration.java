package it.academy.fitness_studio.configuration;

import it.academy.fitness_studio.service.*;
import it.academy.fitness_studio.service.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.Properties;

@Configuration
public class ServiceConfiguration {

    @Bean
    public IMailService emailService(JavaMailSender emailSender,
                                     SimpleMailMessage template,
                                     SpringTemplateEngine thymeleafTemplateEngine){
        return new MailService(emailSender,template,thymeleafTemplateEngine);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.ru");
        mailSender.setPort(465);

        mailSender.setUsername("maksim.maks.23@mail.ru");
        mailSender.setPassword("n6uLLA7AmjfZb1mxwWbM");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the test email template for your email:\n%s\n");
        return message;
    }


//    @Bean
//    @Description("Thymeleaf Template Resolver")
//    public ServletContextTemplateResolver templateResolver() {
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setPrefix("/WEB-INF/views/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//
//        return templateResolver;
//    }
//    @Bean
//    @Description("Thymeleaf View Resolver")
//    public ThymeleafViewResolver viewResolver() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setOrder(1);
//        return viewResolver;
//    }
//    @Bean
//    @Description("Thymeleaf Template Engine")
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.setTemplateEngineMessageSource(messageSource());
//        return templateEngine;
//    }
//    @Bean
//    @Description("Spring Message Resolver")
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }
}
