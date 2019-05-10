package com.lalsberg.ops.script;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name = "script")
public class Script {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "solution")
	private String solution;

	@Column(name = "archived")
	private boolean archived;

	/**
	 * Hibernate eyes only
	 */
	@Deprecated
	Script() {
		super();
	}

	public Script(String title, String content, String solution) {
		this.title = title;
		this.content = content;
		this.solution = solution;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getSolution() {
		return solution;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}