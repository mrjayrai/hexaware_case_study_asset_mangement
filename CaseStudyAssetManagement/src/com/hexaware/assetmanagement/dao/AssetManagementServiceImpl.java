package com.hexaware.assetmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		String updateAssetQuery = "UPDATE assets"
				+ "location = ?,"
				+ "status = ?,"
				+ "WHERE asset_id = ?;";
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

	@Override
	public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdrawReservation(int reservationId) {
		// TODO Auto-generated method stub
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


}
