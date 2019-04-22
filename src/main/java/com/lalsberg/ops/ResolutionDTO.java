package com.lalsberg.ops;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResolutionDTO {

	@JsonProperty(value = "title")
	private String title;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "frequency")
	private int frequency;

	public ResolutionDTO(String title, String description, int frequency) {
		this.title = title;
		this.description = description;
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
