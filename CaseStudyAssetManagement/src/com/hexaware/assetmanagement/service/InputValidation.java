package com.hexaware.assetmanagement.service;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hexaware.assetmanagement.entity.Asset;
import com.hexaware.assetmanagement.entity.Employee;
import com.hexaware.assetmanagement.myexceptions.AssetNotFoundException;

public class InputValidation {
	public IAssetManagementBusinessService service = new AssetManagementBusinessServiceImpl();
	public boolean checkEmployee(Employee employee) {
		if(employee.getEmployeeId()<=0) {
			System.err.println("Invalid Employee ID");
			return false;
		}
		String NAME_REGEX = "^[a-zA-Z\\s]+$";
		if(employee.getEmployeeName().length()>3) {
			Pattern patternName = Pattern.compile(NAME_REGEX);
			Matcher nameMatcher = patternName.matcher(employee.getEmployeeName());
			if(!nameMatcher.matches()) {
				System.err.println("Employee Name Should be greater then 3 letter's and should not contain number or symbolic values");
				return false;
			}
			
		}else {
			System.err.println("Name should be greater then the 3 letter's");
			return false;
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
	
	
	public boolean checkAsset(Asset asset) {
		try {
			if(service.checkExistAssetId(asset.getAssetId())) {
				System.err.println("Asset ID already exits");
				return false;
			};
		} catch (AssetNotFoundException e) {
		}
		
		if(asset.getAssetName().length()<4) {
			System.err.println("Asset Name should be greater than 4 letters");
			return false;
		}
		
		if(asset.getSerialNumber().length() == 5) {
			
		}else {
			System.err.println("Length should be only 5 characters");
			return false;
		}
		LocalDate date = asset.getPurchaseDate();
		if(date.isAfter(LocalDate.now())) {
			System.err.println("Please select present date or date in the past");
			return false;
		}
		
		return true;
	}
	
	public boolean checkMaintenance(int maintenanceId,int maintenanceAssetId,
						String maintenanceDate,String maintenanceDescription,double maintenanceCost) {
		
		try {
	       service.checkExistAssetId(maintenanceAssetId);
	       
	    } catch (AssetNotFoundException e) {
	    	System.err.println("Asset Id does not exist");
		       return false;
	    }
		LocalDate startDate = LocalDate.parse(maintenanceDate);
		if(startDate.isAfter(LocalDate.now())) {
			System.err.println("Maintenance date cannot be in the future");
			return false;
		}
		if (maintenanceDescription.isEmpty() || maintenanceDescription.length() > 255) {
	        System.err.println("Maintenance description must be between 1 and 255 characters");
	        return false;
	    }
		
		if (maintenanceCost < 0) {
	        System.err.println("Maintenance cost must be greater then 0");
	        return false;
	    }
		return true;
	}
	
	public boolean checkReservation(int reserveAssetId, int reserveEmployeeId,String reserveDate, String startDate,
			String endDate) {
		
		try {
	        service.checkExistAssetId(reserveAssetId);
	    } catch (AssetNotFoundException e) {
	    	System.err.println("Asset Id does not exist");
	        return false;
	    }
		
		
		
		LocalDate localReserveDate = LocalDate.parse(reserveDate);
		
		if(localReserveDate.isBefore(LocalDate.now())) {
			System.err.println("Reservation date cannot be in the past");
	        return false;
		}
		
		LocalDate localStartDate = LocalDate.parse(startDate);
		LocalDate localEndDate = LocalDate.parse(endDate);
		
		if(localReserveDate.isAfter(localStartDate)) {
			System.err.println("Reservation Date cannot be after reserve date");
			return false;
		}
		
		if(localStartDate.isAfter(localEndDate)) {
			System.err.println("End date cannot be before the start date");
	        return false;
		}
		return true;
	}
}
