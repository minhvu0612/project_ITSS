package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.bikes;
import model.users;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import alert.alert;
import check.checkField;
import connection.handleBikeType;
import connection.handleCurrentHistory;
import connection.handleListBike;
import connection.handleParking;
import connection.handleUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class BikeRentalController {
	@FXML
	private TableView<bikes> tableParking;
	@FXML
	private TableColumn<bikes, String> columnId;
	@FXML
	private TableColumn<bikes, String> columnBikeId;
	@FXML
	private TableColumn<bikes, String> columnParkingId;
	@FXML
	private TableColumn<bikes, String> columnPosition;
	@FXML
	private TableColumn<bikes, String> columnStatus;
	@FXML
	private TableColumn<bikes, String> columnDate;
	@FXML
	private TableColumn<bikes, String> columnAddress;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnViewInfor;
	@FXML
	private Button btnLogout;
	@FXML
	private TextField searchField;
	@FXML
	private Button btnHelp;
	@FXML
	private Button btnSearch;
	@FXML
	private Label typeLabel;
	@FXML
	private Label addressLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label possitionLabel;
	@FXML
	private Button btnRental;
	
	double minPay = 800000.0;
	
	ObservableList<bikes> list = FXCollections.observableArrayList();
	
	// library
	alert alert_all = new alert();
	checkField check = new checkField();
	handleUsers handelUser = new handleUsers();
	handleListBike listBike = new handleListBike();
	handleBikeType bikeType = new handleBikeType();
	handleParking  park = new handleParking();
	handleCurrentHistory curentHistory = new handleCurrentHistory();
	
    public users user;
    public bikes bike = null;
	
	public void setUser(users u) throws SQLException {
		user = u;
		ResultSet result = listBike.getAllListBike();
		if (result != null) {
			while (result.next()) {
				bikes newBike = new bikes();
				if (result.getInt("bike_id") == 0) {
					newBike.setInfor(result.getInt("id"), 0, result.getInt("parking_id"),
					         result.getInt("possition"), 0, (Date) null, park.getPark(result.getInt("parking_id")).getAddress());
					newBike.setUniqueInfor(null, park.getPark(result.getInt("parking_id")));
				}
				else {
				    newBike.setInfor(result.getInt("id"),
						         result.getInt("bike_id"), 
						         result.getInt("parking_id"),
						         result.getInt("possition"),
						         result.getInt("status"),
						         result.getDate("date"),
						         park.getPark(result.getInt("parking_id")).getAddress()
						         );
				    newBike.setUniqueInfor(bikeType.getBike(newBike.getBikeId()),
				    		               park.getPark(result.getInt("parking_id")));
				    //System.out.println(newBike.getBikeType().getType());
				}
				list.add(newBike);
			}
			columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
			columnBikeId.setCellValueFactory(new PropertyValueFactory<>("bikeId"));
			columnAddress.setCellValueFactory(new PropertyValueFactory<>("parkingId"));
			columnPosition.setCellValueFactory(new PropertyValueFactory<>("possition"));
			columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
			columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
			columnParkingId.setCellValueFactory(new PropertyValueFactory<>("add"));
			tableParking.setItems(list);
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
	
	
	
	
	// Event Listener on Button[#btnHelp].onAction
	@FXML
	public void onclickHelp(ActionEvent event) {
		alert_all.alertSuccess("Enter an id from the table below.");
	}
	
	
	
	// Event Listener on Button[#btnSearch].onAction
	@FXML
	public void onclickSearch(ActionEvent event) throws SQLException {
		if (searchField.getText() == "") {
			alert_all.alertWarning("The field to be entered must be not null");
			return;
		}
		if (check.checkMoney(searchField.getText()) == 0) {
			alert_all.alertFieldError("The field to be entered must be numeric");
		}
		else {
			bike = listBike.getABike(Integer.parseInt(searchField.getText()));
			if (bike == null) {
				alert_all.alertFieldError("There is no vehicle in the list");
			}
			else {
				if (bike.getBikeType() == null) {
					typeLabel.setText("null");
					addressLabel.setText(bike.getParkingId());
					dateLabel.setText("null");
					possitionLabel.setText(String.valueOf(bike.getPossition()));
				}
				else {
					typeLabel.setText(bike.getBikeType().getType());
					addressLabel.setText(bike.getParkingId());
					dateLabel.setText(bike.getDate().toString());
					possitionLabel.setText(String.valueOf(bike.getPossition()));
				}
			}
		}
	}
	
	
	
	// Event Listener on Button[#btnRental].onAction
	@FXML
	public void onclickRental(ActionEvent event) throws SQLException, IOException {
		
		if (bike == null) {
			alert_all.alertWarning("The field to be entered must be not null");
			return;
		}
		if (bike.getBikeType() == null) {
			alert_all.alertWarning("No vehicles in this location");
			return;
		}
		if (bike.getStatus() == 1) {
			alert_all.alertWarning("The vehicle is already in use");
			return;
		}
		if (curentHistory.checkInCurrentRental(user.getId()) == -1) {
			alert_all.alertFieldError("Connect Error!");
			return;
		}
		if (curentHistory.checkInCurrentRental(user.getId()) == 1) {
			alert_all.alertWarning("You have rented a vehicle, return it and continue");
		}
		else {
			if (user.getPay() < minPay) {
				alert_all.alertWarning("Please recharge for rental.You still have to recharge: "+ String.valueOf(minPay-user.getPay()) + "VND");
			}
			else {
				if (alert_all.alertRental(bike.getBikeType().getDeposit()) == 1) 
				{	
					if (curentHistory.addCurrentHistory(bike.getId(), 
							                            user.getId(), 
							                            bike.getBikeType().getDeposit(), 
							                            System.currentTimeMillis()) == 1) 
					{
						if (listBike.updateStatus1(bike.getId()) == 1 && 
								                   handelUser.depositMoney(user.getId(), 
								                   user.getPay(), 
								                   bike.getBikeType().getDeposit()) == 1) 
						{
							alert_all.alertSuccess("Successfully!");
							FXMLLoader loader = new FXMLLoader(getClass().getResource("EcoHome.fxml"));
							Parent root = loader.load();
							EcoHomeController homeController = loader.getController();
							homeController.setUser(handelUser.getUser(user.getUsername(), user.getPassword()));
							Stage window = (Stage) btnRental.getScene().getWindow();
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
	}
}
