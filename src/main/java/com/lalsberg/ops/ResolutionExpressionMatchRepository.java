package com.lalsberg.ops;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResolutionExpressionMatchRepository extends JpaRepository<ResolutionExpressionMatch, Long> {

	List<ResolutionExpressionMatch> findByResolution(Resolution resolution);

}
