package it.academy.fitness_studio.configuration;


import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.dao.api.IUserDao;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.service.*;
import it.academy.fitness_studio.service.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceConfiguration {
    private final IUserDao dao;
    private final ConversionService conversionService;

    public UserServiceConfiguration(IUserDao dao, ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserService userService(IUserDao dao,
                                    ConversionService conversionService,
                                    PasswordEncoder encoder
    ) {
        return new UserService(dao, conversionService, encoder);
    }

    @Bean
    public IAuthenticationService authenticationService(IAuthenticationDao dao,
                                                        IUserService service,
                                                        ConversionService conversionService,
                                                        BCryptPasswordEncoder encoder) {

        return new AuthenticationService(dao, service,
                conversionService, encoder);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> conversionService.convert(dao.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user: ")), UserModel.class);
    }
}

