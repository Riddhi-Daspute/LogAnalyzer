package com.loganalyzer.loganalyzer.controller;

import com.loganalyzer.loganalyzer.service.LogService;
import com.loganalyzer.loganalyzer.model.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        String response = logService.uploadLog(file);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(logService.getStatistics());
    }

    @GetMapping
    public ResponseEntity<List<LogEntry>> getAllLogs() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearLogs() {
        logService.clearLogs();
        return ResponseEntity.ok("All logs deleted successfully.");
    }
}
