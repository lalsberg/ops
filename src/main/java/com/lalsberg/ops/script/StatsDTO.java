package com.lalsberg.ops.script;

import java.time.LocalDate;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDTO {

	@JsonProperty("totalByDate")
	Map<LocalDate, Long> totalByDate;

	public StatsDTO(Map<LocalDate, Long> totalByDate) {
		this.totalByDate = totalByDate;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

}
