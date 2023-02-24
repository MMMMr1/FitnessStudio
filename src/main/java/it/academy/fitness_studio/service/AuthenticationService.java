package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import it.academy.fitness_studio.service.api.IUserService;

public class AuthenticationService implements IAuthenticationService {
    private final IAuthenticationDao dao;
    private final IUserService service;

    public AuthenticationService(IAuthenticationDao dao, IUserService service) {
        this.dao = dao;
        this.service = service;
    }
    @Override
    public void create(UserRegistrationDTO user) {
        //        Validate
//        check double post
//        вызываем метод сайв user seervise
        service.create(new UserDTO(user.getMail(), user.getFio(), user.getPassword()));
//        UserSavedDTO userSavedDTO = new UserSavedDTO(user.getMail(),
//                user.getFio(),
//                user.getPassword());
//
//        UserEntity userEntity = new UserEntity(userSavedDTO.getUuid(),
//                userSavedDTO.getDtCreate(),
//                userSavedDTO.getDtUpdate(),
//                userSavedDTO.getMail(),
//                userSavedDTO.getFio(),
//                new RoleEntity(userSavedDTO.getRole()),
//                new StatusEntity(userSavedDTO.getStatus()),
//                userSavedDTO.getPassword()
//        );
//        userEntity.setCode(UUID.randomUUID().toString());
////
//        dao.save(userEntity);
    }

    @Override
    public void verify(String mail, String code) {
        UserEntity byMail = dao.findByMail(mail);
        if(code.equals(byMail.getCode())){
            byMail.setStatus(new StatusEntity(UserStatus.ACTIVATED));
            byMail.setCode(null);
            dao.save(byMail);
        } else throw new IllegalArgumentException("Code is incorrect");
    }

    @Override
    public void login(UserLoginDTO user) {
        UserEntity byMail = dao.findByMail(user.getMail());
        if(!byMail.getPassword().equals(user.getPassword())){
            throw new IllegalArgumentException("Incorrect mail and password");
        }
    }
}
