package it.academy.fitness_studio.web.controllers.utils;

import it.academy.fitness_studio.core.dto.user.UserDetailsDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {
    public UserDetailsDTO getUser(){
        return (UserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
