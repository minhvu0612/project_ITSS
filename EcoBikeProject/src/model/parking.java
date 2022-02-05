package model;

public class parking {
	private int id;
	private String address;
	
	public void setInfor(int id, String address) {
		this.id = id;
		this.address = address;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getAddress() {
		return this.address;
	}
}
