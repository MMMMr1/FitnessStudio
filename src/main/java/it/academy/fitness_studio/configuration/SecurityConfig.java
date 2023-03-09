package it.academy.fitness_studio.configuration;

//import by.itacademy.user.controller.filter.JwtFilter;
import it.academy.fitness_studio.web.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig
//        extends WebSecurityConfigurerAdapter
{
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

        // Set unauthorized requests exception handler
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
                                .antMatchers("/api/v1/users").hasRole("ADMIN")
                        .anyRequest().authenticated()
//                        .antMatchers("/api/v1/users/registration").permitAll()
//                        // Our private endpoints
//                        .anyRequest().authenticated();
                )
                .httpBasic(withDefaults());
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Enable CORS and disable CSRF
//        http = http.cors().and().csrf().disable();
//
//        // Set session management to stateless
//        http = http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and();
//
//        // Set unauthorized requests exception handler
//        http = http
//                .exceptionHandling()
//                .authenticationEntryPoint(
//                        (request, response, ex) -> {
//                            response.sendError(
//                                    HttpServletResponse.SC_UNAUTHORIZED,
//                                    ex.getMessage()
//                            );
//                        }
//                )
//                .and();
////                     работаем только с этой настройкой
////        --------------------------------->>>>>>>>>>>>>>>>>
//        // Set permissions on endpoints
//        http.authorizeRequests()
//                // Our public endpoints
//                .antMatchers("/public/**").permitAll()
//                // Our private endpoints
//                .anyRequest().authenticated();
//
//        // Add JWT token filter
//        http.addFilterBefore(
//                filter,
//                UsernamePasswordAuthenticationFilter.class
//        );
//    }
}