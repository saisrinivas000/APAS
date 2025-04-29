package com.APAS.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.APAS.demo.model.Feedback;
import com.APAS.demo.model.User;
import com.APAS.demo.repository.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository repo;
	
	public Feedback giveFeedback(User coach, User athlete, String message) {
		Feedback feedback = new Feedback();
		feedback.setCoach(coach);
		feedback.setAthlete(athlete);
		feedback.setMessage(message);
		feedback.setTimestamp(LocalDateTime.now());
		return repo.save(feedback);
	}
	
	public List<Feedback> getFeedbackForAthlete(Long athleteId){
		return repo.findByAthleteId(athleteId);
	}
	
	public List<Feedback> getFeedbackForCoach(Long coachId){
		return repo.findByCoachId(coachId);
	}
}
