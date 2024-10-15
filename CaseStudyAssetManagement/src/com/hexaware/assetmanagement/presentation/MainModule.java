package com.hexaware.assetmanagement.presentation;
/*@ Author : Rajeshwari
 * 
 */
import java.time.LocalDate;
import java.util.Scanner;

import com.hexaware.assetmanagement.entity.Asset;
import com.hexaware.assetmanagement.entity.Employee;
import com.hexaware.assetmanagement.service.IAssetManagementBusinessService;
import com.hexaware.assetmanagement.service.IAssetManagementBusinessServiceImpl;

public class MainModule {
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		IAssetManagementBusinessService service=new IAssetManagementBusinessServiceImpl();
		boolean flag=true;
		while(flag) {
			System.out.println("ASSET MANAGEMENT SYSTEM");
			System.out.println("1.Add Employee");
			System.out.println("2.Add Asset");
			System.out.println("3.Update location and status of Asset");
			System.out.println("4.Delete from Asset");
			System.out.println("5.Allocate Asset");
			System.out.println("6.Deallocate Asset");
			System.out.println("7.Perfom Maintenance");
			System.out.println("8.Reserve Asset");
			System.out.println("9.Withdraw Reservation");
			int choice=scanner.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter Employee Id");
				int employeeId=scanner.nextInt();
				System.out.println("Enter Employee Name");
				String employeeName=scanner.next();
				System.out.println("Enter Department");
				String  department=scanner.next();
				System.out.println("Enter Email");
				String email=scanner.next();
				System.out.println("Enter Employee Password");
				String password=scanner.next();
				Employee employee=new Employee(employeeId,employeeName,department,email,password);
				boolean addEmployee=service.addEmployee(employee);
				if(addEmployee) {
					System.out.println("Employee added successfully");
				}
				else {
					System.err.println("EMPLOYEE NOT ADDED");
				}
				break;
				
			case 2:
				System.out.println("Enter Asset Id");
				int assetId=scanner.nextInt();
				System.out.println("Enter Asset Name");
				String assetName=scanner.next();
				System.out.println("Enter Asset Type");
				String assetType=scanner.next();
				System.out.println("Enter Serial Number");
				String serialNumber=scanner.next();
				System.out.println("Enter Purchse Date");
				String date=scanner.next();
				LocalDate purchaseDate=LocalDate.parse(date);
				System.out.println("Enter location");
				String location=scanner.next();
				System.out.println("Enter asset status");
				String assetStatus=scanner.next();
				System.out.println("Enter owner id");
				int ownerId=scanner.nextInt();
				Asset asset=new Asset(assetId,assetName,assetType,serialNumber,purchaseDate,location,assetStatus,ownerId);
				boolean assetAddCheck=service.addAsset(asset);
				if(assetAddCheck) {
					System.out.println("Asset Added Successfully");
				}
				else {
					System.out.println("Asset not Added");
				}
				break;
			case 3:
				System.out.println("Enter Asset Id to Update ");
				int updateAssetId=scanner.nextInt();
				
				System.out.println("Enter New Location");
				String newLocation=scanner.next();
				System.out.println("Enter new Status");
				String newStatus=scanner.next();
				Asset updateAsset=new Asset();
				updateAsset.setAssetId(updateAssetId);
				updateAsset.setLocation(newLocation);
				updateAsset.setAssetStatus(newStatus);
				boolean updateAssetDetail=service.updateAsset(updateAsset);
				if(updateAssetDetail) {
					System.out.println("Asset details updated successfully");
				}
				else {
					System.err.println("Problem with asset update");
				}
				break;
			case 4:
				System.out.println("Enter Asset Id to delete the particular asset");
			    int deleteAssetId=scanner.nextInt();
			    boolean deleteAsset=service.deleteAsset(deleteAssetId);
			    if(deleteAsset) {
			    	System.out.println("Asset Deleted Successfully");
			    }
			    else {
			    	System.err.println("Poblem with asset deletion");
			    }
			    break;
			case 5:
				System.out.println("Enter Allocation id");
				int allocationId=scanner.nextInt();
				System.out.println("Enter Asset Id");
				int alocateAssetId=scanner.nextInt();
				System.out.println("Enter Employee id");
				int allocateEmployeeId=scanner.nextInt();
				System.out.println("Enter Allocation Date");
				String allocationDate=scanner.next();
				boolean allocateAsset=service.allocateAsset(allocationId,alocateAssetId, allocateEmployeeId, allocationDate);
				if(allocateAsset) {
					System.out.println("Asset ALoocated Successsfully");
				}
				else {
					System.err.println("Problem with asset allocation");
				}
				break;
			case 6:
				System.out.println("Enter Allocation Id");
				int dealloacteAllocationId=scanner.nextInt();
				System.out.println("Enter Employee Id");
				int deallocationEmployeeId=scanner.nextInt();
				System.out.println("Enter  Deallocation date");
				String deallocationDate=scanner.next();
				boolean checkDeallocation=service.deallocateAsset(dealloacteAllocationId, deallocationEmployeeId, deallocationDate);
				if(checkDeallocation) {
					System.out.println("Deallocation done sucessfully");
				}
				else {
					System.err.println("Problem with dealloaction of asset");
				}
				break;
				
			case 7:
				System.out.println("Enter Asset Id");
				int maintenanceAssetId=scanner.nextInt();
				System.out.println("Enter Maintenance Date");
				String maintenanceDate=scanner.next();
				System.out.println("Enter Description");
				String maintenanceDescription=scanner.next();
				System.out.println("Enter maintenance Cost");
				double maintenanceCost=scanner.nextDouble();
				boolean checkMaintenance=service.performMaintenance(maintenanceAssetId, maintenanceDate, maintenanceDescription, maintenanceCost);
				if(checkMaintenance) {
					System.out.println("Maintenance performed successfully");
				}
				else {
					System.err.println("Problem with performing maintenance");
				}
				break;
			case 8:
				System.out.println("Enter Asset Id for Reservation");
				int reserveAssetId=scanner.nextInt();
				System.out.println("Enter Employee Id");
				int reserveEmployeeId=scanner.nextInt();
				System.out.println("Enter Reserve Date");
				String reserveDate=scanner.next();
				System.out.println("Enter Start Date");
				String startDate=scanner.next();
				System.out.println("Enter End Date");
				String endDate=scanner.next();
				boolean checkReserve=service.reserveAsset(reserveAssetId, reserveEmployeeId, reserveDate, startDate, endDate);
				if(checkReserve) {
					System.out.println("Asset Reserved successfully");
				}
				else {
					System.err.println("Problem with Asset reservation");
				}
				break;
			
			case 9:
				System.out.println("Enter Reservation Id");
				int withdrawReservationId=scanner.nextInt();
				boolean checkWithdrawReservation=service.withdrawReservation(withdrawReservationId);
				if(checkWithdrawReservation) {
					System.out.println("Withdraw Reservation done");
				}
				else {
					System.err.println("Problem with Withdraw Reservation ");
				}
				break;
				
				
				}
				
				
				
				
				
				
				
			}
		}
		
		
	}
// can i open a chrome tab
	// wait 2 min let me check the error on my pc
	

