package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.audit.AuditCode;
import it.academy.fitness_studio.audit.Auditable;
import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IUserService {

    UserModel create(UserDTO user);
    UserModel getUser(UUID id);

    UserModel update(UUID id, Instant version, UserDTO user) throws ValidationUserException;
    Pages <UserModel> getPageUser(Pageable paging);
}
