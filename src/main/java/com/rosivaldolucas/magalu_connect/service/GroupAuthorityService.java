package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.entity.GroupAuthority;
import com.rosivaldolucas.magalu_connect.repository.GroupAuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GroupAuthorityService {

  private final GroupAuthorityRepository groupAuthorityRepository;

  public GroupAuthority getGroupAuthoritiesUser() {
    return this.groupAuthorityRepository.findById("GROUP_USER").orElseThrow();
  }

}
