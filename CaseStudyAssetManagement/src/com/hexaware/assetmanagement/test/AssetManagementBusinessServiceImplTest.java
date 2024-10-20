package com.hexaware.assetmanagement.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexaware.assetmanagement.entity.Asset;
import com.hexaware.assetmanagement.entity.Employee;
import com.hexaware.assetmanagement.myexceptions.AssetNotFoundException;
import com.hexaware.assetmanagement.service.AssetManagementBusinessServiceImpl;
import com.hexaware.assetmanagement.service.IAssetManagementBusinessService;

class AssetManagementBusinessServiceImplTest {

	private IAssetManagementBusinessService service;
	
	
	@BeforeEach
	void initiate() {
		service = new AssetManagementBusinessServiceImpl();
	}
	
	@Test
	void testAddAsset() {
		// Test Case 1
		// All Valid Input's Provided Expecting to return true
		LocalDate date1 = LocalDate.parse("2024-10-19");
		Asset asset1 = new Asset(3, "Iphone 14", "Electronic Gadget", "23ftgh", date1, "Delhi India", "in use", 1);
		assertTrue(service.addAsset(asset1));
		//Test Case 2
		// Duplicate Entry
		assertFalse(service.addAsset(asset1));
	}

	@Test
	void testPerformMaintenance() {
		// Test Case 1
		// All Valid Input's Provided Expecting to return true
		assertTrue(service.performMaintenance(2, 1, "2024-10-20", "Performed Maintenace", 2000));
		//Test Case 2
		// Duplicate Entry
		assertFalse(service.performMaintenance(2, 1, "2024-10-20", "Performed Maintenace", 2000));
	}

	@Test
	void testReserveAsset() {
		// Test Case 1
		// All Valid Input's Provided Expecting to return true
		try {
			assertTrue(service.reserveAsset(1, 1, "2024-10-20", "2024-10-20", "2024-10-31"));
		} catch (AssetNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		assertThrows(AssetNotFoundException.class, () -> {
	        service.reserveAsset(-1, 1, "2024-10-20", "2024-10-20", "2024-10-31");
	    });
		
	}

	@Test
	void testWithdrawReservation() {
		// Test Case 1
		// All Valid Input's Provided Expecting to return true
		assertTrue(service.withdrawReservation(361700031));
		
		//Test Case 2
		// Duplicate Entry
		assertTrue(service.withdrawReservation(361700031));
	}

	@Test
	void testAddEmployee() {
		// Test Case 1
		// All Valid Input's Provided Expecting to return true
		Employee employee = new Employee(4,"Jay Rai","Finance Department","jay@gmail.com","Jay@123");
		assertTrue(service.addEmployee(employee));
		
		//Test Case 2
		// Duplicate Entry
		assertFalse(service.addEmployee(employee));
		
		
	}

}
