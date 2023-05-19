package it.academy.fitness_studio.configuration;

import it.academy.fitness_studio.dao.api.IAuditDao;
import it.academy.fitness_studio.service.*;
import it.academy.fitness_studio.service.api.AuditService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;


@Configuration
public class ServiceConfiguration {
    @Bean
    public AuditService IAuditService(IAuditDao dao,
                                      ConversionService conversionService) {
        return new AuditServiceImpl(dao, conversionService );
    }
}



