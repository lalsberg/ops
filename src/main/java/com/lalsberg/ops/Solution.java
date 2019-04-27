package com.lalsberg.ops;

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
@Table(name = "solution")
public class Solution {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Formula("(select count(*) from solution_use s where s.solution_id = id)")
	@Basic(fetch = FetchType.EAGER)
	private int frequency;

	@Column(name = "archived")
	private boolean archived;
	
	/**
	 * Hibernate eyes only
	 */
	@Deprecated
	public Solution() {
		super();
	}

	public Solution(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public boolean isArchived() {
		return this.archived;
	}

	public void archive() {
		this.archived = true;
	}

	public void markUsed(SolutionUseRepository solutionUseRepository) {
		solutionUseRepository.save(new SolutionUse(this));
	}

	public void unmarkUsed(SolutionUseRepository solutionUseRepository) {
		solutionUseRepository.findTopByOrderByIdDesc().ifPresent(solution -> solutionUseRepository.delete(solution));
	}

	public long getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
    
	public int getFrequency() {
		return frequency;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
