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
import lk.ijse.restaurant.dto.Salary;
import lk.ijse.restaurant.dto.tm.SalaryTM;
import lk.ijse.restaurant.model.SalaryModel;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ManagesalaryFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDiscription;
    @FXML
    private TextField txtIngredients;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtDatetime1;
    @FXML
    private TableView tblSalary;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colDiscription;
    @FXML
    private TableColumn colIngredients;
    @FXML
    private TableColumn colPrice;
    @FXML
    private TableColumn colDatetime11;
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
    private  void ClearAllTex()
    {
        txtCode.clear();
        txtName.clear();
        txtDiscription.clear();
        txtIngredients.clear();
        txtPrice.clear();
                //Double.parseDouble(txtAmount.getText()),
        txtDatetime1.clear();
    }
    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        colIngredients.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDatetime11.setCellValueFactory(new PropertyValueFactory<>("datetime"));
    }

    private void getAll() {
        try {
            ObservableList<SalaryTM> observableList = FXCollections.observableArrayList();
            List<Salary> salaryList = SalaryModel.getAll();

            for (Salary salary : salaryList) {
                observableList.add(new SalaryTM(
                        salary.getCode(),
                        salary.getName(),
                        salary.getDiscription(),
                        salary.getIngredients(),
                        salary.getPrice(),
                        salary.getDatetime()
                ));
            }
            tblSalary.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void saveOnAction(ActionEvent event) {
        try {
            Salary salary = new Salary(
                    txtCode.getText(),
                    txtName.getText(),
                    txtDiscription.getText(),
                    txtIngredients.getText(),
                    Integer.parseInt(txtPrice.getText()),
                    //Double.parseDouble(txtAmount.getText()),
                    txtDatetime1.getText()
            );
            if (SalaryModel.save(salary) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...!").show();
                tblSalary.refresh();
                getAll();
                ClearAllTex();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
            System.err.println(" помилка: " + e.getMessage());
            System.err.println( txtCode.getText());
            System.err.println( txtName.getText());
            System.err.println( txtDiscription.getText());
            System.err.println( txtIngredients.getText());
            System.err.println( Integer.parseInt(txtPrice.getText()));
            System.err.println( txtDatetime1.getText());
        }
    }

    @FXML
    private void searchOnAction(ActionEvent event) {
        try {
            Salary salary = SalaryModel.search(txtCode.getText());
            if (salary != null) {
                txtName.setText(salary.getName());
                txtDiscription.setText(salary.getDiscription());
                txtIngredients.setText(salary.getIngredients());
                txtPrice.setText(String.valueOf(salary.getPrice()));
                txtDatetime1.setText(String.valueOf(salary.getDatetime()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void updateOnAction(ActionEvent event) {
        try {
            Salary salary = new Salary(
                    txtCode.getText(),
                    txtName.getText(),
                    txtDiscription.getText(),
                    txtIngredients.getText(),
                    Integer.parseInt(txtPrice.getText()),
                    //Double.parseDouble(txtAmount.getText()),
                    txtDatetime1.getText()
            );

            if (SalaryModel.update(salary) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
        getAll();
        setCellValueFactory();
        ClearAllTex();
    }

    @FXML
    private void deleteOnAction(ActionEvent event) {
        try {
            if (SalaryModel.delete(txtCode.getText()) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully...!").show();
                getAll();
                setCellValueFactory();
                ClearAllTex();
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
