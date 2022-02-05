package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import alert.alert;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class ForgotPassController {
	@FXML
	private PasswordField forgotFieldConfirm;
	@FXML
	private PasswordField forgotFiledPass;
	@FXML
	private TextField forgotFiledUser;
	@FXML
	private Button btnChange;
	@FXML
	private Button btnBack;
	
	//library
	alert alert_all = new alert();
	

	// Event Listener on Button[#btnChange].onAction
	@FXML
	public void onclickChange(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Stage window = (Stage) btnChange.getScene().getWindow();
		window.setScene(new Scene(root));
		alert_all.alertSuccess("Successfully!");
	}
	// Event Listener on Button[#btnBack].onAction
	@FXML
	public void onclickBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Stage window = (Stage) btnBack.getScene().getWindow();
		window.setScene(new Scene(root));
	}
}
