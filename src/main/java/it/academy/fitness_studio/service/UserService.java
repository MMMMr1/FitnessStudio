package it.academy.fitness_studio.service;




import it.academy.fitness_studio.core.UserRole;
import it.academy.fitness_studio.core.UserStatus;
import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.UserDTO;
import it.academy.fitness_studio.core.dto.UserModel;
import it.academy.fitness_studio.core.dto.UserSavedDTO;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.entity.RoleEntity;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserService implements IUserService {
//    @Autowired
    private final IUserDao dao;
    public UserService(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    public void create(UserDTO user) {
//        Validate
//        check double post

        UserSavedDTO userSavedDTO = new UserSavedDTO(user.getMail(),
                user.getFio(),
                user.getRole(),
                user.getStatus(),
                user.getPassword());
        UserEntity userEntity = new UserEntity(userSavedDTO.getUuid(),
                userSavedDTO.getDtCreate(),
                userSavedDTO.getDtUpdate(),
                userSavedDTO.getMail(),
                userSavedDTO.getFio(),
                new RoleEntity(userSavedDTO.getRole()),
                new StatusEntity(userSavedDTO.getStatus()),
                userSavedDTO.getPassword()
        );
        if(userEntity.getStatus().getStatus().equals(UserStatus.WAITING_ACTIVATION)){
            userEntity.setCode(UUID.randomUUID().toString());
        }
        dao.save(userEntity);
    }

    @Override
    public UserModel getUser(UUID id) {
        Optional<UserEntity> byId = dao.findById(id);
        UserEntity userEntity = byId.get();

        Instant dtCreate = userEntity.getDtCreate();
        Instant dtUpdate = userEntity.getDtUpdate();
        String fio = userEntity.getFio();
        String mail = userEntity.getMail();
        UserRole role = userEntity.getRole().getRole();
        UserStatus status = userEntity.getStatus().getStatus();
        UUID uuid = userEntity.getUuid();

        UserModel userModel = new UserModel(uuid, dtCreate, dtUpdate, mail, fio, role, status);
        return userModel;
    }

    @Override
    public void update(UUID id, Instant version, UserDTO user) {
//        validate

        UserEntity userEntity = dao.findById(id).orElseThrow();
        if ( version.toEpochMilli() == userEntity.getDtUpdate().toEpochMilli()){
            userEntity.setFio(user.getFio());
            userEntity.setMail(user.getMail());
            userEntity.setStatus(new StatusEntity(user.getStatus()));
            userEntity.setPassword(user.getPassword());
            userEntity.setRole(new RoleEntity(user.getRole()));
            dao.save(userEntity);
        }
    }

    public Pages getPageUser(int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        Page<UserEntity> all = dao.findAll(paging);

        List<UserModel> content = all.getContent().stream()
                .map(s -> new UserModel(s.getUuid(),
                        s.getDtCreate(),
                        s.getDtUpdate(),
                        s.getMail(),
                        s.getFio(),
                        s.getRole().getRole(),
                        s.getStatus().getStatus()))
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
}
