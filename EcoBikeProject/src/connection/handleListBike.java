package connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.bikes;

public class handleListBike {
	public connectToDB connect = new connectToDB();
	public bikes newBike = new bikes();
	handleBikeType bikeType = new handleBikeType();
	handleParking  park = new handleParking();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	public ResultSet getAllListBike() throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from list_bike");
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else return result;
	}
	
	public bikes getABike(int id) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from list_bike where id = ?");
		pst.setInt(1, id);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			while (result.next()) {
				if (result.getInt("bike_id") == 0) {
					newBike.setInfor(result.getInt("id"), 0, result.getInt("parking_id"),
					         result.getInt("possition"), 0, (Date) null, park.getPark(result.getInt("parking_id")).getAddress());
					newBike.setUniqueInfor(null, park.getPark(result.getInt("parking_id")));
				}
				else {
				    newBike.setInfor(result.getInt("id"),
						         result.getInt("bike_id"), 
						         result.getInt("parking_id"),
						         result.getInt("possition"),
						         result.getInt("status"),
						         result.getDate("date"),
						         park.getPark(result.getInt("parking_id")).getAddress()
						         );
				    newBike.setUniqueInfor(bikeType.getBike(newBike.getBikeId()),
				    		               park.getPark(result.getInt("parking_id")));
			    }
		    }
			return newBike;
	    }
    }
	
	// update 1
	
	public int updateStatus1(int id) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return -1;
		}
		pst = (PreparedStatement) con.prepareStatement("update list_bike set status = ? where id = ?");
		pst.setInt(1, 1);
		pst.setInt(2, id);
		return pst.executeUpdate();
	}
	
	// update 0
	
	public int updateStatus0(int id) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return -1;
		}
		pst = (PreparedStatement) con.prepareStatement("update list_bike set status = ? where id = ?");
		pst.setInt(1, 0);
		pst.setInt(2, id);
		return pst.executeUpdate();
	}
}
