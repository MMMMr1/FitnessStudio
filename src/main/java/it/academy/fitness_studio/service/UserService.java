package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.UserRole;
import it.academy.fitness_studio.core.UserStatus;
import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.entity.RoleEntity;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserService implements IUserService {

    private final IUserDao dao;
    @Autowired
    private ConversionService conversionService;
    public UserService(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    public void create(UserDTO user)  {
        validate(user);
        checkDoubleMail(user);
        UserEntity userEntity = conversionService.convert(user,UserEntity.class);
        if(userEntity != null &&
                userEntity.getStatus().getStatus().equals(UserStatus.WAITING_ACTIVATION)){
            userEntity.setCode(UUID.randomUUID().toString());
        }
        dao.save(userEntity);
    }



    @Override
    public void update(UUID id, Instant version, UserDTO user)  {
        validate(user);
        UserEntity userEntity = dao.findById(id)
                .orElseThrow(() -> new ValidationUserException("There is no user with such id"));
        if ( version.toEpochMilli() == userEntity.getDtUpdate().toEpochMilli()){
            userEntity.setFio(user.getFio());
            userEntity.setMail(user.getMail());
            String status = user.getStatus();
            userEntity.setStatus(new StatusEntity(UserStatus.valueOf(status)));
            userEntity.setPassword(user.getPassword());
            userEntity.setRole(new RoleEntity(UserRole.valueOf(user.getRole())));
            dao.save(userEntity);
        }  else throw new ValidationUserException("Version is not correct");
    }

    public Pages getPageUser(int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        Page<UserEntity> all = dao.findAll(paging);

        List<UserModel> content = all.getContent().stream()
                .map(s -> conversionService.convert(s,UserModel.class))
                .collect(Collectors.toList());
        return  new Pages<UserModel>(
                all.getNumber(),
                all.getSize(),
                all.getTotalPages(),
                all.getTotalElements(),
                all.isFirst(),
                all.getNumberOfElements(),
                all.isLast(),
                content);
    }
    @Override
    public UserModel getUser(UUID id) {
        UserEntity userEntity = dao.findById(id)
                .orElseThrow(() -> new ValidationUserException("There is no user with such id"));
        return  conversionService.convert(userEntity,UserModel.class);
    }
    private void validate(UserDTO user)  throws ValidationUserException{
//        Throwable throwable = new Throwable();

        String mail = user.getMail();
        if (mail == null || mail.isBlank()) {
            throw new ValidationUserException("Mail not entered");
        }

        if (!mail.matches(
                "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
//                "([a-zA-Z]+\\.?[a-zA-Z]+)@[a-zA-Z]+"
        )) {
            throw new ValidationUserException("Wrong format of mail");
        }
        if (mail.length() < 6) {
            throw new ValidationUserException("login can not be less then 6 symbols");
        }
        if (mail.length() > 30) {
            throw new ValidationUserException("login cannot be longer then 30 symbols");
        }

        String password = user.getPassword();

        if (password == null || password.isBlank()){
            throw new ValidationUserException("Password is not entered");
        }
        if (password.length()<8 || password.length()>30){
            throw new ValidationUserException("Password can not be less then 8 symbols") ;
        }

        String fullName = user.getFio();

        if (fullName == null || fullName.isBlank()){
            throw new ValidationUserException("Full name is not entered");
        }

        if (fullName.length()<=4){
         throw new ValidationUserException("Full name cannot be less then 4 symbols");
        }
        if(!fullName.matches("([A-Za-z]+) ([A-Za-z]+)|([А-Яа-я]+ [А-Яа-я]+)")){
            throw new ValidationUserException("Write correct full name");
        }
//     todo   Status Role don't check in this method?
//        if (throwable.getSuppressed().length >0) {
//            throw new IllegalArgumentException(throwable);
//        }
    }
    private void checkDoubleMail(UserDTO user) throws ValidationUserException {
        String mail = user.getMail();
        if (dao.findByMail(mail) != null) {
            throw new ValidationUserException("User with this mail is already registered");
        }
    }
}
