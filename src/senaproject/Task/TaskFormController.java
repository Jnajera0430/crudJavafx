/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package senaproject.Task;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import senaproject.Conexionpg;
import senaproject.IUser;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author auxsistemas3
 */
public class TaskFormController implements Initializable {
    
    Conexionpg conexion = new Conexionpg();
    Connection conn = conexion.getConn();
    int idUser;
    @FXML
    private TextField txtTaskUser;
    @FXML
    private TextField txtTaskTitle;
    @FXML
    private TextArea txtTaskDesc;
    @FXML
    private Button btnTaskAdd;
    @FXML
    private Button btnTaskClearAClose;
    @FXML
    private Button btnTaskClearForm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void eventTaskAdd(ActionEvent event) {
        String title,description,status="PENDING",adminTask = "admin";
        title = txtTaskTitle.getText();
        description = txtTaskDesc.toString();
        var task = new ITask();
        
    }
    
    @FXML
    private void eventTaskClearAClose(ActionEvent event) {
    }
    
    @FXML
    private void eventTaskClearForm(ActionEvent event) {
        txtTaskTitle.setText("");
        txtTaskDesc.setText("");
    }
    
    public void insertDatosOfUser(int id) {
        try {
            idUser = id;
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                IUser user = new IUser(result.getString("name"), result.getInt("age"),
                        result.getString("email"), result.getString("address")
                );
                txtTaskUser.setText(user.getName());
            }
            System.err.println(id);
        } catch (SQLException ex) {
            Logger.getLogger(TaskFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
