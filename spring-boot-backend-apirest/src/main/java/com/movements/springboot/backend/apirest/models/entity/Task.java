package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.movements.springboot.backend.apirest.models.enums.TypeCalculationDeadline;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String description;

	@Column(name = "is_optional_subtask")
	private boolean isOptionalSubtask;

	@Column(name = "is_to_send")
	private boolean isToSend;

	@Column(name = "is_template")
	private boolean isTemplate;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "number_to_calculate_deadline_to_alarm")
	private String numberToCalculateDeadlineToAlarm;

	//@Column(name = "days_to_fix_error")
	//private Integer daysToFixError;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "type_calculation_deadline")
	private TypeCalculationDeadline typeCalculationDeadline;

	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "deadline", columnDefinition= "DATE")
	@NotNull(message="no pot estar buit")	
	//@JsonFormat(pattern = "yyyy-MM-dd")
	//@Temporal(TemporalType.DATE)
	private LocalDate deadline;

	@Column(name = "create_at", columnDefinition= "TIMESTAMP")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "tasks", "hibernateLazyInitializer", "handler", "company", "createAt" })
	@JoinColumn(name = "employee_fk")
	private Employee employee;
	
	
	@JsonProperty("company")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "tasks", "hibernateLazyInitializer", "handler", "employees", "createAt" })
	@JoinColumn(name = "company_fk")
	private Company company;

	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "task_fk")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	private List<TaskInformation> taskInformations;

	@Column(name = "is_done")
	private boolean isDone;

	@Column(name = "done_at", columnDefinition= "TIMESTAMP")
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime doneAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maintask_fk")
	@JsonIgnoreProperties(value = { "subtasks","employee", "company", "hibernateLazyInitializer", "handler", "mainTask"}, allowSetters=true)
	private Task mainTask;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mainTask")
	@JsonIgnoreProperties(value = { "mainTask", "employee", "company", "hibernateLazyInitializer", "handler", "subtasks"}, allowSetters=true)
	private List<Task> subtasks;

	@Column(name = "is_maintask")
	private boolean isMainTask;
	
	public Task() {
		taskInformations = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOptionalSubtask() {
		return isOptionalSubtask;
	}

	public boolean isToSend() {
		return isToSend;
	}

	public boolean isTemplate() {
		return isTemplate;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getNumberToCalculateDeadlineToAlarm() {
		return numberToCalculateDeadlineToAlarm;
	}

	public void setNumberToCalculateDeadlineToAlarm(String numberToCalculteDeadlineToAlarm) {
		this.numberToCalculateDeadlineToAlarm = numberToCalculteDeadlineToAlarm;
	}

	//public Integer getDaysToFixError() {
	//	return daysToFixError;
	//}

	//public void setDaysToFixError(Integer daysToFixError) {
	//	this.daysToFixError = daysToFixError;
	//}

	public TypeCalculationDeadline getTypeCalculationDeadline() {
		return typeCalculationDeadline;
	}

	public void setTypeCalculationDeadline(TypeCalculationDeadline typeCalculationDeadline) {
		this.typeCalculationDeadline = typeCalculationDeadline;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<TaskInformation> getTaskInformations() {
		return taskInformations;
	}

	public void setTaskInformations(List<TaskInformation> taskInformations) {
		
		this.taskInformations = taskInformations;
	}

	public void addTaskInformation(TaskInformation taskInformation) {
		taskInformations.add(taskInformation);
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

	public Task getMainTask() {
		return mainTask;
	}

	public void setMainTask(Task mainTask) {
		this.mainTask = mainTask;
	}

	public void setIsOptionalSubtask(boolean isOptionalSubtask) {
		this.isOptionalSubtask = isOptionalSubtask;
	}

	public void setIsToSend(boolean isToSend) {
		this.isToSend = isToSend;
	}
	
	public void setIsTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	public List<Task> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<Task> subtasks) {
		this.subtasks = subtasks;
	}

	public void addSubtask(Task subtask) {
		subtasks.add(subtask);
	}

	public boolean isDone() {
		return isDone;
	}

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isMainTask() {
		return isMainTask;
	}

	public void setIsMainTask(boolean isMainTask) {
		this.isMainTask = isMainTask;
	}

	
	
	
	public void setOptionalSubtask(boolean isOptionalSubtask) {
		this.isOptionalSubtask = isOptionalSubtask;
	}

	public void setToSend(boolean isToSend) {
		this.isToSend = isToSend;
	}

	public void setTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null)? 0: id.hashCode());
		result = prime * result + ((description == null)? 0 : description.hashCode());
		result = prime * result + (isOptionalSubtask ? 1231 : 1237);
		result = prime * result + (isToSend ? 1231 : 1237);
		result = prime * result + (isTemplate ? 1231 : 1237);
		result = prime * result + ((templateName == null )? 0 : templateName.hashCode());
		result = prime * result + ((numberToCalculateDeadlineToAlarm == null )? 0 : numberToCalculateDeadlineToAlarm.hashCode());
		result = prime * result + ((typeCalculationDeadline == null )? 0 : typeCalculationDeadline.hashCode());
		result = prime * result + ((deadline == null )? 0 : deadline.hashCode());
		result = prime * result + ((createAt == null )? 0 : createAt.hashCode());
		result = prime * result + ((employee == null )? 0 : employee.hashCode());
		result = prime * result + ((company == null )? 0 : company.hashCode());
		result = prime * result + (isDone? 1231 : 1237);
		result = prime * result + ((doneAt == null )? 0 : doneAt.hashCode());
		result = prime * result + ((mainTask == null )? 0 : mainTask.hashCode());
		result = prime * result + (isMainTask? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || !(obj instanceof Task)) {
			return false;
		}
		
		Task other = (Task) obj;
		
		if(id == null) {
			if (other.id == null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		
		if (description == null) {
			if(other.description == null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		
		if (isOptionalSubtask != other.isOptionalSubtask) {
			return false;
		}
		
		if (isToSend != other.isToSend) {
			return false;
		}
		if (isTemplate != other.isTemplate) {
			return false;
		}
		
		if (templateName == null) {
			if(other.templateName == null) {
				return false;
			}
		} else if (!templateName.equals(other.templateName)) {
			return false;
		}
		
		if (numberToCalculateDeadlineToAlarm == null) {
			if(other.numberToCalculateDeadlineToAlarm == null) {
				return false;
			}
		} else if (!numberToCalculateDeadlineToAlarm.equals(other.numberToCalculateDeadlineToAlarm)) {
			return false;
		}
		
		if (typeCalculationDeadline == null) {
			if(other.typeCalculationDeadline == null) {
				return false;
			}
		} else if (!typeCalculationDeadline.equals(other.typeCalculationDeadline)) {
			return false;
		}
		
		if (deadline == null) {
			if(other.deadline == null) {
				return false;
			}
		} else if (!deadline.equals(other.deadline)) {
			return false;
		}
		
		if (createAt == null) {
			if(other.createAt == null) {
				return false;
			}
		} else if (!createAt.equals(other.createAt)) {
			return false;
		}
		
		if (employee == null) {
			if(other.employee == null) {
				return false;
			}
		} else if (!employee.equals(other.employee)) {
			return false;
		}
		
		if (company == null) {
			if(other.company == null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		
		if (isDone != other.isDone) {
			return false;
		}
		
		if (doneAt == null) {
			if(other.doneAt == null) {
				return false;
			}
		} else if (!doneAt.equals(other.doneAt)) {
			return false;
		}
		
		if (mainTask == null) {
			if(other.mainTask == null) {
				return false;
			}
		} else if (!mainTask.equals(other.mainTask)) {
			return false;
		}
		
		if (isMainTask != other.isMainTask) {
			return false;
		}
		
		return true;
	}


	private static final long serialVersionUID = 1L;

}
