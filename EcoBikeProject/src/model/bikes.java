package model;

import java.sql.Date;

public class bikes {
	private int id;
	private int bikeId;
	private String parkingId;
	private int possition;
	private int status;
	private Date date;
	private int parkId;
	private bikestype biketype;
	private parking park;
	
	public void setInfor(int id, int bikeId, int parkingId, int possition, int status, Date date, String parkAdd) {
		this.id = id;
		this.bikeId = bikeId;
		this.parkingId = parkAdd;
		this.possition = possition;
		this.status = status;
		this.date = date;
		this.parkId = parkingId;
	}
	
	public void setUniqueInfor(bikestype biketype, parking park) {
		this.biketype = biketype;
		this.park = park;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getBikeId() {
		return this.bikeId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}
	
	public int getPossition() {
		return this.possition;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public int getAdd() {
		return this.parkId;
	}
	
	public bikestype getBikeType() {
		return this.biketype;
	}
	
	public parking getPark() {
		return this.park;
	}
}
