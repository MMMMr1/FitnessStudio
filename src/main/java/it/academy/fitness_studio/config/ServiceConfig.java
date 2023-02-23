package it.academy.fitness_studio.config;


import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.service.UserService;
import it.academy.fitness_studio.service.AuthenticationService;
import it.academy.fitness_studio.service.api.IUserService;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public IUserService adminService(IUserDao dao){
        return new UserService(dao);
    }
    @Bean
    public IAuthenticationService userService(IAuthenticationDao dao, IUserService service){
        return new AuthenticationService(dao, service);
    }
}
