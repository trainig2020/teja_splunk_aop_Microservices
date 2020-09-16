package com.spring.Department_Service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int deptId;
	@NotNull
	@Size(min = 2,max = 30)
	private String deptName;
	

	public Department() {
		super();
	}
	

	public Department(String deptName) {
		super();
		this.deptName = deptName;
	}


	public Department(int deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
	}

	


}
