package com.lalsberg.ops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpressionMatcher {

	private ResolutionExpressionMatchRepository expressionMatchRepository;

	@Autowired
	public ExpressionMatcher(ResolutionExpressionMatchRepository expressionMatchRepository) {
		this.expressionMatchRepository = expressionMatchRepository;
	}


	public int getScore(Resolution resolution, Ops ops) {
		List<ResolutionExpressionMatch> expressionMatchs = expressionMatchRepository.findByResolution(resolution);
		
		return expressionMatchs.stream()
				.filter(expressionMatch -> expressionMatch.match(ops))
				.mapToInt(ResolutionExpressionMatch::getScore)
				.sum();
	}

}
