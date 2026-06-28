package com.tuservicio.core.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Conciliation {
    private String id;
    private LocalDateTime executionTime;
    private List<String> matchedTransactionIds;
    private List<String> unmatchedTransactionIds;
    private String status;

    public Conciliation() {}

    public Conciliation(String id, LocalDateTime executionTime, List<String> matchedTransactionIds, List<String> unmatchedTransactionIds, String status) {
        this.id = id;
        this.executionTime = executionTime;
        this.matchedTransactionIds = matchedTransactionIds;
        this.unmatchedTransactionIds = unmatchedTransactionIds;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(LocalDateTime executionTime) {
        this.executionTime = executionTime;
    }

    public List<String> getMatchedTransactionIds() {
        return matchedTransactionIds;
    }

    public void setMatchedTransactionIds(List<String> matchedTransactionIds) {
        this.matchedTransactionIds = matchedTransactionIds;
    }

    public List<String> getUnmatchedTransactionIds() {
        return unmatchedTransactionIds;
    }

    public void setUnmatchedTransactionIds(List<String> unmatchedTransactionIds) {
        this.unmatchedTransactionIds = unmatchedTransactionIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
