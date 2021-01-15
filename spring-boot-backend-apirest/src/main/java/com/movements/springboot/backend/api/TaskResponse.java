package com.movements.springboot.backend.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.movements.springboot.backend.apirest.models.entity.Company;
import com.movements.springboot.backend.apirest.models.entity.Employee;
import com.movements.springboot.backend.apirest.models.entity.Task;
import com.movements.springboot.backend.apirest.models.entity.TaskInformation;
import com.movements.springboot.backend.apirest.models.enums.TimesType;

public class TaskResponse {

	private Long id;

	private String description;

	private boolean isOptionalSubtask;

	private boolean isToSend;

	private boolean isTemplate;

	private String templateName;

	private String numberToCalculateDeadlineToAlarm;

	private TimesType typeCalculationDeadline;

	private LocalDate deadline;

	private LocalDateTime createAt;

	private Employee employee;

	private Company company;

	private List<TaskInformation> taskInformations;

	private boolean isDone;

	private LocalDateTime doneAt;

	private Task mainTask;

	private List<Task> subtasks;

	private boolean isMainTask;

	public String comment;

	public TaskResponse(Task task) {
		this.id = task.getId();
		this.description = task.getDescription();
		this.createAt = task.getCreateAt();
		this.deadline = task.getDeadline();
	}

	public Long getId() {
		return id;
	}


	public String getDescription() {
		return description;
	}

	public boolean isOptionalSubtask() {
		return isOptionalSubtask;
	}

	public boolean getIsToSend() {
		return isToSend;
	}

	public boolean getIsTemplate() {
		return isTemplate;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getNumberToCalculateDeadlineToAlarm() {
		return numberToCalculateDeadlineToAlarm;
	}

	public TimesType getTypeCalculationDeadline() {
		return typeCalculationDeadline;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public Company getCompany() {
		return company;
	}

	public Employee getEmployee() {
		return employee;
	}

	public List<TaskInformation> getTaskInformations() {
		return taskInformations;
	}

	public LocalDateTime getDoneAt() {
		return doneAt;
	}

	public Task getMainTask() {
		return mainTask;
	}

	public List<Task> getSubtasks() {
		return subtasks;
	}

	public boolean getIsDone() {
		return isDone;
	}

	public boolean getIsMainTask() {
		return isMainTask;
	}

	public String getComment() {
		return comment;
	}

}
