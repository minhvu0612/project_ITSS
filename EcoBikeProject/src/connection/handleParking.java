package connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.parking;

public class handleParking {
	public connectToDB connect = new connectToDB();
	public parking park = new parking();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	public parking getPark(int id) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from parking where id = ?");
		pst.setInt(1, id);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			int id1 = 0;
			String address = null;
			while (result.next()) {
				id1 = result.getInt("id");
				address = result.getString("address");
			}
			park.setInfor(id1, address);
			return park;
		}
	}
}
