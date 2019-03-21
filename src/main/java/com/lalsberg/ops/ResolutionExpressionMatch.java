package com.lalsberg.ops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resolution_expression_match")
public class ResolutionExpressionMatch {

	@ManyToOne
	@JoinColumn(name = "resolution_id")
	private Resolution resolution;

	@Column(name = "expression")
	private String expression;

	@Column(name = "match_score")
	private int matchScore;

	public int getScore() {
		return matchScore;
	}

	public boolean match(Ops ops) {
		return this.expression.matches(ops.getName()) || this.expression.matches(ops.getDescription());
	}

}