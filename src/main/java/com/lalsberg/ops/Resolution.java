package com.lalsberg.ops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "resolution")
public class Resolution {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "description")
	@JsonProperty(value = "description")
	private String description;

	public Match match(Ops ops, ExpressionMatcher expressionMatcher) {
		int score = expressionMatcher.getScore(this, ops);
 
		System.out.println(score);

		return new Match(this, ops, score);
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
