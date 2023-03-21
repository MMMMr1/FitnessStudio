package it.academy.fitness_studio.configuration;

import it.academy.fitness_studio.web.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfiguration {
    private final JwtFilter filter;

    public SecurityConfiguration(JwtFilter filter) {
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
                .authenticationEntryPoint((request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage());})
                .and();
        http
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers(HttpMethod.GET,"/api/v1/product").permitAll()
                        .antMatchers(HttpMethod.PUT,"/api/v1/product/**").hasAuthority("ROLE_ADMIN")
                        .antMatchers(HttpMethod.POST,"/api/v1/product/**").hasAuthority("ROLE_ADMIN")
                        .antMatchers(HttpMethod.GET,"/api/v1/recipe").permitAll()
                        .antMatchers(HttpMethod.PUT,"/api/v1/recipe/**").hasAuthority("ROLE_ADMIN")
                        .antMatchers(HttpMethod.POST,"/api/v1/recipe/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated());
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
}