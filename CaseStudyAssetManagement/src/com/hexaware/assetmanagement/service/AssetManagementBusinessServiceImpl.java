package com.hexaware.assetmanagement.service;
import java.time.LocalDate;

/*
 * @Author : Rajeshwari
 * */
import com.hexaware.assetmanagement.dao.AssetManagementServiceImpl;
import com.hexaware.assetmanagement.dao.IAssetManagementService;
import com.hexaware.assetmanagement.entity.Asset;
import com.hexaware.assetmanagement.entity.Employee;
import com.hexaware.assetmanagement.myexceptions.AssetNotFoundException;
import com.hexaware.assetmanagement.myexceptions.AssetNotMaintainException;

public class AssetManagementBusinessServiceImpl implements IAssetManagementBusinessService {

	private IAssetManagementService assetManagementService;
	
	public AssetManagementBusinessServiceImpl() {
		super();
		assetManagementService = new AssetManagementServiceImpl();
	}
	//EMPLOYEE TABLE VALIDATION
	public static boolean nameValidation(String name) {
		boolean flag=false;
		if(name.length()>=3) {
			flag=true;
		}
		return flag;
	}
	public static boolean employeeIdValidation(int employeeId) {
		boolean flag=false;
		if(employeeId>=0) {
			flag=true;
		}
		return flag;
	}
	public static boolean departmentValidation(String departmentName) {
		boolean flag=false;
		if(departmentName.length()>=2) {
			flag=true;
		}
		return flag;
	}
	public static boolean emailValidation(String email) {
		boolean flag=false;
		if(email.length()>=12) {
			flag=true;
		}
		return flag;
	}
	public static boolean passwordValidation(String password) {
		boolean flag=false;
		if(password.length()>=5) {
			flag=true;
		}
		return flag;
	}
	//ASSERT TABLE VALIDATION
	public static boolean assetValidation(int assetId) {
		boolean flag=false;
		if(assetId>0) {
			flag=true;
		}
		return flag;
	}
	public static boolean assetNameValidation(String assetName) {
		boolean flag=false;
		if(assetName.length()>=2) {
			flag=true;
		}
		return flag;
	}
	public static boolean assetTypeValidation(String type) {
		boolean flag=false;
		if(type.length()>=5) {
			flag=true;
		}
		return flag;
	}
	public static boolean serialNumberValidation(String serialNumber) {
		boolean flag=false;
		if(serialNumber.length()>=1) {
			flag=true;
		}
		return flag;
	}
	public static boolean locationValidation(String location) {
		boolean flag=false;
		if(location.length()>=3) {
			flag=true;
		}
		return flag;
	}
	public static boolean ownerIdValidation(int ownerId) {
		boolean flag=false;
		if(ownerId>0) {
			flag=true;
		}
		return flag;
	}
	public static boolean returnDateValidation(LocalDate date) {
		boolean flag=false;
		
		//
		return flag;
	}
	public static boolean descriptionValidation(String description) {
		boolean flag=false;
		if(description.length()>4) {
			flag=true;
		}
		return flag;
	}
	public static boolean costValidation(double cost) {
		boolean flag=false;
		if(cost>0) {
			flag=true;
		}
		return flag;
	}
	public static boolean reservationValidation(int reservationId) {
		boolean flag=false;
		if(reservationId>0) {
			flag=true;
		}
		return flag;
	}
	

	@Override
	public boolean maintenanceDate(int assetId) throws AssetNotMaintainException {
		// TODO Auto-generated method stub
		return assetManagementService.maintenanceDate(assetId);
	}


	@Override
	public boolean checkExistAssetId(int assetId) throws AssetNotFoundException{
		// TODO Auto-generated method stub
		boolean flag=assetManagementService.checkExistAssetId(assetId);
		return flag;
	}


	@Override
	public boolean addAsset(Asset asset) {
		// TODO Auto-generated method stub
		return assetManagementService.addAsset(asset);
	}

	@Override
	public boolean updateAsset(Asset asset) {
		// TODO Auto-generated method stub
		return assetManagementService.updateAsset(asset);
	}

	@Override
	public boolean deleteAsset(int assetId) {
		// TODO Auto-generated method stub
		return assetManagementService.deleteAsset(assetId);
	}

	

	@Override
	public boolean allocateAsset(int allocationId, int assetId, int employeeId, String allocationDate) {
		// TODO Auto-generated method stub
		return assetManagementService.allocateAsset(allocationId, assetId, employeeId, allocationDate);
	}

	@Override
	public boolean deallocateAsset(int allocationId, int employeeId, String returnDate) {
		// TODO Auto-generated method stub
		return assetManagementService.deallocateAsset(allocationId, employeeId, returnDate);
	}

	@Override
	public boolean performMaintenance(int maintenanceId,int assetId, String maintenanceDate, String description, double cost) {
		// TODO Auto-generated method stub
		return assetManagementService.performMaintenance(maintenanceId,assetId, maintenanceDate, description, cost);
	}

	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return assetManagementService.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate);
	}

	@Override
	public boolean withdrawReservation(int reservationId) {
		// TODO Auto-generated method stub
		return assetManagementService.withdrawReservation(reservationId);
	}

	@Override
	public boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return assetManagementService.addEmployee(employee);
	}

	
}
