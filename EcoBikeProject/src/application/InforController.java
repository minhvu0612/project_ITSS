package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import alert.alert;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.users;

public class InforController {
	@FXML
	private Button btnGoHome;
	@FXML
	private Button btnLogout;
	@FXML
	private Label inforUsername;
	@FXML
	private Label inforPassword;
	@FXML
	private Label inforPhone;
	@FXML
	private Label inforMoney;
	@FXML
	private Button btnUpdateInfor;
	@FXML
	private Button btnGetMoney;
	
	// library
	alert alert_all = new alert();
	
    public users user;
	
	public void setUser(users u) {
		user = u;
		inforUsername.setText(user.getUsername());
		inforPassword.setText(user.getPassword());
		inforPhone.setText(user.getPhone());
		inforMoney.setText(Double.toString(Math.round(user.getPay())));
	}

	// Event Listener on Button[#btnComeHome].onAction
	@FXML
	public void onclickGoHome(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EcoHome.fxml"));
		Parent root = loader.load();
		EcoHomeController homeController = loader.getController();
		homeController.setUser(user);
		Stage window = (Stage) btnGoHome.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	// Event Listener on Button[#btnLogout].onAction
	@FXML
	public void onclickLogout(ActionEvent event) {
		Stage window = (Stage) btnLogout.getScene().getWindow();
		alert_all.alertLogout(window);
	}
	// Event Listener on Button[#btnUpdateInfor].onAction
	@FXML
	public void onclickUpdateInfor(ActionEvent event) {
		
	}
	// Event Listener on Button[#btnGetMoney].onAction
	@FXML
	public void onclickGetMoney(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Recharge.fxml"));
		Parent root = loader.load();
		RechargeController rechargeController = loader.getController();
		rechargeController.setUser(user);
		Stage window = (Stage) btnGetMoney.getScene().getWindow();
		window.setScene(new Scene(root));
	}
}
