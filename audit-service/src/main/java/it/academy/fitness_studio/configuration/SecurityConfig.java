package it.academy.fitness_studio.configuration;

import it.academy.fitness_studio.web.controllers.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {
    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http = http.cors().and().csrf().disable();
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();
        http
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers(HttpMethod.POST,"/api/v1/audit").permitAll()
                        .antMatchers(HttpMethod.GET,"/api/v1/audit").hasAuthority("ROLE_ADMIN")
                        .antMatchers(HttpMethod.GET,"/api/v1/audit/{uuid}").hasAuthority("ROLE_ADMIN")
//                        .anyRequest().authenticated()
                );
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
}