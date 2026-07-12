package com.loganalyzer.loganalyzer.repository;

import com.loganalyzer.loganalyzer.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface LogRepository extends JpaRepository<LogEntry, Long> {
    long count();

    long countByLevel(String level);

    @Query("SELECT l.level, COUNT(l) FROM LogEntry l GROUP BY l.level")
    List<Object[]> countLogsByLevel();
}