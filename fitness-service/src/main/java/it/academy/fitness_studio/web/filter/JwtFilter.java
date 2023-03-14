package it.academy.fitness_studio.web.filter;

import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.exception.UserNotFoundException;
import it.academy.fitness_studio.service.api.IUserService;
import it.academy.fitness_studio.web.utils.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

//    private final UserDetailsManager userManager;

    //    public JwtFilter(UserDetailsManager userManager) {
//        this.userManager = userManager;
//    }
    private final IUserService userService;

    public JwtFilter(IUserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!JwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }
        // Get user identity and set it on the spring security context

        UserModel userModel;

        try {

            userModel = userService.getUser(JwtTokenUtil.getUserMail(token));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        String s = userModel.getRole().toString();
        String role = "ROLE_" + s;
        roles.add(new SimpleGrantedAuthority(role));

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userModel, null,
                roles
//                userModel == null ?
//                        List.of() : Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("ROLE_"+userModel.getRole().toString()))
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}