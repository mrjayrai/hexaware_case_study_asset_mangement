package com.hexaware.assetmanagement.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hexaware.assetmanagement.entity.Employee;

public class InputValidation {
	public boolean chechEmployee(Employee employee) {
		if(employee.getEmployeeId()<0) {
			System.err.println("Invalid Employee ID");
			return false;
		}
		String NAME_REGEX = "^[a-zA-Z\\s]+$";
		if(employee.getEmployeeName().length()>3) {
			Pattern patternName = Pattern.compile(NAME_REGEX);
			Matcher nameMatcher = patternName.matcher(employee.getEmployeeName());
			if(!nameMatcher.matches()) {
				System.err.println("Employee Name Should be greater then 3 letter's and should not contain number or numeric values");
				return false;
			}
			
		}
		String email = employee.getEmployeeEmail();
		String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern patternEmail = Pattern.compile(EMAIL_REGEX);
		Matcher emailMatcher = patternEmail.matcher(email);
		if(!emailMatcher.matches()) {
			System.err.println("Invalid Employee email");
			return false;
		}
		String password = employee.getEmployeePassword();
		String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		Pattern patternPassword = Pattern.compile(PASSWORD_REGEX);
		Matcher passwordMatcher = patternPassword.matcher(password);
		if(!passwordMatcher.matches()) {
			System.err.println("Password should contain\n1 Uppercase\n1 LowerCase\n1 Number\n1 Special character and of length of atleast 8 ");
			return false;
		}
		return true;
	}
}
