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

public class SignUpController {
	@FXML
	private PasswordField signupFieldPass;
	@FXML
	private TextField signupFieldUser;
	@FXML
	private Button btnSignup;
	@FXML
	private Button btnLogin;
	@FXML
	private PasswordField signupFieldConfirm;
	@FXML
	private TextField signupFieldPhone;
	
	// get library
	public checkField check = new checkField();
	public alert alert_all = new alert();
	public handleUsers handleUser = new handleUsers();

	// Event Listener on Button[#btnSignup].onAction
	@FXML
	public void onclickSignup(ActionEvent event) throws SQLException, IOException {
		if (check.checkFieldUser(signupFieldUser.getText()) == 0) {
			alert_all.alertFieldError("Username must contain at least 1 character and must be 6 or more in length.");
		}
		else {
			if (check.checkFieldPass(signupFieldPass.getText()) == 0) {
				alert_all.alertFieldError("Password must contain at least 1 character and must be 6 or more in length.");
			}
			else {
				if (check.checkConfirmPass(signupFieldPass.getText(), signupFieldConfirm.getText()) == 0) {
					alert_all.alertFieldError("Confirm does not match password.");
				}
				else {
					if (check.checkPhone(signupFieldPhone.getText()) == 0) {
						alert_all.alertFieldError("Phone incorrect.");
					}
					else {
						if (handleUser.checkUserInDB(signupFieldUser.getText(), signupFieldPhone.getText()) == 0) {
							alert_all.alertFieldError("Username or phone was exited.");
						}
						else {
							int add = handleUser.addUser(signupFieldUser.getText(), signupFieldPass.getText(), signupFieldPhone.getText());
							if (add == 1) {
								users newUser = new users();
								newUser = handleUser.getUser(signupFieldUser.getText(), signupFieldPass.getText());
								//System.out.println(newUser.getId() + " " + newUser.getUsername() + " " + newUser.getPassword() + " " + newUser.getPhone());
								FXMLLoader loader = new FXMLLoader(getClass().getResource("EcoHome.fxml"));
								Parent root = loader.load();
								EcoHomeController ecoHome = loader.getController();
								ecoHome.setUser(newUser);
								Stage window = (Stage) btnSignup.getScene().getWindow();
								window.setScene(new Scene(root));
							}
						}
					}
				}
			}
		}
		
	}
	// Event Listener on Button[#btnLogin].onAction
	@FXML
	public void onclickLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Stage window = (Stage) btnLogin.getScene().getWindow();
		window.setScene(new Scene(root));
	}
}
