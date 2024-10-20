package com.hexaware.assetmanagement.service;
/*@ Author : Rajeshwari
 * Description : Implemented Input Validation
 * Date: 18-10-2024
 */
/*@ Author : Pritesh Rai
 * Description : Implemented InputString Validation using Regex
 * Date: 18-10-2024
 */

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hexaware.assetmanagement.dao.AssetManagementServiceImpl;
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
		if(employee.getEmployeeName().length()>=3) {
			Pattern patternName = Pattern.compile(NAME_REGEX);
			Matcher nameMatcher = patternName.matcher(employee.getEmployeeName());
			if(!nameMatcher.matches()) {
				System.err.println("Employee Name Should be greater than 2 letter's and should not contain number or symbolic values");
				return false;
			}
			
		}else {
			System.err.println("Name should be greater than 2 letters");
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
		if(employee.getEmployeeDepartment().length()<2) {
			System.err.println("Enter Valid Department");
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
		
		if(asset.getAssetName().length()<3) {
			System.err.println("Asset Name should be greater than 3 letters");
			return false;
		}
		
		if(asset.getSerialNumber().length() == 5) {
			
		}else {
			System.err.println("Serial Number Length should be only 5 characters");
			return false;
		}
		LocalDate date = asset.getPurchaseDate();
		if(date.isAfter(LocalDate.now())) {
			System.err.println("Please select present date or date in the past");
			return false;
		}
		if(!AssetManagementServiceImpl.checkExistEmployeeId(asset.getOwnerId())) {
			System.err.println("Owner not exist");
			return false;
		}
		
		return true;
	}
	public boolean checkUpdateDetails(String location,String status) {
		if(location.length()<2) {
			System.err.println("Location should have minimum two character");
			return false;
		}
		if(status.length()<2) {
			System.err.println("Please Enter valid status");
			return false;
		}
		return true;
	}
	public boolean checkAllocation(int allocationId,int allocateAssetId,int allocateEmployeeId,String allocationDate) {
		if(allocationId<0) {
			System.err.println("Allocation Id should be positive");
			return false;
		}
		try {
			if(!AssetManagementServiceImpl.checkExistAssetIdInAllocations(allocateAssetId)) {
				System.err.println("Asset Already Allocated");
				return false;
			}
		} catch (AssetNotFoundException e) {
			// TODO Auto-generated catch block
//			System.out.println("Asset Already Allocated");
		}
		if(!AssetManagementServiceImpl.checkExistEmployeeId(allocateEmployeeId)) {
		System.err.println("Employee not found");
			return false;
		}
		LocalDate allocationDatee=LocalDate.parse(allocationDate);
		if(allocationDatee.isBefore(LocalDate.now())){
			System.err.println("Allocation date should not be in past");
			return false;
		}
		return true;
	}
	public boolean checkDeallocationDate(int allocationId,String deallocationDate,int employeeId) {
		LocalDate allocationDate=AssetManagementServiceImpl.getAllocationDate(allocationId);
//		System.out.println(allocationDate);
		if(allocationDate == null) {
			System.err.println("Please enter valid allocation ID");
			return false;
		}
		LocalDate deallocationDates=LocalDate.parse(deallocationDate);
		if(allocationDate.isAfter(deallocationDates)) {
			System.err.println("Delllocation date should be after Allocation date");
			return false;
		}
		if(!AssetManagementServiceImpl.checkExistEmployeeId(employeeId)) {
			System.err.println("Employee not found validation");
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
//		if (maintenanceDescription.isEmpty() || maintenanceDescription.length() > 255) {
//	        System.err.println("Maintenance description must be between 1 and 255 characters");
//	        return false;
//	    }
		if(maintenanceDescription.length() < 5) {
			System.err.println("Description length must be greater than 5");
		}
//		
		if (maintenanceCost <=0) {
	        System.err.println("Maintenance cost must be greater than 0");
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
		try {
			boolean reservationCheck=AssetManagementServiceImpl.checkAssetIdInReservations(reserveAssetId);
		if(!reservationCheck) {
			System.err.println("Asset Already Reserved");
			return false;
		}
		} catch (AssetNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
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
		if(!AssetManagementServiceImpl.checkExistEmployeeId(reserveEmployeeId)) {
			System.err.println("Employee not exist");
			return false;
		}
		return true;
	}
}
