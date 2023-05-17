/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package senaproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author auxsistemas3
 */
public class Conexionpg {
    ClassValidation validate = new ClassValidation();
    private Connection conn;
    private String url;
    public Connection getConn() {
        return conn;
    }

    public Conexionpg() {
        try {
            Path filePath = Paths.get("extra/contrase.txt");
            List<String> lines = Files.readAllLines(filePath);
            
            for (String line : lines) {
                System.err.println("34:"+line);
                this.url = line;
            }
            if(this.url == null ){
                validate.driverNotFound();
                return;
            }
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(Conexionpg.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dbClose() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexionpg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
