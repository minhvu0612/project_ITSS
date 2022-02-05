package model;

public class users {
	private int id;
	private String username;
	private String password;
	private String phone;
	private double money;
	
	public void setInfor(int id, String u, String pw, String pn, double money) {
		this.id = id;
		this.username = u;
		this.password = pw;
		this.phone = pn;
		this.money = money;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public double getPay() {
		return this.money;
	}
}
