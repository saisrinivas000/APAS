package com.APAS.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.APAS.demo.model.Leaderboard;

@Repository	
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long>{
	List<Leaderboard> findAllByOrderByTotalPointsDesc();
	Optional<Leaderboard> findByUserId(Long userId);
}
