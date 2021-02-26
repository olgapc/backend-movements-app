package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.ManyToOne;


@Entity
@Table(name = "task_sequences")
public class TaskSequence implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "create_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createAt;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	@JoinColumn(name="subtask_fk")
	private Task subtask;
	
	private int position;
	
	/*
	 * @ManyToOne(fetch=FetchType.LAZY)
	 * 
	 * @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler",
	 * "subtasks" }, allowSetters = true)
	 * 
	 * @JoinColumn(name="pretask_fk") private Task pretask;
	 */

	private String comment;

	public TaskSequence() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
	/*
	 * public Task getSubtask() { return subtask; }
	 * 
	 * public void setSubtask(Task subtask) { this.subtask = subtask; }
	 */

	
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Task getSubtask() {
		return subtask;
	}

	public void setSubtask(Task pretask) {
		this.subtask = pretask;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private static final long serialVersionUID = 1L;
}
