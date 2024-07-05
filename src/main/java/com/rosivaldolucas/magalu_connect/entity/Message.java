package com.rosivaldolucas.magalu_connect.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rosivaldolucas.magalu_connect.enums.ChannelMessageType;
import com.rosivaldolucas.magalu_connect.enums.MessageStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "message_tb")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "content", nullable = false, length = 300)
  private String content;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private MessageStatus status;

  @Enumerated(EnumType.STRING)
  @Column(name = "channel_type", nullable = false)
  private ChannelMessageType channelType;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
  @Column(name = "scheduled_at", nullable = false)
  private LocalDateTime scheduledAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
  @Column(name = "sent_at")
  private LocalDateTime sentAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @ManyToOne(optional = false)
  @JoinColumn(name = "sender_id")
  private User sender;

  @ElementCollection
  @CollectionTable(
          name = "user_recipient_message_tb",
          joinColumns = @JoinColumn(name = "message_id")
  )
  @Column(name = "recipient")
  private Set<String> recipients;

  protected Message() { }

  private Message(String content, ChannelMessageType channelType, LocalDateTime scheduledAt, User sender, Set<String> recipients) {
    this.content = content;
    this.status = MessageStatus.SCHEDULED;
    this.channelType = channelType;
    this.scheduledAt = scheduledAt;
    this.sender = sender;
    this.recipients = recipients;

    LocalDateTime now = LocalDateTime.now();
    this.createdAt = now;
    this.updatedAt = now;
  }

  public static Message createWith(String content, ChannelMessageType channelType, LocalDateTime scheduledAt, User sender, Set<String> recipients) {
    return new Message(content, channelType, scheduledAt, sender, recipients);
  }

}
