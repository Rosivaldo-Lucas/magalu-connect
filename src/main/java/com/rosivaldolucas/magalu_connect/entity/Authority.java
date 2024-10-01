package com.rosivaldolucas.magalu_connect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authority_table")
public class Authority {

  @Id
  private String name;

}
