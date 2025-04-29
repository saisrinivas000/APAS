package com.APAS.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.APAS.demo.model.PerformanceData;

@Repository
public interface PerformanceDataRepository extends JpaRepository<PerformanceData, Long>{
	List<PerformanceData> findByUserId(Long userId);
	List<PerformanceData> findByUserIdAndTimestampBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
