//package it.academy.fitness_studio.service;
//
//import it.academy.fitness_studio.core.dto.user.UserDTO;
//import it.academy.fitness_studio.core.dto.user.UserModel;
//import it.academy.fitness_studio.core.enums.UserRole;
//import it.academy.fitness_studio.core.enums.UserStatus;
//import it.academy.fitness_studio.core.exception.UserNotFoundException;
//import it.academy.fitness_studio.dao.api.IUserDao;
//import it.academy.fitness_studio.entity.RoleEntity;
//import it.academy.fitness_studio.entity.StatusEntity;
//import it.academy.fitness_studio.entity.UserEntity;
//import it.academy.fitness_studio.service.api.IUserService;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.internal.verification.VerificationModeFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.Instant;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserServiceIntegrationTest {
//
////    @TestConfiguration
////    static class UserServiceTestContextConfiguration {
////        @Bean
////        public IUserService userService(IUserDao dao,
////                                        ConversionService conversionService){
////            return new UserService(dao, conversionService);
////        }
////    }
//
//        @Autowired
//        private IUserService userService;
//
//        @MockBean
//        private IUserDao dao;
//
//        @Before
//        public void setUp() {
//            UserEntity firstUser = new UserEntity();
//            UUID firstUserUUID = UUID.fromString("11941a2b-d0b3-4752-a706-ed959ed02533");
//            firstUser.setUuid(firstUserUUID);
//            Instant dtCreated = Instant.ofEpochSecond(111111111111L);
//            Instant dtUpdated = dtCreated;
//            firstUser.setDtCreate(dtCreated);
//            firstUser.setDtUpdate(dtUpdated);
//            firstUser.setStatus(new StatusEntity(UserStatus.ACTIVATED));
//            firstUser.setRole(new RoleEntity(UserRole.USER));
//            firstUser.setPassword("12345678");
//            firstUser.setMail("Motto.first@fr.com");
//            firstUser.setFio("First first");
//
//            UserEntity secondUser = new UserEntity();
//            UUID secondUserUUID = UUID.fromString("11941a2b-d0b3-4752-a706-ed959ed02533");
//            secondUser.setUuid(secondUserUUID);
//            Instant dt = Instant.ofEpochSecond(111111111122L);
//            Instant dtup = dt;
//            secondUser.setDtCreate(dt);
//            secondUser.setDtUpdate(dtup);
//            secondUser.setStatus(new StatusEntity(UserStatus.ACTIVATED));
//            secondUser.setRole(new RoleEntity(UserRole.USER));
//            secondUser.setPassword("12345678");
//            secondUser.setMail("Motto.second@fr.com");
//            secondUser.setFio("Second Second");
//            Mockito.when(dao.save(secondUser)).thenReturn(secondUser);
//            List<UserEntity> userEntities = Arrays.asList(firstUser, secondUser);
//            Mockito.when(dao.findByMail(firstUser.getMail()))
//                    .thenReturn(Optional.of(firstUser));
//            Mockito.when(dao.findByMail(secondUser.getMail()))
//                    .thenReturn(Optional.of(secondUser));
//            Mockito.when(dao.findByMail("wrong_mail"))
//                    .thenThrow(new UserNotFoundException("There is no user with such mail"));
//            Mockito.when(dao.findById(firstUser.getUuid()))
//                    .thenReturn(Optional.of(firstUser));
//            Mockito.when(dao.findAll())
//                    .thenReturn(userEntities);
//            Mockito.when(dao.findById(UUID.fromString("5541a2b-d0b3-4752-a706-ed959ed02533")))
//                    .thenThrow(new UserNotFoundException("There is no user with such mail"));
//
//        }
//
//        @Test
//        public void whenValidMail_thenUserShouldBeFound() {
//            String mail = "Motto.first@fr.com";
//            UserModel user = userService.getUser(mail);
//            assertThat(user.getMail()).isEqualTo(mail);
//            verifyFindByMailIsCalledOnce("Motto.first@fr.com");
//        }
//
//        @Test
//        public void whenInvalidMail_thenUserShouldNotBeFound() {
//            Throwable exception = assertThrows(UserNotFoundException.class,
//                    () -> userService.getUser("wrong_mail"));
//            assertEquals("There is no user with such mail", exception.getMessage());
//            verifyFindByMailIsCalledOnce("wrong_mail");
//        }
//
//        @Test
//        public void whenValidId_thenUserShouldBeFound() {
//            UUID uuid = UUID.fromString("11941a2b-d0b3-4752-a706-ed959ed02533");
//            UserModel user = userService.getUser(uuid);
//            assertThat(user.getUuid()).isEqualTo(uuid);
//            verifyFindByIdIsCalledOnce();
//        }
//
//        @Test
//        public void whenInValidId_thenUserShouldNotBeFound() {
//            UUID wrongUuid = UUID.fromString("5541a2b-d0b3-4752-a706-ed959ed02533");
//            Throwable exception = assertThrows(UserNotFoundException.class,
//                    () -> userService.getUser(wrongUuid));
//            assertEquals("There is no user with such mail", exception.getMessage());
//        }
//
//        private void verifyFindByMailIsCalledOnce(String name) {
//            Mockito.verify(dao, VerificationModeFactory.times(1))
//                    .findByMail(name);
//            Mockito.reset(dao);
//        }
//
//        private void verifyFindByIdIsCalledOnce() {
//            Mockito.verify(dao, VerificationModeFactory
//                            .times(1))
//                    .findById(Mockito.any(UUID.class));
//            Mockito.reset(dao);
//        }
//
//        private void verifyFindAllUsersIsCalledOnce() {
//            Mockito.verify(dao, VerificationModeFactory.times(1))
//                    .findAll();
//            Mockito.reset(dao);
//        }
//}
////        UserDTO userDTO = new UserDTO("hello.hello@test.com",
////                "Hello Hello",
////                "USER",
////                "ACTIVATED",
////                "12345678");
