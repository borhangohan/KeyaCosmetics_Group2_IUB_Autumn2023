/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpkg;

import HasinMahir.Customer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hasin
 */
public class EmployeeSignupGridController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private ComboBox<String> employeeComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        employeeComboBox.getItems().addAll("HR","Customer Service",
                "Accountant","Receptionist");
    }    

    @FXML
    private void signup(ActionEvent event) {
        String employeeType = employeeComboBox.getValue();
        FileOutputStream fos;
        ObjectOutputStream oos;
        FileInputStream fis;
        ObjectInputStream ois;
        File employeeList;
        if (employeeComboBox.getValue().equals("HR")){
            employeeList = new File("HRList.bin");
        }
        //Checking for empty fields
        if (usernameTextField.getText().equals("") || passwordTextField.getText().equals("") ||
                firstNameTextField.getText().equals("")|| phoneTextField.getText().equals("")
                || lastNameTextField.getText().equals("")) {
            System.out.println("Textfield is empty");
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please fill in all of the fields");
            alert.show();
            return;
        }
        //Validating the Phone number 
        if (phoneTextField.getText().length()!=11){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please enter a valid phone number");
            alert.show();
            return;
        }
        try{
            int phone = Integer.parseInt(phoneTextField.getText());
        } catch(Exception e){
            System.out.println("Phone num parsing to int failed");
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please enter a valid phone number");
            alert.show();
            return;
        }
        //Checking for duplicate
        try {
            customerList = new File("CustomerList.bin");
            fis = new FileInputStream(customerList);
            ois = new ObjectInputStream(fis);
            while (true) {
                Customer registeredCustomer = (Customer)ois.readObject();
                if (registeredCustomer.getUsername().equals(usernameTextField.getText())) {
                    Alert a = new Alert(Alert.AlertType.ERROR,"Username already exists");
                    a.show();
                    return;
                }
            }
            }catch(Exception e) {
            System.out.println("File reading complete");
            System.out.println(e.toString()); 
        }
    }

    @FXML
    private void switchToLoginScreen(ActionEvent event) {
    }
    
}
