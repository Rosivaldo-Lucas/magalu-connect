package com.rosivaldolucas.magalu_connect.repository;

import com.rosivaldolucas.magalu_connect.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

  @Query("SELECT m FROM Message AS m WHERE m.status = 'SCHEDULED' AND m.scheduledAt < :currentDateTime")
  List<Message> findAllByStatusScheduledAndScheduledAtBefore(@Param("currentDateTime") LocalDateTime currentDateTime);

}
