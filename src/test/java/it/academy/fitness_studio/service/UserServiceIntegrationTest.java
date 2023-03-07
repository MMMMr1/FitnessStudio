//package it.academy.fitness_studio.service;
//
//import it.academy.fitness_studio.dao.api.IUserDao;
//import it.academy.fitness_studio.service.api.IUserService;
////import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//public class UserServiceIntegrationTest {
//
//    @TestConfiguration
//    static class UserServiceTestContextConfiguration {
//        @Bean
//        public IUserService userService(IUserDao dao,
//                                        ConversionService conversionService){
//            return new UserService(dao, conversionService);
//        }
//    }
//
//    @Autowired
//    private IUserService userService;
//
//    @MockBean
//    private IUserDao dao;
//    @Before
//    public void setUp() {
////        Employee alex = new Employee("alex");
////
////        Mockito.when(dao.save(alex.getName()))
////                .thenReturn(alex);
//    }
//
//    // write test cases here
//}
