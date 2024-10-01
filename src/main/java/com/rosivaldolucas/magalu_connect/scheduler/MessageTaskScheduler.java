package com.rosivaldolucas.magalu_connect.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class MessageTaskScheduler {

  private final MessageService messageService;

  public MessageTaskScheduler(MessageService messageService) {
    this.messageService = messageService;
  }

  @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
  public void triggerScheduledMessage() {
    LocalDateTime now = LocalDateTime.now();

    this.messageService.sendMessage(now);
  }

}
