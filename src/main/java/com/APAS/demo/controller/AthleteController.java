package com.APAS.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.APAS.demo.model.Feedback;
import com.APAS.demo.model.PerformanceData;
import com.APAS.demo.service.FeedbackService;
import com.APAS.demo.service.PerformanceDataService;

@RestController
@RequestMapping("/api/athlete")
public class AthleteController {
	
	@Autowired
	private PerformanceDataService performanceDataService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@GetMapping("/performance/{userId}")
	public ResponseEntity<List<PerformanceData>> getMyPerformance(@PathVariable Long userId){
		return ResponseEntity.ok(performanceDataService.getByUserId(userId));
	}
	
	@GetMapping("/feedback/{userId}")
	public ResponseEntity<List<Feedback>> getMyFeedback(@PathVariable Long userId){
		return ResponseEntity.ok(feedbackService.getFeedbackForAthlete(userId));
	}
}
