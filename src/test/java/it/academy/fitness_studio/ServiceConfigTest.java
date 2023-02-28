package it.academy.fitness_studio;

import it.academy.fitness_studio.core.converter.*;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.service.UserService;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import it.academy.fitness_studio.service.api.IProductService;
import it.academy.fitness_studio.service.api.IRecipeService;
import it.academy.fitness_studio.service.api.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ServiceConfigTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void givenBeanWhenSearchingInAppContextThenFindThem(){
        assertNotNull(applicationContext.getBean(IUserService.class));
        assertNotNull(applicationContext.getBean(IProductService.class));
        assertNotNull(applicationContext.getBean(IRecipeService.class));
        assertNotNull(applicationContext.getBean(IAuthenticationService.class));
    }
    @Test
    public void givenBeanDaoWhenSearchingInAppContextThenFindThem(){
        assertNotNull(applicationContext.getBean(IProductDao.class));
        assertNotNull(applicationContext.getBean(IRecipeDao.class));
        assertNotNull(applicationContext.getBean(IAuthenticationDao.class));
        assertNotNull(applicationContext.getBean(IUserDao.class));
    }
    @Test
    public void givenScannedScopeComponent_whenSearchingInApplicationContext_thenFindIt() {
        assertNotNull(applicationContext.getBean(ProductDTOToProductEntity.class));
        assertNotNull(applicationContext.getBean(ProductEntityToProductModel.class));
        assertNotNull(applicationContext.getBean(ProductModelToProductEntity.class));
        assertNotNull(applicationContext.getBean(UserDTOToUserEntity.class));
        assertNotNull(applicationContext.getBean(UserEntityToUserModel.class));
        assertNotNull(applicationContext.getBean(ConversionService.class));
    }
}
