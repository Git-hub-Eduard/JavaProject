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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.restaurant.dto.Supplier;
import lk.ijse.restaurant.dto.tm.SupplierTM;
import lk.ijse.restaurant.model.SupplierModel;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ManagesuppliersFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtAmount;
    @FXML
    private TableView tblsupplier;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colPrice;
    @FXML
    private TableColumn colAmount;
    @FXML
    private Label lbldateandtime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
        Date date = new Date();
        lbldateandtime.setText(simpleDateFormat.format(date));

        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
    private void ClearAllText()
    {
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        txtAmount.clear();
    }
    private void getAll() {
        try {
            ObservableList<SupplierTM> observableList = FXCollections.observableArrayList();
            List<Supplier> supplierList = SupplierModel.getAll();

            for (Supplier supplier : supplierList) {
                observableList.add(new SupplierTM(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getPrice(),
                        supplier.getAmount()
                ));
            }
            tblsupplier.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void saveOnAction(ActionEvent event) {
        try {
            Supplier supplier = new Supplier(
                    txtId.getText(),
                    txtName.getText(),
                    Integer.parseInt(txtPrice.getText()),
                    Integer.parseInt(txtAmount.getText())
            );

            if (SupplierModel.save(supplier) > 0) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...!").show();
                tblsupplier.refresh();
                getAll();
                ClearAllText();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
            System.err.println(txtId.getText());
            System.err.println(txtName.getText());
            System.err.println(Integer.parseInt(txtPrice.getText()));
            System.err.println(Integer.parseInt(txtAmount.getText()));
        }
    }

    @FXML
    private void searchOnAction(ActionEvent event) {
        try {
            Supplier supplier = SupplierModel.search(txtId.getText());
            if (supplier != null) {
                txtName.setText(supplier.getName());
                txtPrice.setText(String.valueOf(supplier.getPrice()));
                txtAmount.setText(String.valueOf(supplier.getAmount()));
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void updateOnAction(ActionEvent event) {
        try {
            Supplier supplier = new Supplier(
                    txtId.getText(),
                    txtName.getText(),
                    Integer.parseInt(txtPrice.getText()),
                    Integer.parseInt(txtAmount.getText())
            );

            if (SupplierModel.update(supplier) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...!").show();
            }
            getAll();
            setCellValueFactory();
            ClearAllText();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void deleteOnAction(ActionEvent event) {
        try {
            if (SupplierModel.delete(txtId.getText()) > 0) {
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
        stage.setTitle("Admin Dashboard");
        stage.setResizable(false);
        stage.show();
    }
}
