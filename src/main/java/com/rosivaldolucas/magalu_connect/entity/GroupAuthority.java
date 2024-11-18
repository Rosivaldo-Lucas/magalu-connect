package com.rosivaldolucas.magalu_connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "group_authority_table")
public class GroupAuthority {

  @Id
  private String name;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "group_authority_has_authority_table",
          joinColumns = @JoinColumn(name = "group_authority_name_id"),
          inverseJoinColumns = @JoinColumn(name = "authority_name_id")
  )
  private Set<Authority> authorities = new HashSet<>();

}
