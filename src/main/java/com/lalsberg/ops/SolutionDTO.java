package com.lalsberg.ops;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolutionDTO {

	@JsonProperty(value = "id")
	private long id;

	@JsonProperty(value = "title")
	private String title;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "frequency")
	private int frequency;

	@JsonProperty(value = "archived")
	private boolean archived;

	/*
	 * Jackson eyes only
	 */
	@Deprecated
	public SolutionDTO() {
	}

	public SolutionDTO(long id, String title, String description, int frequency, boolean archived) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.frequency = frequency;
		this.archived = archived;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
	
}
