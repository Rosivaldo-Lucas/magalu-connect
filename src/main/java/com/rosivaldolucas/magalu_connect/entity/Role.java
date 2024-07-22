package com.rosivaldolucas.magalu_connect.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "role_tb")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @ManyToMany
  private final Set<User> users = new HashSet<>();

  @ManyToMany
  @JoinTable(
          name = "role_has_permission",
          joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
  )
  private final Set<Permission> permissions = new HashSet<>();

}
