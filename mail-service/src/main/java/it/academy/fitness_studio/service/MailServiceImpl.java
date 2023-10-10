package it.academy.fitness_studio.service;

import it.academy.fitness_studio.kafka.schema.Verification;
import it.academy.fitness_studio.service.api.MailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailServiceImpl implements MailService {
    private JavaMailSender emailSender;
    private String NOREPLY_ADDRESS = "maksim.maks.23@mail.ru";
    public MailServiceImpl(JavaMailSender emailSender  ) {
        this.emailSender = emailSender;
    }
    @Override
    public void sendVerificationMessage(Verification messageDto) {
        String mail =  messageDto.getMail().toString();
        String subject = "Активируйте свою учетную запись в Thyme ";
        String code = messageDto.getCode().toString();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(NOREPLY_ADDRESS);
            message.setSubject(subject);
            message.setText("Добро пожаловать в Thyme!\n" +
                    "Для завершения процесса регистрации, пожалуйста, пройдите по ссылке: \n http://localhost:8080/api/v1/users/verification?code=" + code + "&mail=" + mail+"\n"+
                    "\n" +
                    "Если вы не зарегистрировали аккаунт в Thyme, пожалуйста, проигнорируйте это сообщение.\n" +
                    "\n" +
                    "С любовью,\n" +
                    "Команда Thyme.");
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}

