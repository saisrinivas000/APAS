package com.APAS.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.APAS.demo.model.PerformanceData;
import com.APAS.demo.repository.PerformanceDataRepository;

@Service	
public class PerformanceDataService {
	
	@Autowired
	private PerformanceDataRepository repo;
	
	public PerformanceData saveData(PerformanceData data) {
		return repo.save(data);
	}
	
	public List<PerformanceData> getByUserId(Long userId){
		return repo.findByUserId(userId);
	}
	
	public List<PerformanceData> getByUserIdAndTimeRange(Long userId,LocalDateTime start,LocalDateTime end){
		return repo.findByUserIdAndTimestampBetween(userId, start, end);
	}
	public PerformanceData getPerformanceDataByAthleteId(Long athleteId) {
        Optional<PerformanceData> performanceData = repo.findById(athleteId);
        
        return performanceData.orElse(null);
    }
}
