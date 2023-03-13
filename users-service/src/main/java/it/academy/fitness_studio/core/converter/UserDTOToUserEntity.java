package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.core.enums.UserStatus;
import it.academy.fitness_studio.entity.RoleEntity;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserEntity implements Converter<UserDTO,UserEntity> {
    @Override
    public UserEntity convert(UserDTO user) {
        return new UserEntity(user.getMail(),
                user.getFio(),
                new RoleEntity(user.getRole()),
                new StatusEntity(user.getStatus()),
                user.getPassword());

    }
}
