package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

//import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "task_informations")
public class TaskInformation implements Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@JoinColumn(name= "information_fk")
	private Information information;

	//@NotNull
	@Column(name = "create_at", columnDefinition= "TIMESTAMP")
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

	private String comment;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "done_at")
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime doneAt;

	@Column(name = "done")
	private boolean done;

	public TaskInformation() {

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();
	}

	public LocalDateTime getDoneAt() {
		return doneAt;
	}

	public void setDoneAt(LocalDateTime doneAt) {
		this.doneAt = doneAt;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	
	private static final long serialVersionUID = 1L;

}
