package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.users;

import java.io.IOException;
import java.sql.SQLException;

import alert.alert;
import check.checkField;
import connection.handleUsers;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class LoginController {
	@FXML
	private PasswordField loginFieldPass;
	@FXML
	private TextField loginFieldUser;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnForgot;
	@FXML
	private Button btnSignup;
	
	// get library
	public checkField check = new checkField();
	public alert alert_all = new alert();
	public handleUsers handleUser = new handleUsers();

	// Event Listener on Button[#btnLogin].onAction
	@FXML
	public void onclickLogin(ActionEvent event) throws SQLException, IOException {
		if (check.checkFieldUser(loginFieldUser.getText()) == 0) {
			alert_all.alertFieldError("Username must contain at least 1 character and must be 6 or more in length.");
		}
		else {
			if (check.checkFieldPass(loginFieldPass.getText()) == 0) {
				alert_all.alertFieldError("Password must contain at least 1 character and must be 6 or more in length.");
			}
			else {
				users newUser = new users();
				newUser = handleUser.getUser(loginFieldUser.getText(), loginFieldPass.getText());
				if (newUser != null) {
					//System.out.println(newUser.getId() + " " + newUser.getUsername() + " " + newUser.getPassword() + " " + newUser.getPhone());
					FXMLLoader loader = new FXMLLoader(getClass().getResource("EcoHome.fxml"));
					Parent root = loader.load();
					EcoHomeController ecoHome = loader.getController();
					ecoHome.setUser(newUser);
					Stage window = (Stage) btnSignup.getScene().getWindow();
					window.setScene(new Scene(root));
				}
				else {
					alert_all.alertFieldError("User was not existed.");
				}
			}
		}
	}
	// Event Listener on Button[#btnForgot].onAction
	@FXML
	public void onclickForgot(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ForgotPass.fxml"));
		Stage window = (Stage) btnForgot.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	// Event Listener on Button[#btnSignup].onAction
	@FXML
	public void onclickSignup(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		Stage window = (Stage) btnSignup.getScene().getWindow();
		window.setScene(new Scene(root));
	}
}
