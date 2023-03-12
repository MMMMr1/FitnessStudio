package it.academy.fitness_studio.service;

import it.academy.fitness_studio.service.api.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;

public class EmailService implements IEmailService {

    private JavaMailSender emailSender;

    private SimpleMailMessage template;

    private SpringTemplateEngine thymeleafTemplateEngine;


    public EmailService(JavaMailSender emailSender,
                        SimpleMailMessage template,
                        SpringTemplateEngine thymeleafTemplateEngine
                       ) {
        this.emailSender = emailSender;
        this.template = template;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("maksim.maks.23@mail.ru");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

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

