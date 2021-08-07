package com.example.tts.controller;

import com.example.tts.exception.ResourceNotFoundException;
import com.example.tts.model.AuditLog;
import com.example.tts.repository.AuditLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class AuditLogController {

    @Autowired
    AuditLogRepository auditLogRepository;

    @GetMapping("/auditlogs")
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    @PostMapping(path = "/auditlogs",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})    
    public AuditLog createAuditLog(@Valid  AuditLog auditLog) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	auditLog.setCreatedAt(dtf.format(now));
        return auditLogRepository.save(auditLog);
    }

    @GetMapping("/auditlogs/{id}")
    public AuditLog getAuditLogById(@PathVariable(value = "id") Long auditLogId) {
        return auditLogRepository.findById(auditLogId)
                .orElseThrow(() -> new ResourceNotFoundException("AuditLog", "id", auditLogId));
    }

	/*
	 * @PutMapping("/auditlogs/{id}") public AuditLog
	 * updateAuditLog(@PathVariable(value = "id") Long auditLogId,
	 * 
	 * @Valid AuditLog auditLogDetails) {
	 * 
	 * AuditLog auditLog = auditLogRepository.findById(auditLogId) .orElseThrow(()
	 * -> new ResourceNotFoundException("AuditLog", "id", auditLogId));
	 * 
	 * auditLog.setInputText(auditLogDetails.getInputText());
	 * 
	 * 
	 * AuditLog updatedAuditLog = auditLogRepository.save(auditLog); return
	 * updatedAuditLog; }
	 */

    @DeleteMapping("/auditlogs/{id}")
    public ResponseEntity<?> deleteAuditLog(@PathVariable(value = "id") Long auditLogId) {
        AuditLog auditLog = auditLogRepository.findById(auditLogId)
                .orElseThrow(() -> new ResourceNotFoundException("AuditLog", "id", auditLogId));

        auditLogRepository.delete(auditLog);

        return ResponseEntity.ok().build();
    }
}
