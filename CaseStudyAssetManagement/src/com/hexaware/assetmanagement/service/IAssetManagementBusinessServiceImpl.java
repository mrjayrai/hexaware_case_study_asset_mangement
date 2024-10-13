package com.hexaware.assetmanagement.service;

import com.hexaware.assetmanagement.dao.AssetManagementServiceImpl;
import com.hexaware.assetmanagement.dao.IAssetManagementService;
import com.hexaware.assetmanagement.entity.Asset;
import com.hexaware.assetmanagement.entity.Employee;

public class IAssetManagementBusinessServiceImpl implements IAssetManagementBusinessService {

	private IAssetManagementService assetManagementService;
	
	public IAssetManagementBusinessServiceImpl() {
		super();
		assetManagementService = new AssetManagementServiceImpl();
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
	public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
		// TODO Auto-generated method stub
		return assetManagementService.allocateAsset(assetId, employeeId, allocationDate);
	}

	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
		// TODO Auto-generated method stub
		return assetManagementService.deallocateAsset(assetId, employeeId, returnDate);
	}

	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
		// TODO Auto-generated method stub
		return assetManagementService.performMaintenance(assetId, maintenanceDate, description, cost);
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
