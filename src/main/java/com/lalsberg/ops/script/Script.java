package com.lalsberg.ops.script;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Formula;

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

	@Formula("(select count(*) from script_use s where s.script_id = id)")
	@Basic(fetch = FetchType.EAGER)
	private int frequency;

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

	public void markUsed(ScriptUseRepository scriptUseRepository) {
		scriptUseRepository.save(new ScriptUse(this));
	}

	public void unmarkUsed(ScriptUseRepository scriptUseRepository) {
		scriptUseRepository.findTopByOrderByIdDesc().ifPresent(script -> scriptUseRepository.delete(script));
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public int getFrequency() {
		return frequency;
	}

	public boolean isArchived() {
		return archived;
	}

	public void archive() {
		this.archived = true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}