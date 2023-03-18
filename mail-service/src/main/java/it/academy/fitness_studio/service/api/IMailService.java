package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.VerificationMailDTO;

public interface IMailService {
    void sendVerificationMessage(VerificationMailDTO mailDTO);

}
//    String to,
//    String subject,
//    String text
//    void sendSimpleMessageUsingTemplate(String to,
//            String subject,
//            String ...templateModel);
//    void sendMessageWithAttachment(String to,
//            String subject,
//            String text,
//            String pathToAttachment);
//
//    void sendMessageUsingThymeleafTemplate(String to,
//            String subject,
//            Map<String, Object> templateModel)
//            throws IOException, MessagingException;
//
//    void sendMessageUsingFreemarkerTemplate(String to,
//            String subject,
//            Map<String, Object> templateModel)
//            throws IOException, TemplateException, MessagingException;
//}

