package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.users;

import java.io.IOException;
import java.sql.SQLException;

import alert.alert;
import connection.handleCurrentHistory;
import javafx.event.ActionEvent;

public class EcoHomeController {
	@FXML
	private Button btnViewInfor;
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnBikeRental;
	@FXML
	private Button btnViewBikeType;
	@FXML
	private Button btnGiveBack;
	
	// library
	alert alert_all = new alert();
	handleCurrentHistory currentHistory = new handleCurrentHistory();
	
	public users user;
	
	public void setUser(users u) {
		user = u;
	}

	// Event Listener on Button[#btnViewInfor].onAction
	@FXML
	public void onclickViewInfor(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Infor.fxml"));
		Parent root = loader.load();
		InforController inforController = loader.getController();
		inforController.setUser(user);
		Stage window = (Stage) btnViewInfor.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	
	
	
	// Event Listener on Button[#btnLogout].onAction
	@FXML
	public void onclickLogout(ActionEvent event) {
		Stage window = (Stage) btnLogout.getScene().getWindow();
		alert_all.alertLogout(window);
	}
	
	
	
	// Event Listener on Button[#btnBikeRental].onAction
	@FXML
	public void onclickBikeRental(ActionEvent event) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BikeRental.fxml"));
		Parent root = loader.load();
		BikeRentalController bikeRental = loader.getController();
		bikeRental.setUser(user);
		Stage window = (Stage) btnViewBikeType.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	
	
	
	// Event Listener on Button[#btnViewBikeType].onAction
	@FXML
	public void viewBikeType(ActionEvent event) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBikeType.fxml"));
		Parent root = loader.load();
		ViewBikeTypeController viewBike = loader.getController();
		viewBike.setUser(user);
		Stage window = (Stage) btnViewBikeType.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	
	
	
	// Event Listener on Button[#btnGiveBack].onAction
	@FXML
	public void onclickGiveBack(ActionEvent event) throws SQLException, IOException {
		if (currentHistory.checkInCurrentRental(user.getId()) == 1) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BikeGiveBack.fxml"));
			Parent root = loader.load();
			BikeGiveBackController giveBack = loader.getController();
			giveBack.setUser(user);
			Stage window = (Stage) btnGiveBack.getScene().getWindow();
			window.setScene(new Scene(root));
		}
		else {
			alert_all.alertWarning("Please rent a vehicle to use this feature");
		}
	}
}
