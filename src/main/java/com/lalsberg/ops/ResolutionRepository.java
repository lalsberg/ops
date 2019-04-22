package com.lalsberg.ops;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResolutionRepository extends JpaRepository<Resolution, Long> {

	List<Resolution> findAll();

	List<Resolution> findAllByOrderByFrequencyDesc();

}
