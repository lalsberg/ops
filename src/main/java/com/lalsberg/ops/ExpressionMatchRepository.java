package com.lalsberg.ops;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpressionMatchRepository extends JpaRepository<ResolutionExpressionMatch, Long> {

	List<ResolutionExpressionMatch> findBy(Resolution resolution);

}
