package connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class handleCurrentHistory {
	public connectToDB connect = new connectToDB();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	public int checkInCurrentRental(int id) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return -1;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from current_rental where user_id = ?");
		pst.setInt(1, id);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return 0;
		}
		else return 1;
	}
	
	public int addCurrentHistory(int bike_id, int user_id, double deposit, long timeStart) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return 0;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into current_rental (bike_id, user_id, deposit, time_start) values (?, ?, ?, ?)");
		pst.setInt(1, bike_id);
		pst.setInt(2, user_id);
		pst.setDouble(3, deposit);
		pst.setLong(4, timeStart);
		return pst.executeUpdate();
	}
	
	
	public ResultSet getCurrent(int user_id) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from current_rental where user_id = ?");
		pst.setInt(1, user_id);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else return result;
	}
	
	public int deleteCurrent(int user_id) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return -1;
		}
		pst = (PreparedStatement) con.prepareStatement("delete from current_rental where user_id = ?");
		pst.setInt(1, user_id);
		return pst.executeUpdate();
	}
}
