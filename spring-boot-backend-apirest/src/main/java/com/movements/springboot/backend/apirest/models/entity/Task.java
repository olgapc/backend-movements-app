package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.movements.springboot.backend.apirest.models.enums.TimesType;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

	//******GENERAL ATTRIBUTES******
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
		)//(strategy = GenerationType.IDENTITY)
	@Type(type="uuid-char")
	@Column(length = 36)
	private UUID id;

	@Column(name = "create_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createAt;
	
	@Column(name = "is_to_send")
	private boolean isToSend;

	public String comment;

	@Column(name = "type_calculation_deadline")
	@Enumerated(EnumType.STRING)
	private TimesType typeCalculationDeadline;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "task_fk")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<TaskInformation> taskInformations;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "createAt", "currentAssignedTasks" }, allowSetters = true)
	@JoinColumn(name="assigned_user")
	private AppUser currentAssignedUser;
	
	
	@Column(name = "is_periodically")
	private boolean isPeriodically;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pretask", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "mainTask", "hibernateLazyInitializer", "handler",
			"pretask" }, allowSetters = true)
	private List<TaskSequence> subtasks;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subtask", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "mainTask", "hibernateLazyInitializer", "handler",
			"subtask", "pretask" }, allowSetters = true)
	private List<TaskSequence> pretasks;
	
	
	//********ONLY TEMPLATE ATTRIBUTES********
	@Column(name = "is_template")
	private boolean isTemplate;

	@Column(name = "template_name", nullable= true)
	@Size(max = 25, message = "ha de tenir màxim 25 caràcters")
	private String templateName;
	
	
	
	
	//********ONLY FOR ALL TASK ATTRIBUTES (NOT TEMPLATE)*********
	
	//@NotEmpty
	@Column(nullable = true)
	@Size(min = 3, max = 80, message = "ha de tenir entre 3 i 80 caràcters")
	private String description;
	
	@Column(name = "is_done")
	private boolean isDone;

	@Column(name = "done_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime doneAt;
	
	@Column(name= "done_by")
	private AppUser doneBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maintask_fk")
	@JsonIgnoreProperties(value = { "subtasks", "employee", "company", "hibernateLazyInitializer", "handler",
			"mainTask" }, allowSetters = true)
	private Task mainTask;

	@Column(name = "is_visible")
	private boolean isVisible;

	@Column(name = "deadline", columnDefinition = "DATE")
	//@NotNull(message = "no pot estar buit")
	private LocalDate deadline;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "tasks", "hibernateLazyInitializer", "handler", "company", "createAt" })
	@JoinColumn(name = "employee_fk")
	private Employee employee;

	@JsonProperty("company")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "tasks", "hibernateLazyInitializer", "handler", "employees", "createAt" })
	@JoinColumn(name = "company_fk")
	private Company company;
	
	
	//*******ONLY MAINTASK ATTRIBUTES*********
	@Column(name = "is_maintask")
	private boolean isMainTask;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "task_fk")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<TaskInformation> taskInformationsStickedToMainTask;


	
	
	//*******ONLY SUBTASK ATTRIBUTES*********
	@Column(name = "number_to_calculate_deadline")
	private String numberToCalculateDeadline;
	
	
	
	//****CONSTRUCTOR*****
	
	public Task() {
		taskInformations = new ArrayList<TaskInformation>();
		subtasks = new ArrayList<>();
		pretasks = new ArrayList<>();
		taskInformationsStickedToMainTask = new ArrayList<>();
	}

	//***PREPERSIST*******
	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();
	}

	//****GETTERS AND SETTERS ******
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsToSend() {
		return isToSend;
	}

	public boolean getIsTemplate() {
		return isTemplate;
	}

	public boolean getIsVisible() {
		return isVisible;
	}

	public boolean getIsPeriodically() {
		return isPeriodically;
	}

	public void setIsPeriodically(boolean isPeriodically) {
		this.isPeriodically = isPeriodically;
	}

	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getNumberToCalculateDeadline() {
		return numberToCalculateDeadline;
	}

	public void setNumberToCalculateDeadline(String numberToCalculteDeadline) {
		this.numberToCalculateDeadline = numberToCalculteDeadline;
	}

	public TimesType getTypeCalculationDeadline() {
		return typeCalculationDeadline;
	}

	public void setTypeCalculationDeadline(TimesType typeCalculationDeadline) {
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



	public List<TaskSequence> getPretasks() {
		return pretasks;
	}

	public void setPretasks(List<TaskSequence> pretasks) {
		this.pretasks = pretasks;
	}
	
	
	public List<TaskSequence> getSubtasks() {
		return subtasks;
	}
	
	
	public void setSubtasks(List<TaskSequence> subtasks) {
		this.subtasks = subtasks;
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

	public AppUser getCurrentAssignedUser() {
		return currentAssignedUser;
	}

	public void setCurrentAssignedUser(AppUser currentAssignedUser) {
		this.currentAssignedUser = currentAssignedUser;
	}

	public AppUser getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(AppUser doneBy) {
		this.doneBy = doneBy;
	}

	public void setMainTask(boolean isMainTask) {
		this.isMainTask = isMainTask;
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

	public void setIsToSend(boolean isToSend) {
		this.isToSend = isToSend;
	}

	public void setIsTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}


	public boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean getIsMainTask() {
		return isMainTask;
	}

	public void setIsMainTask(boolean isMainTask) {
		this.isMainTask = isMainTask;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public List<TaskInformation> getTaskInformationsStickedToMainTask() {
		return taskInformationsStickedToMainTask;
	}

	public void setTaskInformationsStickedToMainTask(List<TaskInformation> taskInformationsStickedToMainTask) {
		this.taskInformationsStickedToMainTask = taskInformationsStickedToMainTask;
	}
	



	//*******ADD METHODS******
	
	public void addTaskInformation(TaskInformation taskInformation) {
		taskInformations.add(taskInformation);
	}

	public void addSubtask(TaskSequence subtask) {
		if(subtasks.contains(subtask)) return;
		
		subtasks.add(subtask);
		subtask.setPretask(this);
		
	}
	
	public void addPretask(TaskSequence pretask) {
		if(pretasks.contains(pretask)) return;
		
		pretasks.add(pretask);
		pretask.setPretask(this);
		
	}

	
	//******HASHCODE AND EQUALS METHODS******
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isToSend ? 1231 : 1237);
		result = prime * result + (isTemplate ? 1231 : 1237);
		result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
		result = prime * result
				+ ((numberToCalculateDeadline == null) ? 0 : numberToCalculateDeadline.hashCode());
		result = prime * result + ((typeCalculationDeadline == null) ? 0 : typeCalculationDeadline.hashCode());
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + ((createAt == null) ? 0 : createAt.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + (isDone ? 1231 : 1237);
		result = prime * result + ((doneAt == null) ? 0 : doneAt.hashCode());
		result = prime * result + ((mainTask == null) ? 0 : mainTask.hashCode());
		result = prime * result + (isMainTask ? 1231 : 1237);
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
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

		if (id == null) {
			if (other.id == null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		if (description == null) {
			if (other.description == null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}

		if (isToSend != other.isToSend) {
			return false;
		}
		if (isTemplate != other.isTemplate) {
			return false;
		}

		if (templateName == null) {
			if (other.templateName == null) {
				return false;
			}
		} else if (!templateName.equals(other.templateName)) {
			return false;
		}

		if (numberToCalculateDeadline == null) {
			if (other.numberToCalculateDeadline == null) {
				return false;
			}
		} else if (!numberToCalculateDeadline.equals(other.numberToCalculateDeadline)) {
			return false;
		}

		if (typeCalculationDeadline == null) {
			if (other.typeCalculationDeadline == null) {
				return false;
			}
		} else if (!typeCalculationDeadline.equals(other.typeCalculationDeadline)) {
			return false;
		}

		if (deadline == null) {
			if (other.deadline == null) {
				return false;
			}
		} else if (!deadline.equals(other.deadline)) {
			return false;
		}

		if (createAt == null) {
			if (other.createAt == null) {
				return false;
			}
		} else if (!createAt.equals(other.createAt)) {
			return false;
		}

		if (employee == null) {
			if (other.employee == null) {
				return false;
			}
		} else if (!employee.equals(other.employee)) {
			return false;
		}

		if (company == null) {
			if (other.company == null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}

		if (isDone != other.isDone) {
			return false;
		}

		if (doneAt == null) {
			if (other.doneAt == null) {
				return false;
			}
		} else if (!doneAt.equals(other.doneAt)) {
			return false;
		}

		if (mainTask == null) {
			if (other.mainTask == null) {
				return false;
			}
		} else if (!mainTask.equals(other.mainTask)) {
			return false;
		}

		if (isMainTask != other.isMainTask) {
			return false;
		}

		if (comment == null) {
			if (other.comment == null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}

		return true;
	}

	private static final long serialVersionUID = 1L;

}
