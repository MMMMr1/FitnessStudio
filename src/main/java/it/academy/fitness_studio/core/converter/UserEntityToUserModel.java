package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.UserRole;
import it.academy.fitness_studio.core.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
@Component
public class UserEntityToUserModel implements Converter<UserEntity, UserModel> {
    @Override
    public UserModel convert(UserEntity userEntity) {
        Instant dtCreate = userEntity.getDtCreate();
        Instant dtUpdate = userEntity.getDtUpdate();
        String fio = userEntity.getFio();
        String mail = userEntity.getMail();
        UserRole role = userEntity.getRole().getRole();
        UserStatus status = userEntity.getStatus().getStatus();
        UUID uuid = userEntity.getUuid();
        return new UserModel(uuid, dtCreate, dtUpdate, mail, fio, role, status);
    }
}
