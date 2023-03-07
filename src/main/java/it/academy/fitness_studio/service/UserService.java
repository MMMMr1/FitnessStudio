package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.dto.pages.Pages;
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
    public void create(@Validated UserDTO user) {
        checkDoubleMail(user);
        if (!conversionService.canConvert(UserDTO.class, UserEntity.class)) {
            throw new RuntimeException("Can not convert UserDTO.class to UserEntity.class");
        }
        UserEntity userEntity = conversionService.convert(user, UserEntity.class);
        userEntity.setUuid(UUID.randomUUID());
//        Instant dtCreated = Instant.ofEpochMilli(Instant.now().toEpochMilli());
        Instant dtCreated = Instant.now();
        Instant dtUpdated = dtCreated;
        userEntity.setDtCreate(dtCreated);
        userEntity.setDtUpdate(dtUpdated);
        dao.save(userEntity);
    }

    @Override
    public void update(UUID id, Instant version, @Validated UserDTO user) {
        UserEntity userEntity = dao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("There is no user with such id"));
//        if (!version.equals(userEntity.getDtUpdate())) {
        if(version.toEpochMilli() != userEntity.getDtUpdate().toEpochMilli()){
            throw new InvalidVersionException("Invalid version");
        }
            userEntity.setFio(user.getFio());
            userEntity.setMail(user.getMail());
            userEntity.setStatus(new StatusEntity(user.getStatus()));
            userEntity.setPassword(user.getPassword());
            userEntity.setRole(new RoleEntity(user.getRole()));
            dao.save(userEntity);
    }

    public Pages<UserModel> getPageUser(Pageable paging) {
        Page<UserEntity> all = dao.findAll(paging);
        if (!conversionService.canConvert(UserEntity.class, UserModel.class)) {
            throw new RuntimeException("Can not convert UserEntity.class to UserModel.class");
        }
        List<UserModel> content = all.getContent().stream()
                .map(s -> conversionService.convert(s, UserModel.class))
                .collect(Collectors.toList());
        return Pages.PagesBuilder.<UserModel>create()
                .setNumber(all.getNumber())
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
        if (!conversionService.canConvert(UserEntity.class, UserModel.class)) {
            throw new RuntimeException("Can not convert UserEntity.class to UserModel.class");
        }
        return conversionService.convert(userEntity, UserModel.class);
    }

    @Override
    public UserModel getUser(String mail) {
        UserEntity userEntity = dao.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("There is no user with such mail"));
        if (!conversionService.canConvert(UserEntity.class, UserModel.class)) {
            throw new RuntimeException("Can not convert UserEntity.class to UserModel.class");
        }
        return conversionService.convert(userEntity, UserModel.class);
    }

    private void checkDoubleMail(UserDTO user) {
        String mail = user.getMail(); 
        if (dao.findByMail(mail).isPresent()) {
            throw new UserAlreadyExistException("User with this mail is already registered");
        }
    }
}
