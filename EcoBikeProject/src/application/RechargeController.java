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

public class RechargeController {
	@FXML
	private PasswordField rechargeFieldPassword;
	@FXML
	private TextField rechargeFieldMoney;
	@FXML
	private Button btnOk;
	
	//library
	checkField check = new checkField();
	alert alert_all = new alert();
	handleUsers handle = new handleUsers();
	
    public users user;
	
	public void setUser(users u) {
		user = u;
	}

	// Event Listener on Button[#btnOk].onAction
	@FXML
	public void onclickOk(ActionEvent event) throws NumberFormatException, SQLException, IOException {
		if (check.checkMoney(rechargeFieldMoney.getText()) == 1) {
			if (Double.parseDouble(rechargeFieldMoney.getText()) >= 10000){
				if (rechargeFieldPassword.getText().compareTo(user.getPassword()) == 0) {
					if (handle.rechargeMoney(user.getUsername(), Double.parseDouble(rechargeFieldMoney.getText())) == 1) {
						alert_all.alertSuccess("Successfully!");
						users newUser = handle.getUser(user.getUsername(), user.getPassword());
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Infor.fxml"));
						Parent root = loader.load();
						InforController inforController = loader.getController();
						inforController.setUser(newUser);
						Stage window = (Stage) btnOk.getScene().getWindow();
						window.setScene(new Scene(root));
					}
					else {
						alert_all.alertFieldError("Error!");
					}
				}
				else {
					alert_all.alertFieldError("Password invalid!");
				}
			}
			else {
				alert_all.alertWarning("The money must be more than 10000");
			}
		}
		else {
			alert_all.alertFieldError("The money invalid!");
		}
	}
}
