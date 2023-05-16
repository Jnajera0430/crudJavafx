/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package senaproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import senaproject.Task.TaskController;
import senaproject.Task.TaskFormController;

/**
 * FXML Controller class
 *
 * @author auxsistemas3
 */
public class ListUsersController implements Initializable {

    private int idUser;
    Conexionpg conexion = new Conexionpg();
    private final ClassValidation validate = new ClassValidation();
    Connection conn = conexion.getConn();
    private final ObservableList<IUser> listUser = FXCollections.observableArrayList();

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtAge;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<IUser> tblUsers;
    @FXML
    private TableView<IUser> tblSearched;
    @FXML
    private TableColumn<IUser, Integer> clmnId;
    @FXML
    private TableColumn<IUser, String> clmnName;
    @FXML
    private TableColumn<IUser, String> clmnEmail;
    @FXML
    private TableColumn<IUser, Integer> clmnAge;
    @FXML
    private TableColumn<IUser, String> clmnAddress;
    @FXML
    private TableColumn<IUser, Integer> clmnId1;
    @FXML
    private TableColumn<IUser, String> clmnName1;
    @FXML
    private TableColumn<IUser, String> clmnEmail1;
    @FXML
    private TableColumn<IUser, Integer> clmnAge1;
    @FXML
    private TableColumn<IUser, String> clmnAddress1;
    @FXML
    private Button btnClearForm;
    @FXML
    private Button btnLogout;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnAddTask;
    @FXML
    private Button btnListTask;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listUser();
        // TODO
        if (conn != null) {
            System.out.println("Db connected");
        }

        eventOnChanged();
    }

    @FXML
    private void eventLogout(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/LoginView.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controller.closeWindow());
            Stage myStageLogin = (Stage) this.btnLogout.getScene().getWindow();
            conexion.dbClose();
            myStageLogin.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void eventAdd(ActionEvent event) {
        try {
            String name, email, address;
            int age;
            name = txtName.getText().trim();
            email = txtEmail.getText().trim();
            address = txtAddress.getText().trim();
            age = txtAge.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtAge.getText().trim());

            if (!validate.fieldsValidate(name, email, age, address)) {
                return;
            }
            if(idUser != 0){
                validate.actionNotAllowed();
                return;
            }
            IUser user = new IUser(name, age, email, address);
            String sql = "INSERT INTO users(name,email,age,address) VALUES(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getAddres());
            statement.executeUpdate();
            System.out.println("Guardado con exito");
            clearForm();
            listUser();
        } catch (SQLException ex) {
            Logger.getLogger(ListUsersController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("error db: " + ex);
        }
    }

    @FXML
    private void eventClearForm(ActionEvent event) {
        clearForm();
    }

    @FXML
    private void eventDelete(ActionEvent event) {

        final String sql = "delete from users " + "  where id=?;";
        try {
            boolean validId = validate.ifExists(idUser, conn);
            System.err.println(validId);
            if (!validId) {
                clearForm();
                return;
            }
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idUser);
            if (statement.executeUpdate() == 0) {
                System.err.println("Error: No se eleiminó el registro");
                return;
            }
            System.out.println("Message: Usuario eliminado");
        } catch (SQLException ex) {
            Logger.getLogger(ListUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listUser();
        clearForm();
    }

    @FXML
    private void eventUpdate(ActionEvent event) {
        String name, email, address;
        int age;
        name = txtName.getText();
        email = txtEmail.getText();
        address = txtAddress.getText();
        age = txtAge.getText().isEmpty() ? 0 : Integer.parseInt(txtAge.getText());
        try {
            if (!validate.ifExists(idUser, conn)) {
                clearForm();
                return;
            }
            if (!validate.fieldsValidate(name, email, age, address)) {
                return;
            }
            IUser user = new IUser(idUser, name, age, email, address);
            String sql = """
                     UPDATE users 
                         SET name = ?, email = ?, age = ?, address = ? WHERE id = ? """;
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getAddres());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
            listUser();
            clearForm();

        } catch (SQLException ex) {
            Logger.getLogger(ListUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void clickClmnUser(MouseEvent event) {
        tblUsers.getSelectionModel().clearSelection();
        tblUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                insertDatos(newSelection);
            }
        });
        clmnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        
    }

    @FXML
    private void eventOnChanged() {
        tblSearched.setVisible(false);
        ObservableList<IUser> listUserSearch = FXCollections.observableArrayList();
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                tblUsers.setVisible(false);
                tblSearched.setVisible(true);
                listUserSearch.clear();
                for (IUser user : listUser) {

                    if (user.getName().toLowerCase().contains(newValue.toLowerCase())) {
                        listUserSearch.add(user);
                    } else if (String.valueOf(user.getAge()).contains(newValue.toLowerCase())) {
                        listUserSearch.add(user);
                    } else if (user.getEmail().toLowerCase().contains(newValue.toLowerCase())) {
                        listUserSearch.add(user);
                    } else if (user.getAddres().toLowerCase().contains(newValue.toLowerCase())) {
                        listUserSearch.add(user);
                    } else if (String.valueOf(user.getId()).contains(newValue.toLowerCase())) {
                        listUserSearch.add(user);
                    }
                }
            } else {
                tblUsers.setVisible(true);
                tblSearched.setVisible(false);
            }

        });
        listUserSearch(listUserSearch);
    }

    public void insertDatos(IUser user) {
        txtName.setText(user.getName());
        txtAge.setText(Integer.toString(user.getAge()));
        txtAddress.setText(user.getAddres());
        txtEmail.setText(user.getEmail());
        idUser = user.getId();
    }

    public void clearForm() {
        idUser = 0;
        txtName.setText("");
        txtAge.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        IUser iUser = new IUser();
    }

    public void closeWindow() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/LoginView.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controller.closeWindow());
            Stage myStageLogin = (Stage) this.btnLogout.getScene().getWindow();
            conexion.dbClose();
            myStageLogin.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listUser() {
        try {
            tblUsers.getItems().clear();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while (result.next()) {
                IUser user = new IUser(result.getInt("id"), result.getString("name"),
                        result.getInt("age"), result.getString("email"),
                        result.getString("address"));
                listUser.add(user);
            }

            tblUsers.setItems(listUser);
            clmnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            clmnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            clmnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            clmnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        } catch (SQLException e) {
            System.err.println("error: " + e);
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, e);
        }
    }

    public void listUserSearch(ObservableList<IUser> listUserSearch) {
        tblSearched.getItems().clear();
        tblSearched.setItems(listUserSearch);
        clmnId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnName1.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmnAge1.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmnEmail1.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmnAddress1.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    @FXML
    private void eventAddTask(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/Task/TaskFormView.fxml"));
            Parent root = loader.load();
            TaskFormController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            controller.insertDatosOfUser(idUser);
            stage.setOnCloseRequest(e -> controller.eventTaskClearAClose(event));
            Stage myStageListUser = (Stage) this.btnAddTask.getScene().getWindow();
            conexion.dbClose();
            myStageListUser.close();
        } catch (IOException ex) {
            Logger.getLogger(ListUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eventListYTask(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/senaProject/Task/TaskListView.fxml"));
            Parent root = loader.load();
            TaskController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            if(idUser != 0){
                controller.listTasks(idUser);
            }
            stage.setOnCloseRequest(e -> controller.eventCloseWindow(event));
            Stage myStageListUser = (Stage) this.btnAddTask.getScene().getWindow();
            conexion.dbClose();
            myStageListUser.close();
        } catch (IOException ex) {
            Logger.getLogger(ListUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}