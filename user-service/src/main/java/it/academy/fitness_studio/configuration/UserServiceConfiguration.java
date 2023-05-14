package it.academy.fitness_studio.configuration;


import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.dao.api.UserDao;
import it.academy.fitness_studio.dao.api.AuthenticationDao;
import it.academy.fitness_studio.service.*;
import it.academy.fitness_studio.service.api.*;
import it.academy.fitness_studio.service.api.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceConfiguration {
    private final UserDao dao;
    private final ConversionService conversionService;
    public UserServiceConfiguration(UserDao dao, ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserService userService(UserDao dao,
                                   ConversionService conversionService,
                                   PasswordEncoder encoder) {
        return new UserServiceImpl(dao, conversionService, encoder);
    }
    @Bean
    public AuthenticationService authenticationService(AuthenticationDao dao,
                                                       UserService service,
                                                       ConversionService conversionService,
                                                       BCryptPasswordEncoder encoder) {
        return new AuthenticationServiceImpl(dao, service,
                conversionService, encoder);
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> conversionService.convert(dao.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user: ")), UserModel.class);
    }
}

