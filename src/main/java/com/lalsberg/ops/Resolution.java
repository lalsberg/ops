package com.lalsberg.ops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resolution")
public class Resolution {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "description")
	private String description;

	public Match match(Ops ops, ExpressionMatcher expressionMatcher) {
		int score = expressionMatcher.getScore(this, ops);

		return new Match(this, ops, score);
	}

}
