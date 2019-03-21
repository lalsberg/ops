package com.lalsberg.ops;

public class Match {

	private Resolution resolution;
	private Ops ops;
	private int score;

	@Deprecated //Hibernate eyes only
	Match() {}

	public Match(Resolution resolution, Ops ops, int score) {
		this.resolution = resolution;
		this.ops = ops;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public Resolution getResolution() {
		return resolution;
	}

}
