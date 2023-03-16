package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.enums.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.core.exception.UserNotFoundException;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IAuthenticationService;
//import it.academy.fitness_studio.service.api.IMailService;
import it.academy.fitness_studio.service.api.IUserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class AuthenticationService implements IAuthenticationService  {
    private final IAuthenticationDao dao;
    private final IUserService service;
//    private final IMailService emailService;
    private ConversionService conversionService;
    private BCryptPasswordEncoder encoder;

    public AuthenticationService(IAuthenticationDao dao,
                                 IUserService service,
//                                 IMailService emailService,
                                 ConversionService conversionService,
                                 BCryptPasswordEncoder encoder
    ) {
        this.dao = dao;
        this.service = service;
//        this.emailService = emailService;
        this.conversionService = conversionService;
        this.encoder = encoder;
    }
    @Override
    public void create(UserRegistrationDTO user) {
        service.create(new UserDTO(user.getMail(), user.getFio(), user.getPassword()));
        UserEntity userEntity = find(user.getMail());
        String code = UUID.randomUUID().toString();
        userEntity.setCode(code);
        dao.save(userEntity);
       sendSimpleMessage(user.getMail(),
                code, "Подтвердите e-mail адрес.");
    }

    @Override
    public void verify(String code,String mail) {
        UserEntity userEntity = find(mail);
        if(code.equals(userEntity.getCode())){
            userEntity.setStatus(new StatusEntity(UserStatus.ACTIVATED));
            userEntity.setCode(null);
            dao.save(userEntity);
        } else throw new ValidationUserException("Incorrect mail and code");
    }
    @Override
    public UserModel login(@Validated UserLoginDTO user) {
        UserEntity userEntity = find(user.getMail());
        if(!encoder.matches(user.getPassword(),userEntity.getPassword())){
            throw new ValidationUserException("Incorrect mail and password");
        }
        return conversionService.convert(userEntity,UserModel.class);
    }
    private UserEntity find(String mail){
        return dao.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("User with this mail is not registered"));
    }
    private void sendSimpleMessage(String to, String subject, String text){
        try {
            JSONObject object =new JSONObject();
            object.put("to", to);
            object.put("subject", subject);
            object.put("text",text);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://mail-service:8080/api/v1/mail"))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (JSONException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
