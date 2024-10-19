package com.hexaware.assetmanagement.service;
/*
 * @Author: Pritesh Rai 
 * Description: Created Service Interface class
 * Date: 12th Oct 24
 */
import com.hexaware.assetmanagement.entity.Asset;
import com.hexaware.assetmanagement.entity.Employee;
import com.hexaware.assetmanagement.myexceptions.AssetNotFoundException;
import com.hexaware.assetmanagement.myexceptions.AssetNotMaintainException;

public interface IAssetManagementBusinessService {
	boolean checkExistAssetId(int assetId) throws AssetNotFoundException;
	boolean addAsset(Asset asset);
    boolean updateAsset(Asset asset);
    boolean deleteAsset(int assetId);
    boolean allocateAsset(int allocation_id,int assetId, int employeeId, String allocationDate);
    boolean deallocateAsset(int allocationId, int employeeId, String returnDate);
    boolean performMaintenance(int maintenanceId,int assetId, String maintenanceDate, String description, double cost);
    boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate);
    boolean withdrawReservation(int reservationId);
    boolean addEmployee(Employee employee);
	boolean maintenanceDate(int assetId) throws AssetNotMaintainException;
}
