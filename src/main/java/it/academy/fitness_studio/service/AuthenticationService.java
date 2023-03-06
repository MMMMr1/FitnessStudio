package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.enums.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.core.exception.UserNotFoundException;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.entity.AuthenticationEntity;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import it.academy.fitness_studio.service.api.IEmailService;
import it.academy.fitness_studio.service.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.MailSender;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

public class AuthenticationService implements IAuthenticationService {
    private final IAuthenticationDao dao;
    private final IUserService service;
    private final IEmailService emailService;
    private ConversionService conversionService;

    public AuthenticationService(IAuthenticationDao dao,
                                 IUserService service,
                                 IEmailService emailService,
                                 ConversionService conversionService) {
        this.dao = dao;
        this.service = service;
        this.emailService = emailService;
        this.conversionService = conversionService;
    }
    @Override
    public void create(UserRegistrationDTO user) {
        service.create(new UserDTO(user.getMail(), user.getFio(), user.getPassword()));
        UserEntity userEntity = find(user.getMail());
        String code = UUID.randomUUID().toString();
        userEntity.setCode(code);
        dao.save(userEntity);
        emailService.sendSimpleMessage("maksim.maks.23@mail.ru",
                code, "hello");
    }

    @Override
    public void verify(String code,String mail) {
        UserEntity userEntity = find(mail);
        if( code.equals(userEntity.getCode())){
            userEntity.setStatus(new StatusEntity(UserStatus.ACTIVATED));
            userEntity.setCode(null);
            dao.save(userEntity);
        } else throw new ValidationUserException("Incorrect mail and code");
    }
    @Override
    public void login(@Validated UserLoginDTO user) {
        UserEntity userEntity = find(user.getMail());
        if(!userEntity.getPassword().equals(user.getPassword())){
            throw new ValidationUserException("Incorrect mail and password");
        }
    }
    private UserEntity find(String mail){
        return dao.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("User with this mail is not registered"));
    }
}
