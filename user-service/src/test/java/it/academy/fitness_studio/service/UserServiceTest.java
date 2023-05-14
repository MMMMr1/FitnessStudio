//package it.academy.fitness_studio.service;
//
//import it.academy.fitness_studio.core.dto.user.UserModel;
//import it.academy.fitness_studio.core.enums.UserRole;
//import it.academy.fitness_studio.core.enums.UserStatus;
//import it.academy.fitness_studio.core.exception.UserNotFoundException;
//import it.academy.fitness_studio.dao.api.IUserDao;
//import it.academy.fitness_studio.entity.RoleEntity;
//import it.academy.fitness_studio.entity.StatusEntity;
//import it.academy.fitness_studio.entity.UserEntity;
////import it.academy.fitness_studio.integration.annotaton.IntegrationTest;
//import it.academy.fitness_studio.service.api.IUserService;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.internal.verification.VerificationModeFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
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
//import static org.mockito.Mockito.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("module-test")
//public class UserServiceTest {
//        @Autowired
//        private IUserService userService;
//        @MockBean
//        private IUserDao dao;
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
//            when(dao.save(secondUser)).thenReturn(secondUser);
//            List<UserEntity> userEntities = Arrays.asList(firstUser, secondUser);
//            when(dao.findByMail(firstUser.getMail()))
//                    .thenReturn(Optional.of(firstUser));
//            when(dao.findByMail(secondUser.getMail()))
//                    .thenReturn(Optional.of(secondUser));
//            when(dao.findByMail("wrong_mail"))
//                    .thenThrow(new UserNotFoundException("There is no user with such mail"));
//            when(dao.findById(firstUser.getUuid()))
//                    .thenReturn(Optional.of(firstUser));
//            when(dao.findAll())
//                    .thenReturn(userEntities);
//            when(dao.findById(UUID.fromString("5541a2b-d0b3-4752-a706-ed959ed02533")))
//                    .thenThrow(new UserNotFoundException("There is no user with such mail"));
//
//        }
//        @Test
//        public void whenValidId_thenUserShouldBeFound() {
//            UUID uuid = UUID.fromString("11941a2b-d0b3-4752-a706-ed959ed02533");
//            UserModel user = userService.getUser(uuid);
//            assertThat(user.getUuid()).isEqualTo(uuid);
//            verifyFindByIdIsCalledOnce();
//        }
//        @Test
//        public void whenInValidId_thenUserShouldNotBeFound() {
//            UUID wrongUuid = UUID.fromString("5541a2b-d0b3-4752-a706-ed959ed02533");
//            Throwable exception = assertThrows(UserNotFoundException.class,
//                    () -> userService.getUser(wrongUuid));
//            assertEquals("There is no user with such mail", exception.getMessage());
//        }
//        private void verifyFindByMailIsCalledOnce(String name) {
//            verify(dao, VerificationModeFactory.times(1))
//                    .findByMail(name);
//            reset(dao);
//        }
//        private void verifyFindByIdIsCalledOnce() {
//            verify(dao, VerificationModeFactory
//                            .times(1))
//                    .findById(any(UUID.class));
//            reset(dao);
//        }
//        private void verifyFindAllUsersIsCalledOnce() {
//            verify(dao, VerificationModeFactory.times(1))
//                    .findAll();
//            reset(dao);
//        }
//}
