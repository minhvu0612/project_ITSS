package connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.users;

public class handleUsers {
	public connectToDB connect = new connectToDB();
	public users user = new users();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	
	// add new user
	public int addUser(String username, String password, String phone) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return 0;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into users (username, password, phone) values (?, ?, ?)");
		pst.setString(1, username);
		pst.setString(2, password);
		pst.setString(3, phone);
		if (pst.executeUpdate() == 1) {
			return 1;
		}
		return 0;
	}
	
	// get user
	public users getUser(String username, String password) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from users where username = ? and password = ?");
		pst.setString(1, username);
		pst.setString(2, password);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			int id = 0;
			String u = null;
			String pass = null;
			String phone = null;
			double money = 0;
			while (result.next()) {
				id = result.getInt("id");
				u = result.getString("username");
				pass = result.getString("password");
				phone = result.getString("phone");
				money = result.getDouble("pay_money");
			}
			user.setInfor(id, u, pass, phone, money);
			return user;
		}
	}
	
	// check user in database
	public int checkUserInDB(String username, String phone) throws SQLException {
		connectToDB connect = new connectToDB();
		this.con = connect.getConnection();
		if (con == null) {
			return 0;
		}
		this.pst = (PreparedStatement) con.prepareStatement("select * from users");
		this.result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return 1;
		}
		else {
			while (result.next()) {
				if (username.compareTo(this.result.getString("username")) == 0) {
					return 0;
				}
				if (phone.compareTo(this.result.getString("phone")) == 0) {
					return 0;
				}
			}
			return 1;
		}
	}
	
	
	// update user
	public int rechargeMoney(String username, double money) throws SQLException {
		connectToDB connect = new connectToDB();
		this.con = connect.getConnection();
		if (con == null) {
			return 0;
		}
		// get current money
		this.pst = (PreparedStatement) con.prepareStatement("select pay_money from users where username = ?");
		this.pst.setString(1, username);
		this.result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return 0;
		}
		else {
			double currentMoney = 0;
			while (result.next()) {
				currentMoney = result.getDouble("pay_money");
			}
			// update money
			this.pst = (PreparedStatement) con.prepareStatement("update users set pay_money = ? where username = ?");
			this.pst.setDouble(1, currentMoney + money);
			this.pst.setString(2, username);
			if (pst.executeUpdate() == 1) {
				return 1;
			}
			return 0;
		}
	}
	
	// deposit
	public int depositMoney(int user_id, double pay_money, double deposit) throws SQLException {
		connectToDB connect = new connectToDB();
		this.con = connect.getConnection();
		if (con == null) {
			return 0;
		}
		this.pst = (PreparedStatement) con.prepareStatement("update users set pay_money = ? where id = ?");
		this.pst.setDouble(1, pay_money-deposit);
		this.pst.setInt(2, user_id);
		return this.pst.executeUpdate();
	}
}
