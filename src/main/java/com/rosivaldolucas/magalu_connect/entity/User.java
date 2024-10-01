package com.rosivaldolucas.magalu_connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Boolean enabled;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "user_has_authority_table",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "authority_name_id")
  )
  private Set<Authority> authorities = new HashSet<>();

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "user_has_group_authority_table",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "group_authority_name_id")
  )
  private Set<GroupAuthority> groupAuthorities = new HashSet<>();

  protected User() { }

  public User(String name, String email, String username, String password, Set<GroupAuthority> groupAuthorities) {
    this.name = name;
    this.email = email;
    this.username = username;
    this.password = password;
    this.groupAuthorities = groupAuthorities;
    this.enabled = Boolean.TRUE;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
