package it.academy.fitness_studio.web.controllers;


import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.web.utils.UserHolder;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import it.academy.fitness_studio.web.utils.JwtTokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AuthenticationController {
    private IAuthenticationService service;
    private final JwtTokenHandler handler;
    private static final Logger logger =
            LoggerFactory.getLogger(AuthenticationController.class);
    public AuthenticationController(IAuthenticationService service, JwtTokenHandler handler) {
        this.service = service;
        this.handler = handler;
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
        logger.info("Authentication of user with mail: "+ mail+ "is successful");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Validated UserLoginDTO user) {
        UserModel login = service.login(user);
        logger.info("Authorization of "+ user+ "is successful" );
        return ResponseEntity.status(HttpStatus.OK)
                .body(handler.generateAccessToken(login));
    }
    @RequestMapping(path = "/me", method = RequestMethod.GET)
    public ResponseEntity<?>  getUserInfo() {
        UserHolder userHolder = new  UserHolder();
        return ResponseEntity.status(HttpStatus.OK)
                .body(userHolder.getUser());
    }
    @RequestMapping( path = "/details", method = RequestMethod.GET)
    protected ResponseEntity<?> verify(
            @RequestParam(name = "mail") String mail)  {
        service.getUser(mail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
