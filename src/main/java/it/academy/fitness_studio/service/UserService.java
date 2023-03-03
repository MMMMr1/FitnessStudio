package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.core.enums.UserStatus;
import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.exception.InvalidVersionException;
import it.academy.fitness_studio.core.exception.UserAlreadyExistException;
import it.academy.fitness_studio.core.exception.UserNotFoundException;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.entity.RoleEntity;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserService implements IUserService {
    private final IUserDao dao;

    private ConversionService conversionService;
    public UserService(IUserDao dao, ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }
    @Override
    public void create(@Validated UserDTO user)  {
        checkDoubleMail(user);
        if (!conversionService.canConvert(UserDTO.class, UserEntity.class)) {
            throw new RuntimeException("Can not convert UserDTO.class to UserEntity.class");
        }
        UserEntity userEntity = conversionService.convert(user,UserEntity.class);
        userEntity.setUuid(UUID.randomUUID());
        Instant now = Instant.now();
        userEntity.setDtCreate(now);
        userEntity.setDtUpdate(now);
        dao.save(userEntity);
    }
    @Override
    public void update(UUID id, Instant version, @Validated  UserDTO user)  {
        UserEntity userEntity = dao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("There is no user with such id"));
        if ( version.toEpochMilli() == userEntity.getDtUpdate().toEpochMilli()){
            userEntity.setFio(user.getFio());
            userEntity.setMail(user.getMail());
//            String status = user.getStatus();
//            userEntity.setStatus(new StatusEntity(UserStatus.valueOf(status)));
         userEntity.setStatus(new StatusEntity(user.getStatus()));
            userEntity.setPassword(user.getPassword());
            userEntity.setRole(new RoleEntity(user.getRole()));
            dao.save(userEntity);
        }  else throw new InvalidVersionException("Invalid version");
    }
    public Pages getPageUser(Pageable paging) {
        Page<UserEntity> all = dao.findAll(paging);
        if (!conversionService.canConvert(UserEntity.class,UserModel.class)) {
            throw new RuntimeException("Can not convert UserEntity.class to UserModel.class");
        }
        List<UserModel> content = all.getContent().stream()
                .map(s -> conversionService.convert(s,UserModel.class))
                .collect(Collectors.toList());
        return  Pages.PagesBuilder.create().setNumber(all.getNumber())
                .setContent(content)
                .setFirst(all.isFirst())
                .setLast(all.isLast())
                .setNumberOfElements(all.getNumberOfElements())
                .setSize(all.getSize())
                .setTotalPages(all.getTotalPages())
                .setTotalElements(all.getTotalElements())
                .build();
    }
    @Override
    public UserModel getUser(UUID id) {
        UserEntity userEntity = dao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("There is no user with such id"));
        if (!conversionService.canConvert(UserEntity.class,UserModel.class)) {
            throw new RuntimeException("Can not convert UserEntity.class to UserModel.class");
        }
        return  conversionService.convert(userEntity,UserModel.class);
    }
    private void checkDoubleMail(UserDTO user) {
        String mail = user.getMail();
        if (dao.findByMail(mail) != null) {
            throw new UserAlreadyExistException("User with this mail is already registered");
        }
    }


//    private void validate(UserDTO user)  throws ValidationUserException{
////        Throwable throwable = new Throwable();
//        ValidationUserException validationUserException = new ValidationUserException();
//
////        String mail = user.getMail();
////        if (mail == null || mail.isBlank()) {
////           validationUserException.addSuppressed(new ValidationUserException("Mail not entered"));
////        }
////
////        if (!mail.matches(
////                "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
////                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
////        )) {
////            validationUserException.addSuppressed(new ValidationUserException("Wrong format of mail"));
////        }
////        if (mail.length() < 6) {
////            validationUserException.addSuppressed(new ValidationUserException("login can not be less then 6 symbols"));
////        }
////        if (mail.length() > 30) {
////            validationUserException.addSuppressed(new ValidationUserException("login cannot be longer then 30 symbols"));
////        }
//
//        String password = user.getPassword();
////
////        if (password == null || password.isBlank()){
////            validationUserException.addSuppressed(new ValidationUserException("Password is not entered"));
////        }
//        if (password.length()<8 || password.length()>30){
//            validationUserException.addSuppressed(new ValidationUserException("Password can not be less then 8 symbols")) ;
//        }
//
//        String fullName = user.getFio();
//
//        if (fullName == null || fullName.isBlank()){
//            validationUserException.addSuppressed(new ValidationUserException("Full name is not entered"));
//        }
//
//        if (fullName.length()<=4){
//            validationUserException.addSuppressed(new ValidationUserException("Full name cannot be less then 4 symbols"));
//        }
//        if(!fullName.matches("([A-Za-z]+) ([A-Za-z]+)|([А-Яа-я]+ [А-Яа-я]+)")){
//            validationUserException.addSuppressed(new ValidationUserException("Write correct full name"));
//        }
////     todo   Status Role don't check in this method?
//        if (validationUserException.getSuppressed().length >0) {
//            throw validationUserException;
//        }
//    }

}
