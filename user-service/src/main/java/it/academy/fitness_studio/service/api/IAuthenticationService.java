package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;

import java.util.UUID;

public interface IAuthenticationService {
    void create(UserRegistrationDTO user);
    void verify(String code,String mail);
    UserModel login(UserLoginDTO user);
    UserModel getUser(String mail);
}