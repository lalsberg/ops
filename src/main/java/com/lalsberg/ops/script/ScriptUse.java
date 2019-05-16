package com.lalsberg.ops.script;

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

import com.lalsberg.ops.script.Script;

@Entity
@Table(name = "script_use")
public class ScriptUse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "script_id")
	private Script script;

	@Column(name = "created_at", insertable = false, updatable = false)
	private LocalDateTime createdAt;

	/**
	 * Hibernate eyes only
	 */
	@Deprecated
	public ScriptUse() {
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDate getCreatedAtDate() {
		return createdAt.toLocalDate();
	}

	public ScriptUse(Script script) {
		this.script = script;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

}
