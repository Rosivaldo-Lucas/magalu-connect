package com.rosivaldolucas.magalu_connect.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "permission_tb")
public class Permission {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @ManyToMany
  private final Set<Role> roles = new HashSet<>();

}
