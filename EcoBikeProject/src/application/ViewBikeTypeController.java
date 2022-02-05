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
import connection.handleBikeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.bikestype;
import model.users;

public class ViewBikeTypeController {
	@FXML
	private Button btnHome;
	@FXML
	private Button btnViewInfor;
	@FXML
	private Button btnLogout;
	@FXML
	private TableView<bikestype> table;
	@FXML
	private TableColumn<bikestype, String> columnId;
	@FXML
	private TableColumn<bikestype, String> columnType;
	@FXML
	private TableColumn<bikestype, String> columnPreprice;
	@FXML
	private TableColumn<bikestype, String> columnPrice;
	@FXML
	private TableColumn<bikestype, String> columnDescription;
	@FXML
	private TableColumn<bikestype, String> columnDeposit;
	
	ObservableList<bikestype> list = FXCollections.observableArrayList();
	
	// library
	alert alert_all = new alert();
	handleBikeType handleBikeType = new handleBikeType();
	
    public users user;
 	
	public void setUser(users u) throws SQLException {
		user = u;
		ResultSet result = handleBikeType.getAllBikes();
		if (result != null) {
			while(result.next()) {
				bikestype newBikeType = new bikestype();
				newBikeType.setInfor(result.getInt("id"), 
						         result.getString("biketype"), 
						         result.getDouble("preprice"), 
						         result.getDouble("price"), 
						         result.getString("description"), 
						         result.getDouble("deposit"));
				list.add(newBikeType);
			}
			columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
			columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
			columnPreprice.setCellValueFactory(new PropertyValueFactory<>("pre"));
			columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
			columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
			columnDeposit.setCellValueFactory(new PropertyValueFactory<>("deposit"));
			table.setItems(list);
		}
	}

	// Event Listener on Button[#btnGoHome].onAction
	@FXML
	public void onclickHome(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EcoHome.fxml"));
		Parent root = loader.load();
		EcoHomeController homeController = loader.getController();
		homeController.setUser(user);
		Stage window = (Stage) btnHome.getScene().getWindow();
		window.setScene(new Scene(root));
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
}
