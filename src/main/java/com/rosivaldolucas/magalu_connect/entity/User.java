package com.rosivaldolucas.magalu_connect.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_table")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @ManyToMany
  @JoinTable(
          name = "user_has_role",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private final Set<Role> roles = new HashSet<>();

  protected User() { }

  private User(String name, String username, String password) {
    LocalDateTime now = LocalDateTime.now();

    this.name = name;
    this.username = username;
    this.password = password;
    this.createdAt = now;
    this.updatedAt = now;
  }

  public static User crateWith(String name, String username, String password) {
    return new User(name, username, password);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
