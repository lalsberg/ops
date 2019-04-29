package com.lalsberg.ops;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "solution_use")
public class SolutionUse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "solution_id")
	private Solution solution;

	@Column(name = "created_at", insertable = false, updatable = false)
	private LocalDateTime createdAt;

	/**
	 * Hibernate eyes only
	 */
	@Deprecated
	public SolutionUse() {
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDate getCreatedAtDate() {
		return createdAt.toLocalDate();
	}

	public SolutionUse(Solution solution) {
		this.solution = solution;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

}
