package it.academy.fitness_studio.config;


import it.academy.fitness_studio.core.converter.*;
import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.service.ProductService;
import it.academy.fitness_studio.service.RecipeService;
import it.academy.fitness_studio.service.UserService;
import it.academy.fitness_studio.service.AuthenticationService;
import it.academy.fitness_studio.service.api.IProductService;
import it.academy.fitness_studio.service.api.IRecipeService;
import it.academy.fitness_studio.service.api.IUserService;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public IUserService userService(IUserDao dao, CustomUserEntityConverter converterUserEntity,
                                     CustomUserDTOConverter converterUserDTO){
        return new UserService(dao, converterUserEntity, converterUserDTO);
    }
    @Bean
    public IAuthenticationService authenticationService(IAuthenticationDao dao, IUserService service){
        return new AuthenticationService(dao, service);
    }
    @Bean
    public IProductService productService(IProductDao dao,
                                          CustomProductDTOConverter converterProductDTO,
                                          CustomProductEntityToModelConverter converterProductEntity ){
        return new ProductService(dao,converterProductDTO,converterProductEntity);
    }
    @Bean
    public IRecipeService recipeService(IRecipeDao dao, IProductService service,
                                        CustomProductEntityToModelConverter converterProductEntity,
                                        CustomProductModelToEntityConverter modelToEntityConverter){
        return new RecipeService(dao, service, converterProductEntity, modelToEntityConverter);
    }

}
