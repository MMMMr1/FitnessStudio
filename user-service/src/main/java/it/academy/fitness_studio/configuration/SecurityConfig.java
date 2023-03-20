package it.academy.fitness_studio.configuration;


import it.academy.fitness_studio.web.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig  {
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
                        .antMatchers("/api/v1/users/registration").permitAll()
                        .antMatchers("/api/v1/users/verification").permitAll()
                        .antMatchers("/api/v1/users/me").authenticated()
                        .antMatchers("/api/v1/users/login").permitAll()
                        .antMatchers("/api/v1/users/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
}