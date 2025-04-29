package com.APAS.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.APAS.demo.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
	
	List<Feedback> findByAthleteId(Long athleteId);
	List<Feedback> findByCoachId(Long coachId);

}
