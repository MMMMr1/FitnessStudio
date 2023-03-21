package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.dto.VerificationMailDTO;
import it.academy.fitness_studio.service.api.IMailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService implements IMailService {
    private JavaMailSender emailSender;
    private String NOREPLY_ADDRESS = "maksim.maks.23@mail.ru";
    public MailService(JavaMailSender emailSender  ) {
        this.emailSender = emailSender;
    }
    @Override
    public void sendVerificationMessage(VerificationMailDTO mailDTO) {
        String mail = mailDTO.getTo();
        String subject = "Активируйте свою учетную запись в Thyme ";
        String code = mailDTO.getText();
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

