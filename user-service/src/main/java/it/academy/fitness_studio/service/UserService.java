package it.academy.fitness_studio.service;

import it.academy.fitness_studio.audit.AuditCode;
import it.academy.fitness_studio.audit.Auditable;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserService implements IUserService {
    private final IUserDao dao;

    private ConversionService conversionService;
    private PasswordEncoder encoder;

    public UserService(IUserDao dao,
                       ConversionService conversionService,
                       PasswordEncoder encoder) {
        this.dao = dao;
        this.conversionService = conversionService;
        this.encoder = encoder;
    }
    @Override
    @Auditable(AuditCode.CREATED)
    public UUID create(@Validated UserDTO user) {
        checkDoubleMail(user);
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        if (!conversionService.canConvert(UserDTO.class, UserEntity.class)) {
            throw new RuntimeException("Can not convert UserDTO.class to UserEntity.class");
        }
        UserEntity userEntity = conversionService.convert(user, UserEntity.class);
        UUID uuid = UUID.randomUUID();
        userEntity.setUuid(uuid);
        Instant dtCreated = Instant.now();
        userEntity.setDtCreate(dtCreated);
        userEntity.setDtUpdate(dtCreated);
        dao.save(userEntity);
        return uuid;
    }
    @Override
    @Auditable(AuditCode.UPDATE)
    public UUID update(UUID id, Instant version, @Validated UserDTO user) {
        UserEntity userEntity = dao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("There is no user with such id"));
        if(version.toEpochMilli() != userEntity.getDtUpdate().toEpochMilli()){
            throw new InvalidVersionException("Invalid version");
        }
            userEntity.setFio(user.getFio());
            userEntity.setMail(user.getMail());
            userEntity.setStatus(new StatusEntity(user.getStatus()));
            userEntity.setPassword(user.getPassword());
            userEntity.setRole(new RoleEntity(user.getRole()));
            dao.save(userEntity);
            return id;
    }
    public Pages<UserModel> getPageUser(Pageable paging) {
        Page<UserEntity> all = dao.findAll(paging);
        if (!conversionService.canConvert(UserEntity.class, UserModel.class)) {
            throw new IllegalStateException("Can not convert UserEntity.class to UserModel.class");
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
            throw new IllegalStateException("Can not convert UserEntity.class to UserModel.class");
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
//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyc2VyLmFuZEBnbWFpbC5jb20iLCJpc3MiOiJJVEFjYWRlbXkiLCJleHAiOjE2Nzk1MTUyNzksImlhdCI6MTY3ODkxMDQ3OSwiYXV0aG9yaXRpZXMiOiJVU0VSIn0.DZRM-j7H73zeSejqwbHA9K79c664r1r3ktEC0e2LLDpa0liV2Bg2SP6KWg2qpbZyC5sfo3ajFz7RGo0Y6yde4g