package com.APAS.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.APAS.demo.model.Leaderboard;
import com.APAS.demo.service.LeaderboardService;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
	
	@Autowired
	private LeaderboardService leaderboardService;
	
	@GetMapping
	public ResponseEntity<List<Leaderboard>> getLeaderboard() {
		List<Leaderboard> leaderboard = leaderboardService.getLeaderboard();

		if (leaderboard.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(leaderboard);
	}
}
