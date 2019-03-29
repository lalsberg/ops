package com.lalsberg.ops;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpsDTO {

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "description")
	private String description;

	@Deprecated //Jackson eyes only
	OpsDTO() {
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
