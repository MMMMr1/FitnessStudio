package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CustomPageUserEntityConverter implements Converter<Page<UserEntity>, Pages< UserModel>> {

    @Override
    public Pages<UserModel> convert(Page<UserEntity> source) {
        return null;
    }
}
