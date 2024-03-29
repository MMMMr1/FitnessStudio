package it.academy.fitness_studio.web.utils;

import it.academy.fitness_studio.core.dto.user.UserModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {
    public UserModel getUser(){
        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
