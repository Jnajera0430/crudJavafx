/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package senaproject;

import javafx.scene.control.Alert;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author auxsistemas3
 */
public class ClassValidation {

    Alert alertErr = new Alert(Alert.AlertType.ERROR);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public boolean fieldsValidate(String name, String email, int age, String address) {
        if (name.isEmpty()) {
            alert.setHeaderText("Empty fields");
            alert.setTitle("Warning: ");
            alert.setContentText("The field name is empty..");
            alert.showAndWait();
            return false;
        }
        if (email.isEmpty()) {
            alert.setHeaderText("Empty fields");
            alert.setTitle("Warning: ");
            alert.setContentText("The field email is empty..");
            alert.showAndWait();
            return false;
        }
        if (address.isEmpty()) {
            alert.setHeaderText("Empty fields");
            alert.setTitle("Warning: ");
            alert.setContentText("The field address is empty..");
            alert.showAndWait();
            return false;
        }
        if (age == 0) {

            alert.setHeaderText("Empty fields");
            alert.setTitle("Warning: ");
            alert.setContentText("Field age is empty..");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean ifExists(int idUser, Connection conn) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement stament = conn.prepareStatement(sql);
            stament.setInt(1, idUser);
            ResultSet result = stament.executeQuery();
            if (!result.next()) {
                alertErr.setHeaderText("DATA BASES");
                alertErr.setTitle("ERROR: ");
                alertErr.setContentText("User not found");
                alertErr.showAndWait();
                return false;
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClassValidation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

}
