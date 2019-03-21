package com.lalsberg.ops;

import java.util.Comparator;

import org.springframework.stereotype.Component;

@Component
public class MatchComparator implements Comparator<Match> {

	@Override
	public int compare(Match match, Match otherMatch) {
		return match.getScore() - otherMatch.getScore();
	}

}