package it.academy.fitness_studio.configuration;


import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.service.*;
import it.academy.fitness_studio.service.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableJpaAuditing( )
@Configuration
public class UserServiceConfiguration {
    private final IUserDao dao;
    private final ConversionService conversionService;

    public UserServiceConfiguration(IUserDao dao, ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserService userService(IUserDao dao,
                                    ConversionService conversionService,
                                    PasswordEncoder encoder
    ) {
        return new UserService(dao, conversionService, encoder);
    }

    @Bean
    public IAuthenticationService authenticationService(IAuthenticationDao dao,
                                                        IUserService service,
                                                        ConversionService conversionService,
                                                        BCryptPasswordEncoder encoder) {

        return new AuthenticationService(dao, service,
                conversionService, encoder);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> conversionService.convert(dao.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user: ")), UserModel.class);
    }
}
//    @Bean
//    public AuditorAware<UserModel> auditorProvider() {
//        return new SpringSecurityAuditorAware();
//    }
//    }
//    @Bean
//    public IMailService emailService(JavaMailSender emailSender, SimpleMailMessage template,
//                                     SpringTemplateEngine thymeleafTemplateEngine){
//        return new MailService(emailSender,template,thymeleafTemplateEngine);
//    }

//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.mail.ru");
//        mailSender.setPort(465);
//
//        mailSender.setUsername("maksim.maks.23@mail.ru");
//        mailSender.setPassword("n6uLLA7AmjfZb1mxwWbM");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtps");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }
//
//    @Bean
//    public SimpleMailMessage templateSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText("This is the test email template for your email:\n%s\n");
//        return message;
//    }


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

