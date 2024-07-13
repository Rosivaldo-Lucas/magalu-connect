package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.exception.MessageSendException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailMessageSender implements MessageSenderStrategy {

  private final Logger log = LoggerFactory.getLogger(EmailMessageSender.class);

  private final JavaMailSender javaMailSender;

  public EmailMessageSender(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Override
  public void send(Message message) throws MessageSendException {
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();

      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
      mimeMessageHelper.setFrom("rosilucas999@gmail.com");
      mimeMessageHelper.setTo(message.getRecipients().toArray(new String[0]));
      mimeMessageHelper.setSubject("Magalu Connect - Send Message Test");
      mimeMessageHelper.setText(message.getContent(), true);

      javaMailSender.send(mimeMessage);
    } catch (Exception ex) {
      throw new MessageSendException(ex.getMessage());
    }
  }

}
