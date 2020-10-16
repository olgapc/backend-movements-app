package com.movements.springboot.backend.apirest.models.pks;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.movements.springboot.backend.apirest.models.entity.Task;
import com.movements.springboot.backend.apirest.models.entity.Information;;

@Embeddable
public class TaskInformationPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "task_fk")
	private Task task;

	@ManyToOne
	@JoinColumn(name = "information_fk")
	private Information information;

	public TaskInformationPK() {

	}
	
	public TaskInformationPK(Task task, Information information) {
		this.task = task;
		this.information = information;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result =1;
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((information ==  null ) ? 0 : information.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof TaskInformationPK)) {
			return false;
		}
		
		TaskInformationPK other = (TaskInformationPK) obj;
		
		if(information == null) {
			if (other.information == null) {
				return false;
			}
		} else if (!information.equals(other.information)) {
			return false;
		}
		
		if (task == null) {
			if(other.task == null) {
				return false;
			}
		} else if (!task.equals(other.task)) {
			return false;
		}
		return true;
		
	}

	private static final long serialVersionUID = 1L;

}
