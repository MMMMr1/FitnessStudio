package it.academy.fitness_studio.web.controllers.filter;

import it.academy.fitness_studio.core.dto.user.UserDetailsDTO;
import it.academy.fitness_studio.web.controllers.utils.JwtTokenHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsManager userManager;
    private final JwtTokenHandler jwtHandler;

    public JwtFilter(UserDetailsManager userManager, JwtTokenHandler jwtHandler) {
        this.userManager = userManager;
        this.jwtHandler = jwtHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.split(" ")[1].trim();
        if (!jwtHandler.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        UserDetailsDTO userDTO = null;
        String userMail = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userMail = jwtHandler.extractUsername(token);
            String fio = jwtHandler.extractFio(token);
            String uuid = jwtHandler.extractUUID(token);
            userDTO = new UserDetailsDTO();
            userDTO.setMail(userMail);
            userDTO.setUuid(uuid);
            userDTO.setName(fio);

        }
        if (userDTO != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            String commaSeparatedListOfAuthorities = jwtHandler.extractAuthorities(jwt);
            userDTO.setRole(commaSeparatedListOfAuthorities);
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" +commaSeparatedListOfAuthorities);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                          userDTO, null, authorities);
            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        chain.doFilter(request, response);
    }
}