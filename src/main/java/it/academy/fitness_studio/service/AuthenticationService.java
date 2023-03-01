package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.core.exception.UserNotFoundException;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import it.academy.fitness_studio.service.api.IUserService;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

public class AuthenticationService implements IAuthenticationService {
    private final IAuthenticationDao dao;
    private final IUserService service;

    public AuthenticationService(IAuthenticationDao dao, IUserService service) {
        this.dao = dao;
        this.service = service;
    }
    @Override
    public void create(UserRegistrationDTO user) {
        service.create(new UserDTO(user.getMail(), user.getFio(), user.getPassword()));
    }

    @Override
    public void verify(String code,String mail) {
        checkMail(mail);
        UserEntity byMail = dao.findByMail(mail);
        if(byMail !=null && code.equals(byMail.getCode())){
            byMail.setStatus(new StatusEntity(UserStatus.ACTIVATED));
            byMail.setCode(null);
            dao.save(byMail);
        } else throw new ValidationUserException("Incorrect mail and code");
    }
    @Override
    public void login(@Validated UserLoginDTO user) {
        UserEntity byMail = dao.findByMail(user.getMail());
        if(byMail == null || !byMail.getPassword().equals(user.getPassword())){
            throw new ValidationUserException("Incorrect mail and password");
        }
    }
    private void checkMail(String mail){
        if (dao.findByMail(mail) == null) {
            throw new UserNotFoundException("User with this mail is not registered");
        }
    }
}
