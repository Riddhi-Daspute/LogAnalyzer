package com.loganalyzer.loganalyzer.service;

import com.loganalyzer.loganalyzer.model.LogEntry;
import com.loganalyzer.loganalyzer.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public String uploadLog(MultipartFile file) {

         logRepository.deleteAll();

        if (file.isEmpty()) {
            return "File is empty!";
        }

        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream()))) {

            String line;

            while ((line = reader.readLine()) != null) {

               String[] parts = line.split(" ", 4);

                if (parts.length == 4) {

                    String timestamp = parts[0] + " " + parts[1];
                    String level = parts[2];
                    String message = parts[3];

                    System.out.println("------------------------");
                    System.out.println("Timestamp : " + timestamp);
                    System.out.println("Level     : " + level);
                    System.out.println("Message   : " + message);

                    LogEntry logEntry = new LogEntry();

                    logEntry.setTimestamp(timestamp);
                    logEntry.setLevel(level);
                    logEntry.setMessage(message);

                    logRepository.save(logEntry);

                    lineCount++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file.";
        }

        return "Test Success: read " + lineCount + " log  entries.";
    }

    public Map<String, Long> getStatistics() {

        Map<String, Long> stats = new LinkedHashMap<>();
        stats.put("Total Logs", logRepository.count());
        List<Object[]> results = logRepository.countLogsByLevel();

        for (Object[] row : results) {
            String level = (String) row[0];
            Long count = (Long) row[1];
            stats.put(level, count);
        }
        return stats;
    }

    public List<LogEntry> getAllLogs() {
        return logRepository.findAll();
    }

    public void clearLogs() {
        logRepository.deleteAll();
}
}