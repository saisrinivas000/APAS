package com.APAS.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.APAS.demo.model.PerformanceData;
import com.APAS.demo.model.User;

@Service
public class SensorSimulationService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PerformanceDataService performanceDataService;
	
	@Autowired
	private LeaderboardService leaderboardService;
	
	private final Random random = new Random();
	
	@Scheduled(fixedRate = 60000)
	public void simulateSensorData() {
		List<User> athletes = userService.getAllAthletes();
		for(User athlete : athletes) {
			int heartRate = random.nextInt(61) + 60;
			String positionQuality = random.nextBoolean() ? "GOOD" : "BAD";
			int pointsScored = random.nextInt(11);
			
			PerformanceData data = new PerformanceData();
			data.setUser(athlete);
			data.setHeartRate(heartRate);
			data.setPointScored(pointsScored);
			data.setPositionQuality(positionQuality);
			data.setTimestamp(LocalDateTime.now());
			
			performanceDataService.saveData(data);
			leaderboardService.updateLeaderboard(athlete, pointsScored);
			
			System.out.println("Simulated data for: " + athlete.getName());
			 
		}
	}
}
