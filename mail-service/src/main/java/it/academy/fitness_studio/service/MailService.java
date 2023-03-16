package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.MailDTO;
import it.academy.fitness_studio.service.api.IMailService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.crypto.Data;
import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class MailService implements IMailService {

    private JavaMailSender emailSender;

    private SimpleMailMessage template;

    private SpringTemplateEngine thymeleafTemplateEngine;
    private String NOREPLY_ADDRESS = "maksim.maks.23@mail.ru";


    public MailService(JavaMailSender emailSender,
                       SimpleMailMessage template,
                       SpringTemplateEngine thymeleafTemplateEngine
                       ) {
        this.emailSender = emailSender;
        this.template = template;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    public void sendSimpleMessage(MailDTO mailDTO) {
        String to = mailDTO.getTo();
        String subject = mailDTO.getSubject();
        String text = mailDTO.getText();
        try {
//      SimpleMailMessage шаблон для электронной почты
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(NOREPLY_ADDRESS);
            message.setSubject(subject);

            message.setText("Для завершения процесса регистрации, пожалуйста, пройдите по ссылке: \n http://localhost:8080/api/v1/users/verification?code=" + subject + "&mail=" + to);

            emailSender.send(message);

        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

//    @Override
//    public void sendSimpleMessageUsingTemplate(String to,
//                                               String subject,
//                                               String ...templateModel) {
//        String text = String.format(template.getText(), templateModel);
//        sendSimpleMessage(to, subject, text);
//    }
//
//    @Override
//    public void sendMessageWithAttachment(String to,
//                                          String subject,
//                                          String text,
//                                          String pathToAttachment) {
//        try {
//            MimeMessage message = emailSender.createMimeMessage();
//            // pass 'true' to the constructor to create a multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom(NOREPLY_ADDRESS);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text);
//
//            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
//            helper.addAttachment("Invoice", file);
//
//            emailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public void sendMessageUsingThymeleafTemplate(
//            String to, String subject, Map<String, Object> templateModel)
//            throws MessagingException {
//
//        Context thymeleafContext = new Context();
//        thymeleafContext.setVariables(templateModel);
//
//        String htmlBody = thymeleafTemplateEngine.process("template-thymeleaf.html", thymeleafContext);
//
//        sendHtmlMessage(to, subject, htmlBody);
//    }
//
//    @Override
//    public void sendMessageUsingFreemarkerTemplate(
//            String to, String subject, Map<String, Object> templateModel)
//            throws IOException, TemplateException, MessagingException {
//
//        Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("template-freemarker.ftl");
//        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);
//
//        sendHtmlMessage(to, subject, htmlBody);
//    }
//
//    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
//
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//        helper.setFrom(NOREPLY_ADDRESS);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(htmlBody, true);
//        helper.addInline("attachment.png", resourceFile);
//        emailSender.send(message);
//    }

}

