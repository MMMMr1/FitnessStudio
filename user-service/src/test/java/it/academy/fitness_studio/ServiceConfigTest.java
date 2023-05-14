//package it.academy.fitness_studio;
//
//import it.academy.fitness_studio.core.converter.*;
//import it.academy.fitness_studio.dao.api.AuthenticationDao;
//import it.academy.fitness_studio.dao.api.UserDao;
//import it.academy.fitness_studio.service.api.AuthenticationService;
//import it.academy.fitness_studio.service.api.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
////@ActiveProfiles("module-test")
//public class ServiceConfigTest {
//    @Autowired
//    private ApplicationContext applicationContext;
//    @Test
//    public void givenBeanWhenSearchingInAppContextThenFindThem(){
//        assertNotNull(applicationContext.getBean(UserService.class));
//        assertNotNull(applicationContext.getBean(AuthenticationService.class));
//    }
//    @Test
//    public void givenBeanDaoWhenSearchingInAppContextThenFindThem(){
//        assertNotNull(applicationContext.getBean(AuthenticationDao.class));
//        assertNotNull(applicationContext.getBean(UserDao.class));
//    }
//    @Test
//    public void givenScannedScopeComponent_whenSearchingInApplicationContext_thenFindIt() {
//        assertNotNull(applicationContext.getBean(UserDTOToUserEntity.class));
//        assertNotNull(applicationContext.getBean(UserEntityToUserModel.class));
//        assertNotNull(applicationContext.getBean(ConversionService.class));
//    }
//}
