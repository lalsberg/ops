package com.lalsberg.ops;

import java.util.List;

public class ExpressionMatcher {

	private ExpressionMatchRepository expressionMatchRepository;

	public int getScore(Resolution resolution, Ops ops) {
		List<ResolutionExpressionMatch> expressionMatchs = expressionMatchRepository.findBy(resolution);
		
		return expressionMatchs.stream()
				.filter(expressionMatch -> expressionMatch.match(ops))
				.mapToInt(ResolutionExpressionMatch::getScore)
				.sum();
	}

}
