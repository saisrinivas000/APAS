package com.APAS.demo.DTO;

import java.time.LocalDateTime;

public class FeedbackResponseDTO {
    private Long id;
    private String coachName;
    private String athleteName;
    private String message;
    private LocalDateTime timestamp;

    public FeedbackResponseDTO() {
    }

    public FeedbackResponseDTO(Long id, String coachName, String athleteName, String message, LocalDateTime timestamp) {
        this.id = id;
        this.coachName = coachName;
        this.athleteName = athleteName;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
