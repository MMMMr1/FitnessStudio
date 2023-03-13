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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class ServiceConfiguration {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public IUserService userService(IUserDao dao,
                                    ConversionService conversionService,
                                    PasswordEncoder encoder
                                    ){
        return new UserService(dao, conversionService, encoder);
    }

    @Bean
    public IAuthenticationService authenticationService(IAuthenticationDao dao,
                                                        IUserService service,
                                                        IEmailService emailService,
                                                        ConversionService conversionService,
                                                        BCryptPasswordEncoder encoder){
        return new AuthenticationService(dao, service, emailService, conversionService, encoder);
    }
//    @Bean
//    public IProductService productService(IProductDao dao,
//                                          ConversionService conversionService){
//        return new ProductService(dao, conversionService);
//    }
//    @Bean
//    public IRecipeService recipeService(IRecipeDao dao,
//                                        IProductService service,
//                                        ConversionService conversionService,
//    IValidatorRecipe validator){
//        return new RecipeService(dao, service, conversionService,validator);
//    }
//    @Bean
//    public IValidatorRecipe validatorRecipe(IProductService service ){
//        return new ValidatorRecipe( service );
//    }
    @Bean
    public IEmailService emailService(JavaMailSender emailSender,SimpleMailMessage template,
                                      SpringTemplateEngine thymeleafTemplateEngine){
        return new EmailService(emailSender,template,thymeleafTemplateEngine);
    }
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource, PasswordEncoder encoder) {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        return manager;
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.ru");
        mailSender.setPort(465);

        mailSender.setUsername("maksim.maks.23@mail.ru");
        mailSender.setPassword("DhrzvYXCZRL5RdPwTQbA");

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
