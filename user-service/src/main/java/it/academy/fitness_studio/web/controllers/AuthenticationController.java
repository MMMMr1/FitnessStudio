package it.academy.fitness_studio.web.controllers;


import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.service.UserHolder;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import it.academy.fitness_studio.web.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AuthenticationController {
    UserDetails userDetails;
    private IAuthenticationService service;

    public AuthenticationController(IAuthenticationService service) {
        this.service = service;
    }
    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    protected ResponseEntity<?> create(
            @RequestBody @Validated UserRegistrationDTO user) {
        service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping( path = "/verification", method = RequestMethod.GET)
    protected ResponseEntity<?> verify(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "mail") String mail)  {
        service.verify(code,mail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Validated UserLoginDTO user) {
        UserModel login = service.login(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(JwtTokenUtil.generateAccessToken( login ));
    }
    @RequestMapping(path = "/me", method = RequestMethod.GET)
    public ResponseEntity<?>  getUserInfo() {
        UserHolder userHolder = new  UserHolder();
        return ResponseEntity.status(HttpStatus.OK)
                .body(userHolder.getUser());
    }
}
