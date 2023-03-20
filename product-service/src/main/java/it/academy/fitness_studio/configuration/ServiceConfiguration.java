package it.academy.fitness_studio.configuration;

import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.service.*;
import it.academy.fitness_studio.service.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class ServiceConfiguration {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
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
}
