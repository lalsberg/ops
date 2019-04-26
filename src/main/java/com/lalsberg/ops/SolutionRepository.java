package com.lalsberg.ops;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

	List<Solution> findAll();

	List<Solution> findAllByOrderByFrequencyDesc();

}
