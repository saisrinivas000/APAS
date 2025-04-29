package com.APAS.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.APAS.demo.DTO.FeedbackRequest;
import com.APAS.demo.DTO.FeedbackResponseDTO;
import com.APAS.demo.DTO.UserDTO;
import com.APAS.demo.model.Feedback;
import com.APAS.demo.model.PerformanceData;
import com.APAS.demo.model.User;
import com.APAS.demo.service.FeedbackService;
import com.APAS.demo.service.PerformanceDataService;
import com.APAS.demo.service.UserService;

@RestController
@RequestMapping("/api/coach")
public class CoachController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private PerformanceDataService performanceDataService;

    @PostMapping("/feedback")
    public ResponseEntity<?> giveFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        User coach = userService.findById(feedbackRequest.getCoachId()).orElse(null);
        if (coach == null || coach.getRole() != User.Role.COACH) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only a valid coach can give feedback.");
        }

        User athlete = userService.findById(feedbackRequest.getAthleteId()).orElse(null);
        if (athlete == null || athlete.getRole() != User.Role.ATHLETE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid athlete ID.");
        }

        Feedback feedback = feedbackService.giveFeedback(coach, athlete, feedbackRequest.getMessage());
        FeedbackResponseDTO responseDTO = new FeedbackResponseDTO(
            feedback.getId(),
            feedback.getCoach().getName(),
            feedback.getAthlete().getName(),
            feedback.getMessage(),
            feedback.getTimestamp()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/performance/data/{id}")
    public ResponseEntity<PerformanceData> getPerformanceData(@PathVariable("id") Long athleteId) {
        PerformanceData performanceData = performanceDataService.getPerformanceDataByAthleteId(athleteId);

        if (performanceData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(performanceData);
    }

    @GetMapping("/athletes")
    public ResponseEntity<List<UserDTO>> getAllAthletes() {
        List<User> athletes = userService.getAllAthletes();

        List<UserDTO> athleteDTOs = athletes.stream()
            .map(athlete -> new UserDTO(athlete.getId(), athlete.getName(), athlete.getEmail()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(athleteDTOs);
    }


    @GetMapping("/performance/{athleteId}")
    public ResponseEntity<List<PerformanceData>> getAthletePerformance(@PathVariable Long athleteId) {
        return ResponseEntity.ok(performanceDataService.getByUserId(athleteId));
    }

    @GetMapping("/sent-feedbacks/{coachId}")
    public ResponseEntity<List<FeedbackResponseDTO>> getSentFeedbacksByCoach(@PathVariable Long coachId) {
        List<Feedback> feedbacks = feedbackService.getFeedbackForCoach(coachId);

        if (feedbacks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<FeedbackResponseDTO> responseDTOs = feedbacks.stream()
            .map(feedback -> new FeedbackResponseDTO(
                feedback.getId(),
                feedback.getCoach().getName(),
                feedback.getAthlete().getName(),
                feedback.getMessage(),
                feedback.getTimestamp()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }
}
