package connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.bikestype;

public class handleBikeType {
	public connectToDB connect = new connectToDB();
	public bikestype bike = new bikestype();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	public int addBikes(bikestype newBike) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return 0;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into bikestype (biketype, preprice, price, description, deposit) values (?, ?, ?, ?, ?)");
		pst.setString(1, bike.getType());
		pst.setDouble(2, bike.getPre());
		pst.setDouble(3, bike.getPrice());
		pst.setString(4, bike.getDescription());
		pst.setDouble(5, bike.getDeposit());
		if (pst.executeUpdate() == 1) {
			return 1;
		}
		return 0;
	}
	
	public ResultSet getAllBikes() throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from bikestype");
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else return result;
	}
	
	public bikestype getBike(int id) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from bikestype where id = ?");
		pst.setInt(1, id);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			while (result.next()) {
				bike.setInfor(result.getInt("id"), result.getString("biketype"), result.getDouble("preprice"),
						result.getDouble("price"), result.getString("description"), result.getDouble("deposit"));
			}
			return bike;
		}
	}
}
