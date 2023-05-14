package it.academy.fitness_studio.integration.service;

import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.integration.IntegrationTestBase;
import it.academy.fitness_studio.service.AuthenticationServiceImpl;
import it.academy.fitness_studio.service.api.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


class UserServiceTest extends IntegrationTestBase {
    @Autowired
    private UserService service;
    private static final Logger logger =
            LoggerFactory.getLogger(AuthenticationServiceImpl.class);


    @Test
    void checkCreate() {
        UserModel user = service.getUser(UUID.fromString("66c83144-c4ca-4e88-a5b1-4c94c0b91449"));

        logger.info("-----------------> "+user.getName());
//        var user = new UserDTO("mail.mail@mail.com",
//                "Mail Mail",
//                "password");
//        service.create(user);
//        System.out.println();

    }
}