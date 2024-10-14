package com.hexaware.assetmanagement.entity;
/*
 * @Author: Pritesh Rai 
 * Description: Created POJO class for Employees
 * Date: 12th Oct 24
 */

public class Employee {
	private int employeeId;
	private String employeeName;
	private String employeeDepartment ;
	private String employeeEmail ;
	private String employeePassword ;
	
	
	public Employee() {
		super();
	}


	public Employee(int employeeId, String employeeName, String employeeDepartment, String employeeEmail,
			String employeePassword) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeDepartment = employeeDepartment;
		this.employeeEmail = employeeEmail;
		this.employeePassword = employeePassword;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeeDepartment() {
		return employeeDepartment;
	}


	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}


	public String getEmployeeEmail() {
		return employeeEmail;
	}


	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}


	public String getEmployeePassword() {
		return employeePassword;
	}


	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	
	
	
}
