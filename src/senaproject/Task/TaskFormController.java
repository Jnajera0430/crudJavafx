/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package senaproject.Task;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import senaproject.ListUsersController;
import senaproject.LoginController;

/**
 * FXML Controller class
 *
 * @author auxsistemas3
 */
public class TaskFormController implements Initializable {

    Conexionpg conexion = new Conexionpg();
    Connection conn = conexion.getConn();
    protected int idUser;
    @FXML
    protected TextField txtTaskUser;
    @FXML
    protected TextField txtTaskTitle;
    @FXML
    protected TextArea txtTaskDesc;
    @FXML
    protected Button btnTaskAdd;
    @FXML
    protected Button btnTaskClearAClose;
    @FXML
    protected Button btnTaskClearForm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void eventTaskAdd(ActionEvent event) {
        try {
            String title, description, adminTask = "admin";
            title = txtTaskTitle.getText();
            description = txtTaskDesc.getText();
            ITask task = new ITask(title, description, idUser, adminTask, StateEnum.PENDING);
            String sql = "INSERT INTO tasks(title,description,fk_user,fk_admintask,state) VALUES(?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setInt(3, task.getFk_user());
            statement.setString(4, task.getFk_adminTask());
            statement.setString(5, task.getState());
            statement.executeUpdate();
            System.out.println("Guardado con exito...");

            txtTaskTitle.setText("");
            txtTaskDesc.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(TaskFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void eventTaskClearAClose(ActionEvent event) {
        try {
            txtTaskTitle.setText("");
            txtTaskDesc.setText("");
            ITask iTask = new ITask();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/ListUsersView.fxml"));
            Parent root = loader.load();
            ListUsersController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controller.closeWindow());
            Stage myStageTaskForm = (Stage) this.btnTaskClearAClose.getScene().getWindow();
            conexion.dbClose();
            myStageTaskForm.close();
        } catch (IOException ex) {
            Logger.getLogger(TaskFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void eventTaskClearForm(ActionEvent event) {
        txtTaskTitle.setText("");
        txtTaskDesc.setText("");
        ITask iTask = new ITask();
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
        } catch (SQLException ex) {
            Logger.getLogger(TaskFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeWindow() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/TaskListView.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controller.closeWindow());
            
            conexion.dbClose();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
