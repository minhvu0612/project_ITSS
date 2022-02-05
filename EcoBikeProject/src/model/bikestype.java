package model;

public class bikestype {
	private int id;
	private String type;
	private double pre;
	private double price;
	private String description;
	private double deposit;
	
	public void setInfor(int id, String type, double pp, double p, String des, double dpt) {
		this.id = id;
		this.type = type;
		this.pre = pp;
		this.price = p;
		this.description = des;
		this.deposit = dpt;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public double getPre() {
		return this.pre;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public double getDeposit() {
		return this.deposit;
	}
}
