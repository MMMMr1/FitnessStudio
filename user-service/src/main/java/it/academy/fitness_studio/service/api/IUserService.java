package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.Instant;
import java.util.UUID;

public interface IUserService extends UserDetailsService {
    void create(UserDTO user);
    UserModel getUser(UUID id);
    UserModel getUser(String mail);
    void update(UUID id, Instant version, UserDTO user) throws ValidationUserException;
    Pages <UserModel> getPageUser(Pageable paging);
}
