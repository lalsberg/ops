package com.lalsberg.ops;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

public class ResolutionResolver {

	private ResolutionRepository resolutionRepository;
	private MatchComparator matchComparator;
	private ExpressionMatcher expressionMatcher;

	public List<Resolution> findPossibleResolutions(Ops ops) {
		List<Resolution> resolutions = resolutionRepository.findAll();

		List<Match> matchs = resolutions.stream()
				.map(resolution -> resolution.match(ops, expressionMatcher))
				.collect(toList());

		Collections.sort(matchs, matchComparator);

		return matchs.stream().map(Match::getResolution).collect(toList());
	}

}
