/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package senaproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author auxsistemas3
 */
public class LoginController implements Initializable {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnSignin;
    @FXML
    private CheckBox checkMostrar;
    @FXML
    private PasswordField txtPassHidden;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void eventSignin(ActionEvent event) {
        try {
            if (txtUser.getText().equals("admin") && txtPassHidden.getText().equals("admin")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUsersView.fxml"));
                Parent root = loader.load();
                ListUsersController controller = loader.getController();                
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("listusersview.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                
                stage.setOnCloseRequest(e -> controller.closeWindow());
                Stage myStageLogin = (Stage) this.btnSignin.getScene().getWindow();
                myStageLogin.close();
            }
            alert.setHeaderText("DATA BASES");
            alert.setTitle("ERROR: ");
            alert.setContentText("User not found");
            alert.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eventCheckMostrar(ActionEvent event) {
        if (checkMostrar.isSelected()) {
            txtPass.setText(txtPassHidden.getText());
            txtPass.setVisible(true);
            txtPassHidden.setVisible(false);
        }
        if (!checkMostrar.isSelected()) {
            txtPassHidden.setText(txtPass.getText());
            txtPassHidden.setVisible(true);
            txtPass.setVisible(false);
        }
    }
    
    
    public void closeWindow() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("loginview.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.close();
            Stage myStageLogin = (Stage) this.btnSignin.getScene().getWindow();
            myStageLogin.close();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
