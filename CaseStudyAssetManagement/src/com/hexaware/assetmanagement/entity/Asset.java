package com.hexaware.assetmanagement.entity;

import java.time.LocalDate;

public class Asset {
	private int assetId;
	private String assetName;
	private String type;
	private String serialNumber;
	private LocalDate purchaseDate;
	private String location;
	private String assetStatus;
	private int ownerId;

	public Asset() {
		super();
	}

	public Asset(int assetId, String assetName, String type, String serialNumber, LocalDate purchaseDate,
			String location, String assetStatus, int ownerId) {
		super();
		this.assetId = assetId;
		this.assetName = assetName;
		this.type = type;
		this.serialNumber = serialNumber;
		this.purchaseDate = purchaseDate;
		this.location = location;
		this.assetStatus = assetStatus;
		this.ownerId = ownerId;
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	
	
}
