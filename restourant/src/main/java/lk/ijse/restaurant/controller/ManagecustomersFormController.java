package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.restaurant.dto.Customer;
import lk.ijse.restaurant.dto.tm.CustomerTM;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.util.Validation;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManagecustomersFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAmountSalary;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtNumberTable;
    @FXML
    private TableView tblCustomer;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colAmountSalary;
    @FXML
    private TableColumn colPrice;
    @FXML
    private TableColumn colNumberTable;
    @FXML
    private Label lbldateandtime;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
        Date date = new Date();
        lbldateandtime.setText(simpleDateFormat.format(date));

        getAll();
        setCellValueFactory();
    }

    private void ClearAllText()
    {
        txtId.clear();
        txtName.clear();
        txtAmountSalary.clear();
        txtPrice.clear();
        txtNumberTable.clear();
    }

    public void setBorders(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-border-color: transparent");
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAmountSalary.setCellValueFactory(new PropertyValueFactory<>("amountSalary"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colNumberTable.setCellValueFactory(new PropertyValueFactory<>("NumberTable"));
    }

    private void getAll() {
        try {
            ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
            List<Customer> customerList = CustomerModel.getAll();

            for (Customer customer : customerList) {
                observableList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getAmountSalary(),
                        customer.getPrice(),
                        customer.getNumberTable()
                ));
            }
            tblCustomer.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }



    @FXML
    private void saveOnAction(ActionEvent event) {

        try {
            Customer customer = new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    Integer.parseInt(txtAmountSalary.getText()),
                    Integer.parseInt(txtPrice.getText()),
                    txtNumberTable.getText()

            );

            if (CustomerModel.save(customer) > 0) {
                //setCellValueFactory();

                /*ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
                observableList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getNic(),
                        customer.getEmail(),
                        customer.getContact(),
                        customer.getAddress()
                ));
                tblCustomer.setItems(observableList);*/

                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...!").show();
                tblCustomer.refresh();
                getAll();
                ClearAllText();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
        txtName.requestFocus();
    }

    @FXML
    private void searchOnAction(ActionEvent event) {

        try {
            Customer customer = CustomerModel.search(txtId.getText());
            if (customer != null) {
                txtName.setText(customer.getName());
                txtAmountSalary.setText(String.valueOf(customer.getAmountSalary()));
                txtPrice.setText(String.valueOf(customer.getPrice()));
                txtNumberTable.setText(customer.getNumberTable());
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void updateOnAction(ActionEvent event) {

        try {
            Customer customer = new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    Integer.parseInt(txtAmountSalary.getText()),
                    Integer.parseInt(txtPrice.getText()),
                    txtNumberTable.getText()
            );

            if (CustomerModel.update(customer) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
        getAll();
        setCellValueFactory();
        ClearAllText();
    }

    @FXML
    private void deleteOnAction(ActionEvent event) {
        try {
            if (CustomerModel.delete(txtId.getText()) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully...!").show();
                getAll();
                setCellValueFactory();
                ClearAllText();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void backOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/admindashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Cashier Dashboard");
        stage.setResizable(false);
        stage.show();
    }
}
