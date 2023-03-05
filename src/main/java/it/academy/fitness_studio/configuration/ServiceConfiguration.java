package it.academy.fitness_studio.configuration;



import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.service.*;
import it.academy.fitness_studio.service.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Properties;

@Configuration
public class ServiceConfiguration {
    @Bean
    public IUserService userService(IUserDao dao,
                                    ConversionService conversionService){
        return new UserService(dao, conversionService);
    }
    @Bean
    public IAuthenticationService authenticationService(IAuthenticationDao dao,
                                                        IUserService service,
                                                        IEmailService emailService,
                                                        ConversionService conversionService){
        return new AuthenticationService(dao, service, emailService, conversionService);
    }
    @Bean
    public IProductService productService(IProductDao dao,
                                          ConversionService conversionService){
        return new ProductService(dao, conversionService);
    }
    @Bean
    public IRecipeService recipeService(IRecipeDao dao,
                                        IProductService service,
                                        ConversionService conversionService,
    IValidatorRecipe validator){
        return new RecipeService(dao, service, conversionService,validator);
    }
    @Bean
    public IValidatorRecipe validatorRecipe(IProductService service ){
        return new ValidatorRecipe( service );
    }
    @Bean
    public IEmailService emailService(JavaMailSender emailSender,SimpleMailMessage template,
                                      SpringTemplateEngine thymeleafTemplateEngine){
        return new EmailService(emailSender,template,thymeleafTemplateEngine);
    }


    @Bean
    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost(mailServerHost);
//        mailSender.setPort(mailServerPort);
//
//        mailSender.setUsername(mailServerUsername);
//        mailSender.setPassword(mailServerPassword);
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", mailServerAuth);
//        props.put("mail.smtp.starttls.enable", mailServerStartTls);
//        props.put("mail.debug", "true");
//
//        return mailSender;
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.ru");
        mailSender.setPort(465);

        mailSender.setUsername("maksim.maks.23@mail.ru");
        mailSender.setPassword("gGhRkGnRtLwpk3QCpqr4");

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
//        ServletContextTemplateResolver templateResolver =
//                new ServletContextTemplateResolver();
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