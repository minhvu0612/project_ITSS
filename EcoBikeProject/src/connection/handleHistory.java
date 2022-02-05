package connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class handleHistory {
	public connectToDB connect = new connectToDB();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	public int addHistory(int bike_id, int user_id, double deposit, double pay, long times) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return 0;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into history_rental (bike_id, user_id, pay, times) values (?, ?, ?, ?)");
		pst.setInt(1, bike_id);
		pst.setInt(2, user_id);
		pst.setDouble(3, pay);
		pst.setLong(4, times);
		return pst.executeUpdate();
	}
}
