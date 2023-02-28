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
import org.springframework.core.convert.ConversionService;

@Configuration
public class ServiceConfig {
    @Bean
    public IUserService userService(IUserDao dao){
        return new UserService(dao);
    }
    @Bean
    public IAuthenticationService authenticationService(IAuthenticationDao dao,
                                                        IUserService service){
        return new AuthenticationService(dao, service);
    }
    @Bean
    public IProductService productService(IProductDao dao){
        return new ProductService(dao);
    }
    @Bean
    public IRecipeService recipeService(IRecipeDao dao,
                                        IProductService service){
        return new RecipeService(dao, service);
    }

}
