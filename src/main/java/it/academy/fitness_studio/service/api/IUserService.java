package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IUserService {
    void create(UserDTO user);
    UserModel getUser(UUID id);
    void update(UUID id, Instant version, UserDTO user) throws ValidationUserException;
    Pages getPageUser(Pageable paging);
}
