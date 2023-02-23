package it.academy.fitness_studio.service.api;



import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.UserDTO;
import it.academy.fitness_studio.core.dto.UserModel;

import java.time.Instant;
import java.util.UUID;

public interface IUserService {

    void create(UserDTO user);
    UserModel getUser(UUID id);
    void update(UUID id, Instant version, UserDTO user);
    Pages getPageUser(int page, int size);



}
