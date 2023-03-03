package it.academy.fitness_studio.config;



import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.service.*;
import it.academy.fitness_studio.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.convert.ConversionService;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ServiceConfig {
    @Bean
    public IUserService userService(IUserDao dao,
                                    ConversionService conversionService){
        return new UserService(dao, conversionService);
    }
    @Bean
    public IAuthenticationService authenticationService(IAuthenticationDao dao,
                                                        IUserService service){
        return new AuthenticationService(dao, service);
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
