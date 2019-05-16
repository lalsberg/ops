package com.lalsberg.ops.script;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScriptDTO {

	@JsonProperty(value = "id")
	private long id;

	@JsonProperty(value = "title")
	private String title;

	@JsonProperty(value = "content")
	private String content;

	@JsonProperty(value = "solution")
	private String solution;

	@JsonProperty(value = "frequency")
	private int frequency;

	@JsonProperty(value = "archived")
	private boolean archived;

	/**
	 * Jackson eyes only
	 */
	@Deprecated
	public ScriptDTO() {
	}

	public ScriptDTO(long id, String title, String content, String solution, int frequency, boolean archived) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.solution = solution;
		this.frequency = frequency;
		this.archived = archived;
	}

	public long getId() {
		return id;
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
