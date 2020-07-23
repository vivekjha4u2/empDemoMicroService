package com.infy.employeeclientModel;

import java.util.List;

public class EmpList {
	private List<Employee> empList;

	public EmpList() {
	}
	public EmpList(List<Employee> empList) {
		super();
		this.empList = empList;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
}
