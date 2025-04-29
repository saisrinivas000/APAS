package com.APAS.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "performance_data")
public class PerformanceData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int heartRate;
	private String positionQuality;
	private int pointScored;
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public PerformanceData() {
	}

	public PerformanceData(Long id, int heartRate, String positionQuality, int pointScored, LocalDateTime timestamp,
			User user) {
		this.id = id;
		this.heartRate = heartRate;
		this.positionQuality = positionQuality;
		this.pointScored = pointScored;
		this.timestamp = timestamp;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public String getPositionQuality() {
		return positionQuality;
	}

	public void setPositionQuality(String positionQuality) {
		this.positionQuality = positionQuality;
	}

	public int getPointScored() {
		return pointScored;
	}

	public void setPointScored(int pointScored) {
		this.pointScored = pointScored;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
