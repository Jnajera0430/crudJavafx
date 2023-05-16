/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package senaproject.Task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import senaproject.Conexionpg;
import senaproject.IUser;
import senaproject.ListUsersController;

/**
 * FXML Controller class
 *
 * @author auxsistemas3
 */
public class TaskController implements Initializable {

    Conexionpg conexion = new Conexionpg();
    Connection conn = conexion.getConn();
    private ObservableList<ITask> taskList = FXCollections.observableArrayList();
    @FXML
    private TableView<ITask> tblTaskList;
    @FXML
    private TableColumn<?, ?> clmnTaskId;
    @FXML
    private TableColumn<?, ?> clmnTaskTitle;
    @FXML
    private TableColumn<?, ?> clmnTaskDesc;
    @FXML
    private TableColumn<?, ?> clmnTaskUser;
    @FXML
    private TableColumn<?, ?> clmnTaskState;
    @FXML
    private Button btnColseWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listTasks();
    }

    @FXML
    public void eventCloseWindow(ActionEvent event) {
        try {

            ITask iTask = new ITask();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/ListUsersView.fxml"));
            Parent root = loader.load();
            ListUsersController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controller.closeWindow());
            Stage myStageTaskForm = (Stage) this.btnColseWindow.getScene().getWindow();
            conexion.dbClose();
            myStageTaskForm.close();
        } catch (IOException ex) {
            Logger.getLogger(TaskFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listTasks() {
        try {
            tblTaskList.getItems().clear();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("""
                        SELECT T.id,T.title,T.description,T.fk_user,T.fk_admintask,T.state,U.name 
                        FROM tasks T
                        left JOIN users U 
                        ON U.id = T.fk_user
                        order by T.id; """);
            while (result.next()) {
                ITask task = new ITask(result.getInt("id"), result.getString("title"),
                        result.getString("description"), result.getInt("fk_user"),
                        result.getString("fk_adminTask"), result.getString("state"),
                        result.getString("name"));
                taskList.add(task);
            }

            tblTaskList.setItems(taskList);
            clmnTaskId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmnTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            clmnTaskDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            clmnTaskUser.setCellValueFactory(new PropertyValueFactory<>("name"));
            clmnTaskState.setCellValueFactory(new PropertyValueFactory<>("state"));
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listTasks(int id) {
        try {
            tblTaskList.getItems().clear();
            String sql = """
                        SELECT T.id,T.title,T.description,T.fk_user,T.fk_admintask,T.state,U.name 
                        FROM tasks T 
                        LEFT JOIN users U 
                        ON U.id = T.fk_user
                        WHERE T.fk_user = ?
                        ORDER BY T.id; """;

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ITask task = new ITask(result.getInt("id"), result.getString("title"),
                        result.getString("description"), result.getInt("fk_user"),
                        result.getString("fk_adminTask"), result.getString("state"),
                        result.getString("name"));
                taskList.add(task);
            }

            tblTaskList.setItems(taskList);
            clmnTaskId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmnTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            clmnTaskDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            clmnTaskUser.setCellValueFactory(new PropertyValueFactory<>("name"));
            clmnTaskState.setCellValueFactory(new PropertyValueFactory<>("state"));
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eventClmnClick(MouseEvent event) {
        tblTaskList.getSelectionModel().clearSelection();
        tblTaskList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                insertDatos(newSelection);
            }
        });
        clmnTaskId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        clmnTaskDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmnTaskUser.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmnTaskState.setCellValueFactory(new PropertyValueFactory<>("address"));
    }


    public void insertDatos(ITask task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/Task/TaskFormView.fxml"));
            Parent root = loader.load();
            TaskFormController controller = loader.getController();
            controller.insertDatos(task);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controller.closeWindow());
            Stage myStageTaskForm = (Stage) this.tblTaskList.getScene().getWindow();
            conexion.dbClose();
            myStageTaskForm.close();
        } catch (IOException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void closeWindow() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/ListUsersView.fxml"));
            Parent root = loader.load();
            ListUsersController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controller.closeWindow());

            conexion.dbClose();

        } catch (IOException ex) {
            Logger.getLogger(ListUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
