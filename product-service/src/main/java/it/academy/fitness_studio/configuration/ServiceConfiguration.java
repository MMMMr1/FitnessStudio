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
    public ProductService productService(IProductDao dao,
                                         ConversionService conversionService){
        return new ProductServiceImpl(dao, conversionService);
    }
    @Bean
    public RecipeService recipeService(IRecipeDao dao,
                                       ProductService service,
                                       ConversionService conversionService,
                                       ValidatorRecipe validator){
        return new RecipeServiceImpl(dao, service, conversionService,validator);
    }
    @Bean
    public ValidatorRecipe validatorRecipe(ProductService service ){
        return new ValidatorRecipeImpl( service );
    }
}
