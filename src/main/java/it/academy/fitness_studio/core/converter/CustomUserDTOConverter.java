package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.UserRole;
import it.academy.fitness_studio.core.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserSavedDTO;
import it.academy.fitness_studio.entity.RoleEntity;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDTOConverter implements Converter<UserDTO,UserEntity> {

    @Override
    public UserEntity convert(UserDTO user) {
        UserSavedDTO userSavedDTO = new UserSavedDTO(user.getMail(),
                user.getFio(),
                UserRole.valueOf(user.getRole()),
                UserStatus.valueOf(user.getStatus()),
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
        return userEntity;
    }
}
