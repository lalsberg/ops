package com.lalsberg.ops;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResolutionDTO {

	@JsonProperty(value = "description")
	private String description;
	
	public ResolutionDTO(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
