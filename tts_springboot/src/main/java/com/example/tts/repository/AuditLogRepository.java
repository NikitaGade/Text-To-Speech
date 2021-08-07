package com.example.tts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tts.model.AuditLog;


@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
