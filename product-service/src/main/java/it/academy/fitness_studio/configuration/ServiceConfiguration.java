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

}
