package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import alert.alert;
import check.getPayment;
import connection.handleCurrentHistory;
import connection.handleHistory;
import connection.handleListBike;
import connection.handleUsers;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.bikes;
import model.users;

public class BikeGiveBackController {
	@FXML
	private Button btnHome;
	@FXML
	private Button btnInfor;
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnGiveBack;
	@FXML
	private Label fieldBikeType;
	@FXML
	private Label fieldAddress;
	@FXML
	private Label fieldDeposit;
	@FXML
	private Label fieldTimes;
	@FXML
	private Label fieldPossition;
	@FXML
	private Label fieldTotal;
	
	public users user;
	public bikes bike;
	long times;
	
	// library
	alert alert_all = new alert();
	getPayment getPay = new getPayment();
	handleCurrentHistory currentHistory = new handleCurrentHistory();
	handleHistory history = new handleHistory();
	handleUsers handleUser = new handleUsers();
	handleListBike listBike = new handleListBike();
	
	//set user
	public void setUser(users u) throws SQLException {
		user = u;
		ResultSet result = currentHistory.getCurrent(user.getId());
		if (result != null) {
			while (result.next()) {
				bike = listBike.getABike(result.getInt("bike_id"));
				fieldBikeType.setText(bike.getBikeType().getType());
				fieldAddress.setText(bike.getPark().getAddress());
				fieldDeposit.setText(String.valueOf(result.getDouble("deposit")));
				fieldPossition.setText(String.valueOf(bike.getPossition()));
				times = System.currentTimeMillis() - result.getLong("time_start");
				fieldTimes.setText(String.valueOf(times));
				fieldTotal.setText(String.valueOf(getPay.getPaymentEcoBikes(times, bike.getBikeType().getPre(), bike.getBikeType().getPrice())));
			}
		}
	}

	// Event Listener on Button[#btnHome].onAction
	@FXML
	public void onclickHome(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EcoHome.fxml"));
		Parent root = loader.load();
		EcoHomeController homeController = loader.getController();
		homeController.setUser(user);
		Stage window = (Stage) btnHome.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	
	
	
	// Event Listener on Button[#btnInfor].onAction
	@FXML
	public void onclickInfor(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Infor.fxml"));
		Parent root = loader.load();
		InforController inforController = loader.getController();
		inforController.setUser(user);
		Stage window = (Stage) btnInfor.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	
	
	
	// Event Listener on Button[#btnLogout].onAction
	@FXML
	public void onclickLogout(ActionEvent event) {
		Stage window = (Stage) btnLogout.getScene().getWindow();
		alert_all.alertLogout(window);
	}
	
	
	
	// Event Listener on Button[#btnGiveBack].onAction
	@FXML
	public void onclickGiveBack(ActionEvent event) throws NumberFormatException, SQLException, IOException {
		if (alert_all.alertGiveback() == 1) {
			if (history.addHistory(bike.getId(), 
					user.getId(), 
					bike.getBikeType().getDeposit(), 
					Double.parseDouble(fieldTotal.getText()), 
					times/60000) == 1) 
			{
				int updateMoney = handleUser.rechargeMoney(user.getUsername(), 
						Double.parseDouble(fieldDeposit.getText()) - Double.parseDouble(fieldTotal.getText()));
				int updateBike = listBike.updateStatus0(bike.getId());
				int updateCurrent = currentHistory.deleteCurrent(user.getId());
				if (updateMoney == 1 && updateBike == 1 && updateCurrent == 1) {
					alert_all.alertSuccess("Successfully!");
					FXMLLoader loader = new FXMLLoader(getClass().getResource("EcoHome.fxml"));
					Parent root = loader.load();
					EcoHomeController homeController = loader.getController();
					homeController.setUser(handleUser.getUser(user.getUsername(), user.getPassword()));
					Stage window = (Stage) btnGiveBack.getScene().getWindow();
					window.setScene(new Scene(root));
				}
			}
		    else 
		    {
				alert_all.alertFieldError("Error!");
			}
		}
	}
}
