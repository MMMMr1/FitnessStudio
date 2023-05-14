package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.enums.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.core.exception.UserNotFoundException;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import it.academy.fitness_studio.dao.api.AuthenticationDao;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.AuthenticationService;
import it.academy.fitness_studio.service.api.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${mail.url}")
    private String mailUrl;
    private final AuthenticationDao dao;
    private final UserService service;
    private ConversionService conversionService;
    private BCryptPasswordEncoder encoder;
    private static final Logger logger =
            LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    public AuthenticationServiceImpl(AuthenticationDao dao,
                                     UserService service,
                                     ConversionService conversionService,
                                     BCryptPasswordEncoder encoder
    ) {
        this.dao = dao;
        this.service = service;
        this.conversionService = conversionService;
        this.encoder = encoder;
    }
    @Override
    @Transactional
    public void create(@Validated UserRegistrationDTO user) {
        service.create(new UserDTO(user.getMail(), user.getFio(), user.getPassword()));
        UserEntity userEntity = find(user.getMail());
        String code = UUID.randomUUID().toString();
        userEntity.setCode(code);
        dao.save(userEntity);
        logger.info("Send verification code {"+code+"} to user: ", user);
        sendMessage(user.getMail(),code);
    }
    @Override
    @Transactional
    public void verify(String code,String mail) {
        UserEntity userEntity = find(mail);
        if(code.equals(userEntity.getCode())){
            userEntity.setStatus(new StatusEntity(UserStatus.ACTIVATED));
            userEntity.setCode(null);
            dao.save(userEntity);
        } else {
            logger.error("Unsuccessful verification: "+mail+" "+code);
            throw new ValidationUserException("Incorrect mail and code");
        }
    }
    @Override
    public UserModel login(@Validated UserLoginDTO user) {
        UserEntity userEntity = find(user.getMail());
        if(!encoder.matches(user.getPassword(),userEntity.getPassword())){
            logger.error("Unsuccessful login with "+ user.getMail());
            throw new ValidationUserException("Incorrect mail and password");
        }
        if (!userEntity.getStatus().getStatus().equals(UserStatus.ACTIVATED)) {
            logger.error("Unsuccessful login with "+ user.getMail()+ " user is not activated");
            throw new ValidationUserException("Incorrect mail and password");
        }
        return conversionService.convert(userEntity,UserModel.class);
    }
    @Override
    public UserModel getUser(String mail) {
        UserEntity userEntity = find(mail);
        if (!conversionService.canConvert(UserEntity.class, UserModel.class)) {
            throw new IllegalStateException("Can not convert UserEntity.class to UserModel.class");
        }
        return conversionService.convert(userEntity, UserModel.class);
    }
    private UserEntity find(String mail){
        return dao.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("User with mail {"+mail+"} is not registered"));
    }
    private void sendMessage(String to, String code ){
        try {
            JSONObject object =new JSONObject();
            object.put("to", to);
            object.put("subject", "Активируйте свою учетную запись в Healthy Cloud ");
            object.put("text",code);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(mailUrl))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();
            httpClient.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .exceptionally(e -> "Exception: "+ e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
