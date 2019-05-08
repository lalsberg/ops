package com.lalsberg.ops.script;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.lalsberg.ops.Solution;

@Entity
@Table(name = "script")
public class Script {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "script")
	private String script;

	@ManyToOne
	@JoinColumn(name = "solution_id")
	private Solution solution;

	@Column(name = "archived")
	private boolean archived;

	/**
	 * Hibernate eyes only
	 */
	@Deprecated
	Script() {
		super();
	}

	public String getScript() {
		return script;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
