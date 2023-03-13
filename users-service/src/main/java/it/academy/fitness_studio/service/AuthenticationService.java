package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.enums.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import it.academy.fitness_studio.service.api.IEmailService;
import it.academy.fitness_studio.service.api.IUserService;
import it.academy.fitness_studio.web.utils.JwtTokenUtil;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

public class AuthenticationService implements IAuthenticationService  {
    private final IAuthenticationDao dao;
    private final IUserService service;
    private final IEmailService emailService;
    private ConversionService conversionService;
    private BCryptPasswordEncoder encoder;
//    private final UserDetailsManager userManager;

    public AuthenticationService(IAuthenticationDao dao,
                                 IUserService service,
                                 IEmailService emailService,
                                 ConversionService conversionService,
                                 BCryptPasswordEncoder encoder
//            ,
//                                 UserDetailsManager userManager
    ) {
        this.dao = dao;
        this.service = service;
        this.emailService = emailService;
        this.conversionService = conversionService;
        this.encoder = encoder;
//        this.userManager = userManager;
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

        if(code.equals(userEntity.getCode())){
            userEntity.setStatus(new StatusEntity(UserStatus.ACTIVATED));
            userEntity.setCode(null);
            dao.save(userEntity);
        } else throw new ValidationUserException("Incorrect mail and code");
    }
    @Override
    public String login(@Validated UserLoginDTO user) {
        UserEntity userEntity = find(user.getMail());
        if(!encoder.matches(user.getPassword(),userEntity.getPassword())){
            throw new ValidationUserException("Incorrect mail and password");
        }
        return JwtTokenUtil.generateAccessToken( userEntity );
    }
    private UserEntity find(String mail){
        return dao.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("User with this mail is not registered"));
    }


}
