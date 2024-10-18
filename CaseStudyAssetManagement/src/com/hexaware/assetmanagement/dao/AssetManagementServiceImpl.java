package com.hexaware.assetmanagement.dao;

/*
 * @Author: Pritesh Rai
 * Description: Implemented Dao Interface
 * Date: 12th Oct 24
 */

import java.sql.Connection;
import com.hexaware.assetmanagement.myexceptions.AssetNotFoundException;
import com.hexaware.assetmanagement.myexceptions.AssetNotMaintainException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import com.hexaware.assetmanagement.entity.Asset;
import com.hexaware.assetmanagement.entity.Employee;
import com.hexaware.assetmanagement.util.DBUtil;

public class AssetManagementServiceImpl implements IAssetManagementService {

	private Connection conn;

	public AssetManagementServiceImpl() {
		super();
		conn = DBUtil.getConnection();
	}
	

	


	@Override
	public boolean checkExistAssetId(int assetId) throws AssetNotFoundException  {
		// TODO Auto-generated method stub
		String retriveAssetIdQuery="select asset_id from assets";
		try {
			PreparedStatement retrieveAssetIdStmt=conn.prepareStatement(retriveAssetIdQuery);
			ResultSet resultSet=retrieveAssetIdStmt.executeQuery();
			while(resultSet.next()) {
				int existAssetId=resultSet.getInt(1);
				if(assetId==existAssetId) {
//					maintenanceDate(assetId);	
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 throw new AssetNotFoundException();
	}
//
public boolean maintenanceDate(int assetId) throws AssetNotMaintainException {
	String checkDate = "select maintenance_date from maintenance_records where asset_id =?";
	try {
		PreparedStatement checkDateStmt = conn.prepareStatement(checkDate);
		checkDateStmt.setInt(1, assetId);
		ResultSet result = checkDateStmt.executeQuery();
				if(result.next()) {
					 Date recDate = result.getDate("maintenance_date"); // SQL Date
			            LocalDate maintenanceDate = recDate.toLocalDate();  // Convert to LocalDate
			            
			            // Calculate the difference in years between the maintenance date and the current date
			            long yearsDifference = ChronoUnit.YEARS.between(maintenanceDate, LocalDate.now());
			            
			            // If the difference is greater than 2 years, return false
			            System.out.println(yearsDifference);
			            if (yearsDifference > 2) {
			                throw new AssetNotMaintainException();
			            } else {
			                return true;
			            }
				}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
}



	@Override
	public boolean addAsset(Asset asset) {
		// TODO Auto-generated method stub
		String insertAssetQuery = "insert into assets"
				+ "(asset_id,name,type,serial_number,purchase_date,location,status,owner_id)values(?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement insertAssetStmt = conn.prepareStatement(insertAssetQuery);
			insertAssetStmt.setInt(1,asset.getAssetId());
			insertAssetStmt.setString(2, asset.getAssetName());
			insertAssetStmt.setString(3, asset.getType());
			insertAssetStmt.setString(4, asset.getSerialNumber());
			insertAssetStmt.setDate(5, Date.valueOf( asset.getPurchaseDate()));
			insertAssetStmt.setString(6, asset.getLocation());
			insertAssetStmt.setString(7,asset.getAssetStatus());
			insertAssetStmt.setInt(8, asset.getOwnerId());
			
			int count = insertAssetStmt.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean updateAsset(Asset asset) {
		// TODO Auto-generated method stub
		
//		String updateAssetQuery = "UPDATE assets SET"
//				+ "location = ?,"
//				+ "status = ?,"
//				+ "WHERE asset_id = ?;";
		
		String updateAssetQuery = "UPDATE assets"
				+ "     SET location = ?, status = ?"
				+ "    WHERE asset_id = ?;";
		try {
			PreparedStatement updateAssetStmt = conn.prepareStatement(updateAssetQuery);
			updateAssetStmt.setString(1, asset.getLocation());
			updateAssetStmt.setString(2, asset.getAssetStatus());
			updateAssetStmt.setInt(3, asset.getAssetId());
			int count = updateAssetStmt.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		

		return false;
	}

	@Override
	public boolean deleteAsset(int assetId) {
		// TODO Auto-generated method stub
		
		String deleteAssetQuery = "delete from assets where asset_id =?;";
		try {
			PreparedStatement deleteAssetStmt = conn.prepareStatement(deleteAssetQuery);
			deleteAssetStmt.setInt(1, assetId);
			
			int rowsAffected = deleteAssetStmt.executeUpdate();
			
			return rowsAffected >0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

//	@Override
//	public boolean allocateAsset(int allocation_id,int assetId, int employeeId, String allocationDate) {
//		// TODO Auto-generated method stub
//		String allocateAssetQuery =  "INSERT INTO asset_allocations (allocation_id, asset_id, employee_id, allocation_date)"
//				+ "VALUES (?,?, ?, ?);";
//		try {
//			PreparedStatement allocateAssetQueryStmt = conn.prepareStatement(allocateAssetQuery);
//			allocateAssetQueryStmt.setInt(1, assetId);
//			allocateAssetQueryStmt.setInt(2, assetId);
//			allocateAssetQueryStmt.setInt(3, employeeId);
//			allocateAssetQueryStmt.setDate(4, Date.valueOf(allocationDate));
//			
//			int count = allocateAssetQueryStmt.executeUpdate();
//			if(count>0) {
//				return true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}

	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
		// TODO Auto-generated method stub
		String deallocateAssetQuery = "UPDATE asset_allocations SET return_date = ? WHERE asset_id = ? AND employee_id = ? AND return_date IS NULL";
		try {
			PreparedStatement deallocateAssetQueryStmt = conn.prepareStatement(deallocateAssetQuery);
			deallocateAssetQueryStmt.setDate(1, Date.valueOf(returnDate)); 
			deallocateAssetQueryStmt.setInt(2, assetId);
			deallocateAssetQueryStmt.setInt(3, employeeId);
			int affectedRows = deallocateAssetQueryStmt.executeUpdate();
			return affectedRows>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean performMaintenance(int maintenanceId,int assetId, String maintenanceDate, String description, double cost) {
		// TODO Auto-generated method stub
		String insertMaintenanceQuery = "INSERT INTO maintenance_records (maintenance_id, asset_id, maintenance_date, description, cost) "
                + "VALUES (?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement insertMaintenanceQueryStmt =  conn.prepareStatement(insertMaintenanceQuery);
			
//			int maintenanceId = ge; 
			insertMaintenanceQueryStmt.setInt(1, maintenanceId); 
			insertMaintenanceQueryStmt.setInt(2, assetId); 
			insertMaintenanceQueryStmt.setDate(3, java.sql.Date.valueOf(maintenanceDate)); 
			insertMaintenanceQueryStmt.setString(4, description); 
			insertMaintenanceQueryStmt.setDouble(5, cost);
			
			int affectedRows = insertMaintenanceQueryStmt.executeUpdate();
	        return affectedRows > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
		// TODO Auto-generated method stub
		String insertReservationQuery = "INSERT INTO reservations (reservation_id, asset_id, employee_id, reservation_date, start_date, end_date, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement insertReservationQueryStmt = conn.prepareStatement(insertReservationQuery);
			int reservationId = generateMaintenanceId(employeeId); 
			
			insertReservationQueryStmt.setInt(1, reservationId);
			insertReservationQueryStmt.setInt(2, assetId);
			insertReservationQueryStmt.setInt(3, employeeId); 
			insertReservationQueryStmt.setDate(4, java.sql.Date.valueOf(reservationDate)); 
			insertReservationQueryStmt.setDate(5, java.sql.Date.valueOf(startDate)); 
			insertReservationQueryStmt.setDate(6, java.sql.Date.valueOf(endDate)); 
			insertReservationQueryStmt.setString(7, "Approved");
			
			int count = insertReservationQueryStmt.executeUpdate();
			return count>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean withdrawReservation(int reservationId) {
		// TODO Auto-generated method stub
		String updateReservationQuery = "UPDATE reservations SET status = ? WHERE reservation_id = ?;";
		try {
			PreparedStatement updateReservationQueryStmt = conn.prepareStatement(updateReservationQuery);
			updateReservationQueryStmt.setString(1, "Cancelled"); // Set status to 'Cancelled'
			updateReservationQueryStmt.setInt(2, reservationId); // Set the reservation ID

	        // Execute the update
	        int affectedRows = updateReservationQueryStmt.executeUpdate();

	        // Return true if at least one row was updated
	        return affectedRows > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String insertEmployeeQuery = "insert into employees(employee_id,name,department,email,password)values(?,?,?,?,?);";
		try {
			PreparedStatement insertEmployeeStmt = conn.prepareStatement(insertEmployeeQuery);
			insertEmployeeStmt.setInt(1, employee.getEmployeeId());
			insertEmployeeStmt.setString(2, employee.getEmployeeName());
			insertEmployeeStmt.setString(3, employee.getEmployeeDepartment());
			insertEmployeeStmt.setString(4, employee.getEmployeeEmail());
			insertEmployeeStmt.setString(5, employee.getEmployeePassword());
			int count = insertEmployeeStmt.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	private int generateMaintenanceId(int assetId) {
		LocalDate currentDate = LocalDate.now();
	    int year = currentDate.getYear(); 
	    int month = currentDate.getMonthValue(); 
	    int day = currentDate.getDayOfMonth(); 
	    int limitedAssetId = assetId % 1000; 
	    int maintenanceId = Integer.parseInt(String.format("%04d%02d%", year, month, limitedAssetId));

	    return maintenanceId;
	}

	@Override
	public boolean allocateAsset(int allocationId, int assetId, int employeeId, String allocationDate) {
		// TODO Auto-generated method stub
		String allocateAssetQuery =  "INSERT INTO asset_allocations (allocation_id, asset_id, employee_id, allocation_date)"
				+ "VALUES (?,?, ?, ?);";
		try {
			
			PreparedStatement allocateAssetQueryStmt = conn.prepareStatement(allocateAssetQuery);
			allocateAssetQueryStmt.setInt(1, allocationId);
			allocateAssetQueryStmt.setInt(2, assetId);
			allocateAssetQueryStmt.setInt(3, employeeId);
			allocateAssetQueryStmt.setDate(4, Date.valueOf(allocationDate));
			
			int count = allocateAssetQueryStmt.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
