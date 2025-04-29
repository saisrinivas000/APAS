package com.APAS.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.APAS.demo.model.Leaderboard;
import com.APAS.demo.model.User;
import com.APAS.demo.repository.LeaderboardRepository;
import com.APAS.demo.repository.UserRepository;

@Service
public class LeaderboardService {
	
	@Autowired
	private LeaderboardRepository repo;

	@Autowired
	private UserRepository userRepository;
	
	public void updateLeaderboard(User user, int newPoints) {
		Optional<Leaderboard> existingEntryOpt = repo.findByUserId(user.getId());
		
		Leaderboard entry;
		if (existingEntryOpt.isPresent()) {
			entry = existingEntryOpt.get();
			entry.setTotalPoints(entry.getTotalPoints() + newPoints);
			entry.setMatchesPlayed(entry.getMatchesPlayed() + 1);
		} else {
			entry = new Leaderboard();
			entry.setUser(user);
			entry.setTotalPoints(newPoints);
			entry.setMatchesPlayed(1);
		}
		repo.save(entry);
	}
	
	public List<Leaderboard> getLeaderboard() {
		List<User> athletes = userRepository.findAllByRole(User.Role.ATHLETE);
		
		List<Leaderboard> leaderboard = repo.findAllByOrderByTotalPointsDesc();

		return leaderboard.stream()
			.filter(entry -> athletes.contains(entry.getUser()))
			.collect(Collectors.toList());
	}
}
