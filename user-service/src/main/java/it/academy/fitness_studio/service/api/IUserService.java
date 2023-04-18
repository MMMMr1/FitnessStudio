package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IUserService {
    UserModel create(UserDTO user);
    UserModel getUser(UUID id);
    UserModel update(UUID id, Instant version, UserDTO user);
    Pages <UserModel> getPageUser(Pageable paging);
}
